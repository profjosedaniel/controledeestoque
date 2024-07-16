package br.ufma.ppgee.eds.sistemacontroleestoque.validation;

import org.checkerframework.checker.units.qual.min;

public class Validation {
    public static void  isInteger(String value,String field) throws ValidationFieldException{
        try {
            Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationFieldException("não é um valor inteiro",field,value);
        }
    }

    public static void isFloat(String value,String field) throws ValidationFieldException{
        try {
            Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new ValidationFieldException("não é um valor float",field,value);
        }
    }
    public static void minFloat(String value,String field,float min) throws ValidationFieldException{
        float val=Float.parseFloat(value);
        if(val<min){
            throw new ValidationFieldException("valor menor que "+min,field,value);
        }
    }
    public static void maxFloat(String value,String field,float max) throws ValidationFieldException{
        float val=Float.parseFloat(value);
        if(val>max){
            throw new ValidationFieldException("valor maior que "+max,field,value);
        }
    }

    public static void minmaxFloat(String value,String field,float min,float max) throws ValidationFieldException{
        isFloat(value, field);
        minFloat(value, field, 0);
        maxFloat(value, field, max);
    }

    public static void notNull(String value,String field) throws ValidationFieldException{
        if(value==null || value.isEmpty()){
            throw new ValidationFieldException("valor nulo",field,value);
        }
    }
    public static void minSizeString(String value,String field,int size) throws ValidationFieldException{
        if(value==null||value.length()<size){
            throw new ValidationFieldException("tamanho menor que "+size,field,value);
        }
    }

    public static void maxSizeString(String value,String field,int size) throws ValidationFieldException{
        if(value==null||value.length()>size){
            throw new ValidationFieldException("tamanho maior que "+size,field,value);
        }
    }

    public static void minMaxSizeString(String value,String field,int minSize,int maxSize) throws ValidationFieldException{
        notNull(value, field);
        minSizeString(value, field, minSize);
        maxSizeString(value, field, maxSize);
    }

    public static void telefone(String value, String field) throws ValidationFieldException{
        try{
            isInteger(value, field);
        }catch(ValidationFieldException e){
            throw new ValidationFieldException("telefone deve ser um número",field,value);
        }
        notNull(value, field);
        minSizeString(value, field, 8);
        maxSizeString(value, field, 15);
       
        

    }

    public static void email(String value, String field) throws ValidationFieldException{
       
            if(value==null||value.isEmpty()){
                throw new ValidationFieldException("email nulo",field,value);
            }
            if(!value.contains("@")||!value.contains(".")){
                throw new ValidationFieldException("email inválido",field,value);
            }
        
        

    }


    public static void password( String value, String field) throws ValidationFieldException{
        try{
            if(value==null||value.isEmpty()){
                throw new ValidationFieldException("password nulo",field,value);
            }
            if(value.length()<8){
                throw new ValidationFieldException("password menor que 8",field,value);
            }
            if(value.length()>50){
                throw new ValidationFieldException("password maior que 50",field,value);
            }
        }catch(ValidationFieldException e){
            throw new ValidationFieldException("telefone inválido",field,value);
        }
        
    }

    public static void cpf(String value, String field)throws ValidationFieldException {
       
            if(value==null||value.isEmpty()){
                throw new ValidationFieldException("cpf nulo",field,value);
            }
            if(value.length()<8&&value.length()>11){
                throw new ValidationFieldException("cpf inválido",field,value);
            }
        try{
            isInteger(value, field);
        }catch(ValidationFieldException e){
            throw new ValidationFieldException("cpf deve ser um número",field,value);
        } 
    }


    }

