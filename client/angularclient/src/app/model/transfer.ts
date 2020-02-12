import {Account} from "./account";

export class Transfer {
  id: number;
  amount: bigint;
  sourceAccount: Account;
  targetAccount: Account;
}
