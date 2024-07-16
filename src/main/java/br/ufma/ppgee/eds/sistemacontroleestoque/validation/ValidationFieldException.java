package br.ufma.ppgee.eds.sistemacontroleestoque.validation;

public class ValidationFieldException  extends Exception{
    private String field;
    private String value;

    public ValidationFieldException(String message,String field,String value){
        super(message);
        this.field=field;
        this.value=value;
    }
    public String getField(){
        return field;
    }

    public String getText(){
        return "\nErro de validação no campo.\nMensagem:"+getMessage()+"\nCampo:"+field+ "\nValor informado:"+value;
    }
}
