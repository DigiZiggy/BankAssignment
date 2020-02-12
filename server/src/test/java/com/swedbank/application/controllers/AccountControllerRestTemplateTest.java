package com.swedbank.application.controllers;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swedbank.application.model.Account;
import com.swedbank.application.model.Currency;
import com.swedbank.application.repositories.AccountRepository;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private AccountRepository mockRepository;

    @Before
    public void init() {
        Account account = new Account(1L, "Gold", new BigDecimal("55555"), Currency.JPY);
        when(mockRepository.findById(1L)).thenReturn(Optional.of(account));
    }

    @Test
    public void find_accountId_OK() throws JSONException {

        String expected = "{id:1,name:\"Gold\",balance:55555,currency:\"JPY\"}";

        ResponseEntity<String> response = restTemplate.getForEntity("/accounts/1", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    public void find_allAccounts_OK() throws Exception {
        List<Account> accounts = Arrays.asList(
                new Account("Gold", new BigDecimal("5000"), Currency.EUR),
                new Account("Silver", new BigDecimal("222"), Currency.USD));

        when(mockRepository.findAll()).thenReturn(accounts);

        String expected = om.writeValueAsString(accounts);

        ResponseEntity<String> response = restTemplate.getForEntity("/accounts", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(mockRepository, times(1)).findAll();
    }

    @Test
    public void find_accountIdNotFound_404() throws Exception {

        String expected = "{status:404,error:\"Not Found\",message:\"Account id not found : 5\",path:\"/accounts/5\"}";

        ResponseEntity<String> response = restTemplate.getForEntity("/accounts/5", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void save_account_CREATED() throws Exception {

        Account newAccount = new Account("Silver", new BigDecimal("222"), Currency.USD);
        when(mockRepository.save(any(Account.class))).thenReturn(newAccount);

        String expected = om.writeValueAsString(newAccount);

        ResponseEntity<String> response = restTemplate.postForEntity("/accounts/add", newAccount, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(mockRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void update_account_OK() throws Exception {

        Account updateAccount = new Account("Silver", new BigDecimal("222"), Currency.USD);
        when(mockRepository.save(any(Account.class))).thenReturn(updateAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(updateAccount), headers);

        ResponseEntity<String> response = restTemplate.exchange("/accounts/edit/1", HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(om.writeValueAsString(updateAccount), response.getBody(), false);

        verify(mockRepository, times(1)).findById(1L);
        verify(mockRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void delete_account_OK() {

        doNothing().when(mockRepository).deleteById(1L);

        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("/accounts/delete/1", HttpMethod.DELETE, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockRepository, times(1)).deleteById(1L);
    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
