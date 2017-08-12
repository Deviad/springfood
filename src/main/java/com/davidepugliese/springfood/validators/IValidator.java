package com.davidepugliese.springfood.validators;

public interface IValidator <T> {

   boolean validate(T params);

}
