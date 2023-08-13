package org.example.utils.validator.impl;

import org.example.utils.validator.Validate;
import org.example.utils.validator.Validator;

import java.lang.reflect.Field;

public class DefaultValidator implements Validator {
    @Override
    public boolean isValid(Object obj) {
        Class ref= obj.getClass();
        for(Field field: ref.getDeclaredFields()){
            Validate annotation = field.getAnnotation(Validate.class);
            if(annotation !=null){
                String pattern = annotation.pattern();
                field.setAccessible(true);
                Object fieldValue=null;
                try {
                    fieldValue=field.get(obj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if(fieldValue instanceof String str){
                   if(!str.matches(pattern)){
                       System.out.println("False: "+ fieldValue);
                       return false;
                   }
                }
            }
        }
        return true;
    }
}
