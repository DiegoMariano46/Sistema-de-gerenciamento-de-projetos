import java.util.Scanner;
import service.UserService;
import service.ProjectService;
import model.Project;
import service.EquipeService;
import model.Equipe;
import service.ValidacaoService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        UserService servico = new UserService();
        ProjectService projeto = new ProjectService();
        EquipeService equipeService = new EquipeService();
        ValidacaoService validar = new ValidacaoService();
        Equipe equipe = new Equipe();
        boolean op=false;
        int menu = 0;       
        Project projetoEscolhido = null;
        System.out.println("Sistema de Gerenciamento de Projetos\n");
        
       
        do{ 
             System.out.println("|--------Menu--------|");
             System.out.println("|1 - Login:          |");
             System.out.println("|2 - cadastrar:      |");
             System.out.println("|3 - Listar:         |");
             System.out.println("|4 - Sair:           |");
             System.out.println("|--------------------|");
             menu = sc.nextInt();
             sc.nextLine();
        switch(menu){
            case 1:
                if(servico.login(sc)){ 
                menuLogado(sc,servico,projeto,projetoEscolhido,equipeService,equipe,validar);
                }
                break;
            case 2:
                servico.cadastrarUsuario(sc,validar);
                break;
            case 3:
                servico.listarUser();
                break;
            case 4:
                System.out.println("Saindo...");
                op=true;
                break;
            default:
                System.out.println("Opção invalida");
        }



        }while(!op);
        
    }
   
    public static void menuLogado(Scanner sc, UserService servico,ProjectService projeto, Project projetoEscolhido,EquipeService equipeService,Equipe equipe,ValidacaoService validar){
         boolean logout=false;
         int menu=0;
            

        servico.limparTela();
        do {
            System.out.println("Olá");
            System.out.println("1 - Listar Usuarios: ");
            System.out.println("2 - Cadastrar usuarios: ");
            System.out.println("3 - Cadastrar projetos: ");
            System.out.println("4 - Listar projetos: ");
            System.out.println("5 - Cadastrar equipe: ");
            System.out.println("6 - Listar equipes: ");
            System.out.println("7 - Entrar no projeto: ");
            System.out.println("8 - Remover Usuario: ");
            System.out.println("9 - Logout ");
            menu = sc.nextInt();
            sc.nextLine();
            switch (menu){
                case 1:
                    servico.listarUser();
                    break;
                case 2:
                    servico.cadastrarUsuario(sc,validar);
                    break;
                case 3:
                    projeto.cadastrarProjetos(sc,servico);
                    break;
                case 4:
                    projeto.listarProjetos();
                    break;
                case 5:
                        equipeService.cadastrarEquipes(sc, servico);
                        break;

                case 6:
                        equipeService.listarEquipes();
                        break;
                case 7:
                    projetoEscolhido = projeto.entrarProjeto(sc);
                    if (projetoEscolhido != null) {
                        menuProjeto(sc, servico, projeto, projetoEscolhido, equipeService, equipe);
                    } else {
                        System.out.println("Nenhum projeto escolhido");
                    }
                    break;
                case 8:
                   servico.removerUsuario(sc);
                    break;
                case 9:
                    logout=true;
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;

            }

        }while(!logout);
    } 


    public static void menuProjeto(Scanner sc, UserService servico,ProjectService projeto, Project projetoEscolhido,EquipeService equipeService,Equipe equipeEscolhida){
        boolean sair=false;
        int menu=0;

        servico.limparTela();
        
        do{ 
        System.out.println("1 - Adicionar equipe: ");
        System.out.println("2 - Entrar equipe: ");
        System.out.println("3 - Alterar status: ");
        System.out.println("4 - Ver detalhes do projeto:");
        System.out.println("5 - Remover ");
        System.out.println("6 - Sair ");
        menu = sc.nextInt();
        sc.nextLine();
            switch (menu){
                case 1:
                    projeto.adicionarEquipe(projetoEscolhido,equipeService,sc);
                    break;
                case 2:
                    equipeEscolhida = equipeService.entrarEquipe(sc);
                    if(equipeEscolhida!=null){
                        menuEquipe(sc,servico,equipeService,equipeEscolhida);
                    }else if(equipeEscolhida==null){
                        System.out.println("Nenhuma equipe escolhida");
                        break;
                    }
                    break;
                case 3:
                    projeto.alterarStatus(sc,projetoEscolhido);
                    break;
                case 4:
                    projeto.detalhesProjeto(projetoEscolhido,servico);
                    break;                
                case 5:
                    projeto.removerProjeto(sc);
                    break;
                case 6:
                    sair=true;
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;
                }
        
        }while(!sair);
    };


    public static void menuEquipe(Scanner sc, UserService servico,EquipeService equipeService,Equipe equipe){
        boolean sair=false;
        int menu=0;

        servico.limparTela();

        do{ 
        System.out.println("1 - Adicionar membro: ");
        System.out.println("2 - Remover membro: ");
        System.out.println("3 - Ver detalhes da equipe: ");
        System.out.println("4 - Remover equipe");
        System.out.println("5 - Sair");
        menu = sc.nextInt();
        sc.nextLine();
            switch (menu){
                case 1:
                    equipeService.adicionarMembroEquipe(sc,servico,equipe);
                    break;
                case 2:
                    equipeService.removerMembroEquipe(sc,equipe);
                    break;
                case 3:
                    equipeService.detalheEquipe(equipe);
                    break;
                 case 4:
                    equipeService.removerEquipe(sc);
                    break;
                case 5:
                    sair=true;
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;
                }

        }while(!sair);
    };
    
}