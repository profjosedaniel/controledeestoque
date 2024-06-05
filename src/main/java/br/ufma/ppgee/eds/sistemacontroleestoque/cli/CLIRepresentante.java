package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Representante;
import br.ufma.util.LerTerminal;

public class CLIRepresentante extends CLIAbstractCRUD<Representante> {
    private RepresentanteDAO representanteDAO;
    public static void main(String[] args) {
        new CLIRepresentante(new LerTerminal()).show();
    }

    @Override
    public void opcoesMenu() {
        
        super.opcoesMenu();
        System.out.println("6 - Relacionar Fabricante ao Representante");
        System.out.println("7 - Relatorio Representante");
    }

    @Override
    public boolean acoesMenu(int opcao) {
        if(opcao==6){
            addRepresentanteFabricante();
            return true;
        }else if(opcao==7){
            relatorio();
            return true;
        }
        return super.acoesMenu(opcao);
    }
    
    public CLIRepresentante(LerTerminal terminal) {
        super(terminal);
        representanteDAO = new RepresentanteDAO(SingletonConnectionDB.getConnection());
    }

    @Override
    public void showEntity(Representante o) {
        System.out.println("============Representante================");
        System.out.println("CPF: " + o.getCpf());
        System.out.println("Nome: " + o.getNome());
        System.out.println("Telefone: " + o.getTelefone());
        System.out.println("Localização: " + o.getEmail());
        System.out.println("\nRepresentações:");
        if(o.getFabricante()!=null&&o.getFabricante().size()>0)
            o.getFabricante().forEach(r->{
                System.out.println("CNPJ do fabricante: "+r.getCnpj());
                System.out.println("Nome do fabricante: "+r.getNome());
            });
        System.out.println("==================================");
    }

    @Override
    public RepresentanteDAO getDAO() {
         return representanteDAO;
    }

    @Override
    public String getName() {
        return Representante.class.getSimpleName();
    }

    @Override
    public Representante get() throws Exception {
        String cpf = terminal.nextLine("Digite o id do representante",false);
        Representante representante = representanteDAO.get(cpf);
        return  representante;
    }

    @Override
    public Representante create() {
        Representante representante = new Representante();
        representante.setCpf(terminal.nextLine("CPF",true));
        representante.setNome(terminal.nextLine("Nome",true));
        representante.setTelefone(terminal.nextLine("Telefone",true));
        representante.setEmail(terminal.nextLine("Email",true));

           
        return representante;
    }

    @Override
    public Representante update(Representante representante) {
        representante.setCpf(terminal.nextLine("CPF",true));
        representante.setNome(terminal.nextLine("Nome",true));
        representante.setTelefone(terminal.nextLine("Telefone",true));
        representante.setEmail(terminal.nextLine("Email",true));
        
        return representante;
    }
 
    public void addRepresentanteFabricante() {
        try {
            System.out.println("Informe o representante e o fabricante");
            Representante representante=  get();
            Fabricante fabricante = new CLIFabricante(terminal).get();
            representanteDAO.addRepresentacao(representante.getCpf(), fabricante.getCnpj());
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    void relatorio() {
        try {
            new CliTable().visualize(getDAO().relatorio());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
