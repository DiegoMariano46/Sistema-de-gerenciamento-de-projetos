package service;
import java.util.ArrayList;
import java.util.Scanner;
import model.Equipe;
import model.User;
import model.PerfilUsuario;
import model.Project;
import model.StatusProject;


public class EquipeService{

  ArrayList<Equipe> equipes = new ArrayList<Equipe>();

    public Equipe entrarEquipe(Scanner sc){
      Equipe equipeEscolhida=null;
      if(equipes.isEmpty()){
        System.out.println("Nenhuma equipe adicionada");
        return equipeEscolhida;
      };
      for(int i=0;i<equipes.size();i++){     
        System.out.println(i+" = "+ equipes.get(i).getNomeEquipe()); 
  }
    System.out.println("Digite a equipe: ");
    int indice = sc.nextInt();
    sc.nextLine();
    if(indice>=equipes.size() || indice <0){
        System.out.println("Indice incorreto");

    }else{    
       equipeEscolhida = equipes.get(indice);

    }
      return equipeEscolhida;

  }
    public void adicionarMembroEquipe(Scanner sc, UserService service, Equipe equipeEscolhida){
        if(!service.existeColaborador()){
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        int id;

        for(int i = 0; i < service.getUsers().size(); i++){
            if(service.getUsers().get(i).getPerfil() == PerfilUsuario.COLABORADOR){
                System.out.println(i + " = " + service.getUsers().get(i).getName());
            }
        }

        System.out.println("Digite o id do colaborador: ");
        id = sc.nextInt();
        sc.nextLine();

        if(id >= service.getUsers().size() || id < 0){
            System.out.println("ID incorreto");
            return;
        }

        User user = service.getUsers().get(id);

        if(equipeEscolhida.getMembros().contains(user)){
            System.out.println("Esse usuário já pertence à equipe.");
            return;
        }

        equipeEscolhida.getMembros().add(user);
        System.out.println("Usuário adicionado.");
    }
    public void cadastrarEquipes(Scanner sc, UserService service){
    String nome,descricao;
    Equipe equipe = new Equipe();
    service.limparTela();  
    System.out.println("Bem vindo!");
    System.out.println("Nome da equipe: ");
    nome = sc.nextLine();
    System.out.println("Faça a descrição da equipe: ");
    descricao=sc.nextLine(); 
    
    equipe.setNomeEquipe(nome);
    equipe.setDescricao(descricao);
    equipes.add(equipe);




}
    public void listarEquipes(){
    if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada.");
            return;
        }

        for (Equipe equipe:equipes){          
                System.out.println("--------------------------------");
                System.out.println("Nome: " + equipe.getNomeEquipe());
                System.out.println("Descrição: "+equipe.getDescricao());
                System.out.println("--------------------------------");

        }

    }
    public void removerMembroEquipe(Scanner sc, Equipe equipeEscolhida){
      int id;
      if (equipeEscolhida.getMembros().isEmpty()) {
          System.out.println("Nenhuma membro cadastrado.");
          return;
      }

    for(int i=0;i<equipeEscolhida.getMembros().size();i++){
        
          System.out.println(i+" = "+ equipeEscolhida.getMembros().get(i).getName());
        }
      System.out.println("Digite o indice do colaborador: ");
      id = sc.nextInt();
      sc.nextLine();
        if(id>=equipeEscolhida.getMembros().size() ||id<0){
          System.out.println("Indice incorreto");
        }else{
          System.out.println("Usuario removido");
            equipeEscolhida.getMembros().remove(id);
          }
    }
    public void removerEquipe(Scanner sc){

        if(equipes.isEmpty()){
            System.out.println("Nenhuma equipe cadastrada.");
            return;
        }

        for(int i = 0; i < equipes.size(); i++){
            System.out.println(i + " = " + equipes.get(i).getNomeEquipe());
        }

        System.out.println("Digite o índice da equipe: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(id < 0 || id >= equipes.size()){
            System.out.println("Índice inválido");
            return;
        }

        equipes.remove(id);
        System.out.println("Equipe removida com sucesso!");
    }
 
    public void detalheEquipe( Equipe equipeEscolhida){
      
      System.out.println("Nome: " + equipeEscolhida.getNomeEquipe());
      System.out.println("Descrição: " + equipeEscolhida.getDescricao());

      if(equipeEscolhida.getMembros().isEmpty()){
          System.out.println("Nenhum membro cadastrado.");
          return;
      }

      System.out.println("Membros:");

      for(int i = 0; i < equipeEscolhida.getMembros().size(); i++){
          System.out.println(i + " = " +
              equipeEscolhida.getMembros().get(i).getName());
      }
  
      
      
    }

    public ArrayList<Equipe> getEquipes(){

      return equipes;
    }
  
  }
