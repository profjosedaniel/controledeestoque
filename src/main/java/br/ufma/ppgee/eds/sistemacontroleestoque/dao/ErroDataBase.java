package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import org.postgresql.util.PSQLException;

public class ErroDataBase {
    public String getMessageErro( PSQLException e){
        System.out.println("-------------");
        System.out.println("getErrorCode:"+e.getErrorCode());
        System.out.println("getMessage:"+e.getMessage());
        System.out.println("getSQLState:"+e.getSQLState());
        System.out.println("getLocalizedMessage:"+e.getLocalizedMessage());

        System.out.println("-------------");
        System.out.println("getConstraint:"+e.getServerErrorMessage().getLine());
        System.out.println("getConstraint:"+e.getServerErrorMessage().getConstraint());
        System.out.println("getDatatype:"+e.getServerErrorMessage().getDatatype());
        System.out.println("getDetail:"+e.getServerErrorMessage().getDetail());
        System.out.println("getColumn:"+e.getServerErrorMessage().getColumn());
        System.out.println("getHint:"+e.getServerErrorMessage().getHint());
        System.out.println("-------------");


        String msg="";
        if(e.getServerErrorMessage().getLine()==673){
            msg+= "Valor duplicado!\n";
        }else if(e.getServerErrorMessage().getLine()==641){
            msg+= "Valor muito longo!\n";
        }else if(e.getServerErrorMessage().getLine()==2060){
            msg+= "\n";
        }

        if(e.getServerErrorMessage()!=null&&e.getServerErrorMessage().getDatatype()!=null){
            msg+="Coluna:"+e.getServerErrorMessage().getDatatype().split("_")[1]+"\n";
        }
        
        if(e.getServerErrorMessage().getDetail()!=null){
            msg+="Mensagem:"+e.getServerErrorMessage().getDetail()+"\n";
        }else if(e.getLocalizedMessage()!=null){
            msg+="Mensagem:"+e.getLocalizedMessage()+"\n";
        }
        return msg;
    }
}
