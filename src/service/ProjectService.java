package service;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;
import model.PerfilUsuario;
import model.Project;
import model.StatusProject;
import model.Equipe;


public class ProjectService{

  ArrayList<Project> projects = new ArrayList<Project>(); 
  

  public void cadastrarProjetos(Scanner sc, UserService service){
    String nome,descricao,dataInicio,dataFim;
  int op;
  boolean loop=true,achouGerente=false;
  Project project = new Project();
    service.limparTela();
    if(!service.existeGerente()){
        System.out.println("Nenhum gerente cadastrado.");
        System.out.println("Cadastre um gerente antes de criar um projeto.");
        return;
    }
    System.out.println("Bem vindo!");
    System.out.println("Nome do projeto: ");
    nome = sc.nextLine();
    System.out.println("Faça a descrição do projeto: ");
    descricao=sc.nextLine();
    System.out.println("Qual a data de inicio: ");
    dataInicio=sc.nextLine();
    System.out.println("Qual a previsão de finalizar: ");
    dataFim=sc.nextLine();

    do{ 
      System.out.println("Diga qual o Status do projeto: ");
      System.out.println("1 - Planejado");
      System.out.println("2 - Em andamento");
      System.out.println("3 - Concluido");
      System.out.println("4 - Cancelado");
      op = sc.nextInt();
      sc.nextLine();
      switch(op){
        case 1:
          project.setStatus(StatusProject.PLANEJADO);
          loop = false;
          break;
        case 2:
          project.setStatus(StatusProject.EM_ANDAMENTO);
          loop = false;
          break;
        case 3:
          project.setStatus(StatusProject.CONCLUIDO);
          loop = false;
          break;
        case 4:
          project.setStatus(StatusProject.CANCELADO);
          loop = false;
          break;
        default:
          System.out.println("Opção invalida");
      }
      }while(loop);

      

  do{    
  for(int i=0;i<service.getUsers().size();i++){
    if(service.getUsers().get(i).getPerfil()==PerfilUsuario.GERENTE){ 
      System.out.println(i+" = "+ service.getUsers().get(i).getName()); }
      
  }
    System.out.println("Gerente responsavel: ");
    int indice = sc.nextInt();
    sc.nextLine();
    if(indice>=service.getUsers().size() || indice <0){
        System.out.println("Indice incorreto");
        
    }else{    
    project.setGerenteResponsavel(service.getUsers().get(indice));
        achouGerente=true;    
    }
  }while(!achouGerente);


  
    project.setNomeProjeto(nome);
    project.setDescricao(descricao);
    project.setDataInicio(dataInicio);
    project.setDataFimPrevista(dataFim);
    projects.add(project);
    
    

  
}
  public void listarProjetos(){
    if (projects.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
            return;
        }

        for (Project project : projects){          
                System.out.println("--------------------------------");
                System.out.println("Nome: " + project.getNomeProjeto());
                System.out.println("Data de inicio: " + project.getDataInicio());
                System.out.println("Data de finalização: "+ project.getDataFimPrevista());
                System.out.println("Status: "+ project.getStatus());
                System.out.println("Gerente: "+project.getGerenteResponsavel().getName());
                System.out.println("Descrição: "+project.getDescricao());
                System.out.println("--------------------------------");

        }

    }
  public Project entrarProjeto(Scanner sc){
  Project projetoEscolhido=null;
  if(projects.isEmpty()){
      System.out.println("Nenhum projeto adicionado");
      return projetoEscolhido;
  };
  for(int i=0;i<projects.size();i++){     
      System.out.println(i+" = "+ projects.get(i).getNomeProjeto()); 
}
  System.out.println("Digite o projeto: ");
  int indice = sc.nextInt();
  sc.nextLine();
  if(indice>=projects.size() || indice <0){
      System.out.println("Indice incorreto");

  }else{    
     projetoEscolhido = projects.get(indice);
   
  }
    return projetoEscolhido;
  
}
  public void alterarStatus(Scanner sc, Project projetoEscolhido){
  int op;
  boolean loop=true;
  
    
   System.out.println("Nome: " + projetoEscolhido.getNomeProjeto());
   System.out.println("Status: " + projetoEscolhido.getStatus());
  
  
  do{ 
    System.out.println("Diga qual o novo Status do projeto: ");
    System.out.println("1 - Planejado");
    System.out.println("2 - Em andamento");
    System.out.println("3 - Concluido");
    System.out.println("4 - Cancelado");
    op = sc.nextInt();
    sc.nextLine();
    switch(op){
      case 1:
        projetoEscolhido.setStatus(StatusProject.PLANEJADO);
        loop = false;
        break;
      case 2:
        projetoEscolhido.setStatus(StatusProject.EM_ANDAMENTO);
        loop = false;
        break;
      case 3:
        projetoEscolhido.setStatus(StatusProject.CONCLUIDO);
        loop = false;
        break;
      case 4:
        projetoEscolhido.setStatus(StatusProject.CANCELADO);
        loop = false;
        break;
      default:
        System.out.println("Opção invalida");
    }
    }while(loop);
  
}
  public void detalhesProjeto(Project projetoEscolhido, UserService servico){
            servico.limparTela();
            System.out.println("--------------------------------");
            System.out.println("Nome: " + projetoEscolhido.getNomeProjeto());
            System.out.println("Data de inicio: " + projetoEscolhido.getDataInicio());
            System.out.println("Data de finalização: "+ projetoEscolhido.getDataFimPrevista());
            System.out.println("Status: "+ projetoEscolhido.getStatus());
            System.out.println("Gerente: "+projetoEscolhido.getGerenteResponsavel().getName());
            System.out.println("Descrição: "+projetoEscolhido.getDescricao());
            System.out.println("--------------------------------");
            System.out.println("          Equipes         ");
              if(projetoEscolhido.getEquipes().isEmpty()){
                  System.out.println("Nenhuma equipe vinculada.");
                  return;
              }
            for(int i=0;i<projetoEscolhido.getEquipes().size();i++){
                System.out.println(i+" = " +projetoEscolhido.getEquipes().get(i).getNomeEquipe());
            }
           
      
}
    public void adicionarEquipe(Project projetoEscolhido,
                                EquipeService equipeService,
                                Scanner sc){

        int indice;

        if (equipeService.getEquipes().isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada.");
            return;
        }

        for (int i = 0; i < equipeService.getEquipes().size(); i++) {
            System.out.println(i + " = " +
                    equipeService.getEquipes().get(i).getNomeEquipe());
        }

        System.out.println("Digite o Id da equipe: ");
        indice = sc.nextInt();
        sc.nextLine();

        if(indice >= equipeService.getEquipes().size() || indice < 0){
            System.out.println("Indice incorreto");
            return;
        }

        Equipe equipe = equipeService.getEquipes().get(indice);

        if(projetoEscolhido.getEquipes().contains(equipe)){
            System.out.println("Essa equipe já está no projeto.");
        }else{
            projetoEscolhido.getEquipes().add(equipe);
            System.out.println("Equipe adicionada.");
        }
    }
  public void removerProjeto(Scanner sc){

      if(projects.isEmpty()){
          System.out.println("Nenhum projeto cadastrado.");
          return;
      }

      for(int i = 0; i < projects.size(); i++){
          System.out.println(i + " = " + projects.get(i).getNomeProjeto());
      }

      System.out.println("Digite o índice do projeto: ");
      int id = sc.nextInt();
      sc.nextLine();

      if(id < 0 || id >= projects.size()){
          System.out.println("Índice inválido");
          return;
      }

      projects.remove(id);
      System.out.println("Projeto removido com sucesso!");
  }



  
}
