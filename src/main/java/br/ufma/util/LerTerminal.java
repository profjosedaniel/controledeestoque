package br.ufma.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Date;

public class LerTerminal {
    public static BufferedReader input ;
    public LerTerminal(){
        input = new BufferedReader(new InputStreamReader(System.in));
 
    }
    
    private  String nextLine() throws IOException{
        return input.readLine();
    }

    public String nextLine(String label,boolean obrigatorio){
        return nextLine(label,obrigatorio,null);
    }
    public String nextLine(String label,boolean obrigatorio,String defaultValue){
        do{
            if(label!=null && label.length()>0){
                if(defaultValue!=null && defaultValue.length()>0)
                    System.out.println("O valor atual para "+label+" é ("+defaultValue+") digite um novo valor:");
                else
                    System.out.println("Digite um valor para "+label+":");
            }
            try{
                String str=nextLine();
                if(str.length()>=1)
                    return str.trim() ;
            }catch(Exception e){
                System.out.println("Valor inválido, digite um valor para "+label+":");
            }
            if(obrigatorio){
                System.out.println("Você deve digitar um valor válido para "+label+"!");
            }
        }while(obrigatorio);
        return null;
    }

    public  Integer nextInt(String label,boolean obrigatorio){
        do{
            
            try{
                String str=nextLine(label,obrigatorio);
                return Integer.parseInt(str);
            }catch(Exception e){
                System.out.println("Valor inválido, digite um valor inteiro para "+label+":");
            }   
        }while(obrigatorio);
        return null;
        
    }

    public  Integer nextInt(String label,boolean obrigatorio,Serializable defaultValue){
        do{
            if(label!=null && label.length()>0){
                if(defaultValue!=null )
                    System.out.println("O valor atual para "+label+" é ("+defaultValue+") digite um novo valor:");
                else
                    System.out.println("Digite um valor para "+label+":");
            }
            try{
                String str=nextLine(label,obrigatorio);
                return Integer.parseInt(str);
            }catch(Exception e){
                System.out.println("Valor inteiro inválido, digite um valor para "+label+":");
            }
        }while(obrigatorio);
        return null;
        
    }

    public   Float nextFloat(String label,boolean obrigatorio){
        do{
          
            try{
                String str=nextLine(label,obrigatorio);
                return Float.parseFloat(str);
            }catch(Exception e){
                System.out.println("Valor inválido, digite um valor real para "+label+":");
            }
        }while(obrigatorio);
        return null;
    }

    public   Double nextDouble(String label,boolean obrigatorio){
        do{
          
            try{
                String str=nextLine(label,obrigatorio);
                return Double.parseDouble(str);
            }catch(Exception e){
                System.out.println("Valor inválido, digite um valor real para "+label+":");
            }
        }while(obrigatorio);
        return null;
    }
    public  String nextString(String label,boolean obrigatorio){
        do{
             
            String str=nextLine(label,obrigatorio);
            if(str.length()>=2){
                if(!str.contains(" ")){
                    return str;
                }else{
                    System.err.println("O valor digitado não poder conter espaço");
                }
                
            }else if(obrigatorio){
                System.err.println("Valor deve possuir pelo menos dois caracteres para "+label);
            }
    
        }while(obrigatorio);
        return null;
    }

    public  Date nextDate(String label,boolean obrigatorio){
         
        do{
           
            try{
                String str=nextLine(label,obrigatorio);
                return Date.valueOf(str);
            }catch(Exception e){
                
                System.err.println("Valor inválido, digite uma data para "+label+":");
            }
        }while(obrigatorio);
        return null;
    }

}
