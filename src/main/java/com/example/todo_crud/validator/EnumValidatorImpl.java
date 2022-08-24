package com.example.todo_crud.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private List<String> valueList;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {

        valueList = new ArrayList<String>();
        
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();
        
        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();
        
        for (@SuppressWarnings("rawtypes")
        Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString());
        }
        System.out.println(valueList);

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Validando "+value);
        return valueList.contains(value);
    }

}