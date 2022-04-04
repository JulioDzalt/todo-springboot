package com.example.todo_crud.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ISO8016 implements ConstraintValidator<DateISOValidation, String> {
    @Override
    public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
        // try {
        //     OffsetDateTime.parse("2013-08-11T17:22:04.51+01:00");
        //     System.out.println("Valid ISO 8601");
            
        //     return true;
        // } catch (DateTimeParseException e) {
        //     System.out.println("Not valid ISO 8601");
        // }

        // return false;
        if(valueToValidate != null){
            System.out.println("Validating date");
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            sdf.setLenient(false);
            try {
                sdf.parse(valueToValidate);
                System.out.println("Valid ISO 8601");
                return true;
            } catch (ParseException e) {
                System.out.println("Not valid ISO 8601");
            }
        }
        return false;
    }
}