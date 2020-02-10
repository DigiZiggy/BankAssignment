export enum Currency {
  USD = 'USD',
  AUD = 'AUD',
  EUR = 'EUR',
  GBP = 'GBP',
  JPY = 'JPY'
}

export namespace Currency {

  export function values() {
    return Object.keys(Currency).filter(
      (type) => isNaN(<any>type) && type !== 'values'
    );
  }
}
