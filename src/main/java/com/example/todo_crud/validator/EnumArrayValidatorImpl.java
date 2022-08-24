package com.example.todo_crud.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumArrayValidatorImpl implements ConstraintValidator<EnumArrayValidator, ArrayList<String>> {

    private List<String> valueEnumList;

    @Override
    public void initialize(EnumArrayValidator constraintAnnotation) {

        valueEnumList = new ArrayList<String>();
        
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();
        
        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();
        
        for (@SuppressWarnings("rawtypes")
        Enum enumVal : enumValArr) {
            valueEnumList.add(enumVal.toString());
        }
        System.out.println(valueEnumList);

    }

    @Override
    public boolean isValid(ArrayList<String> values, ConstraintValidatorContext context) {

        System.out.println("Validando enums "+values);
        boolean valuesAreValid = true;
        
        for (String s : values) {
            if(!valueEnumList.contains(s)){
                valuesAreValid = false;
                break;
            }

        }

        return valuesAreValid;
    }

}