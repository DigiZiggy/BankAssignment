import { AbstractControl, ValidatorFn } from '@angular/forms';
import { NgModule } from '@angular/core';

import { max } from './max/validator';
import { min } from './min/validator';

import { MaxValidator } from './max/directive';
import { MinValidator } from './min/directive';

export const CustomValidators = {
  max,
  min
};

const CustomDirectives = [
  MaxValidator,
  MinValidator
];

@NgModule({
  declarations: [CustomDirectives],
  exports: [CustomDirectives]
})
export class CustomFormsModule { }
