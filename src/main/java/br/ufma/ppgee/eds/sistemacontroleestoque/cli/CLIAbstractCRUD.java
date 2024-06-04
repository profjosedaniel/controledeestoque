package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.util.List;
import br.ufma.util.LerTerminal;

public abstract class CLIAbstractCRUD<T> implements CLIInterface<T> {
    
    public LerTerminal terminal;
    public static final int OPCAO_CADASTRAR = 1;
    public static final int OPCAO_CONSULTAR = 2;
    public static final int OPCAO_ATUALIZAR = 3;
    public static final int OPCAO_EXCLUIR = 4;
    public static final int OPCAO_LISTAR = 5;
    public  CLIAbstractCRUD(LerTerminal terminal){
        this.terminal = terminal;
    }
    public void opcoesMenu() {
            System.out.println("0 - Sair");
            System.out.println(OPCAO_CADASTRAR+" - Cadastrar "+getName());
            System.out.println(OPCAO_CONSULTAR+" - Consultar "+getName());
            System.out.println(OPCAO_ATUALIZAR+" - Atualizar "+getName());
            System.out.println(OPCAO_EXCLUIR+" - Excluir "+getName());
            System.out.println(OPCAO_LISTAR+" - Listar "+getName());
            
    }
    public boolean acoesMenu(int opcao) {
        switch (opcao) {
            case OPCAO_CADASTRAR:
                createEntity();
                return true;
            case OPCAO_CONSULTAR:
                getEntity();
                return true;
            case OPCAO_ATUALIZAR:
                updateEntity();
                return true;
            case OPCAO_EXCLUIR:
                deleteEntity();
                return true;
            case OPCAO_LISTAR:
                getAllEntity();
                return true;
            default:
                return false;
        }
    }

    
    @Override
    public void show() {
            System.out.println("======================================="); 
            opcoesMenu();
            System.out.println("=======================================");
            Integer opcao = terminal.nextInt("Digite a opção desejada",true);
            if(opcao==0){
                System.out.println("Saindo...");
            }else if(!acoesMenu(opcao)){
               System.out.println("Opção inválida");
            }
           
    }

    public void getEntity(){
        T o;
        try {
            o = get();
            if( o != null){
                showEntity(o);
            }else{
                System.out.println(getName()+" não encontrado");
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao consultar "+getName());
            System.out.println(e.getMessage());
        }

    }
    @Override
    public void createEntity() {
        try{
            T fabricante = create();
  
            getDAO().create(fabricante);
            System.out.println(getName()+" cadastrado com sucesso");
        }catch (Exception e){
            System.err.println("Erro ao cadastrar "+getName());
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity() {
        
        try{
            T fabricante = get();
            if(fabricante != null){
                fabricante=update(fabricante);
                getDAO().update(fabricante);
                System.out.println(getName()+" atualizado com sucesso");
            }else{
                System.err.println(getName()+" não encontrado");
            }
        }catch (Exception e){
            System.err.println("Fabricante não encontrado");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity() {
      
        try{
            T fabricante = get();
            if(fabricante != null){
                getDAO().deleteE(fabricante);
                System.out.println(getName()+" excluído com sucesso");
            }else{
                System.err.println(getName()+" não encontrado");
            }
        }catch (Exception e){
            System.err.println(getName()+" não encontrado");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getAllEntity() {
        try{
            List<T> l =getDAO().getAll();
            for(T entity : l ){
                if(entity!=null)
                    showEntity(entity);
            }
        }catch (Exception e){
            System.out.println("Erro ao listar "+getName());
            System.out.println(e.getMessage());
        }
    }


}
