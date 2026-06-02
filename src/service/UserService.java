package service;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;
import model.PerfilUsuario;
import repository.UserRepository;


public class UserService {

    ArrayList<User> users = new ArrayList<User>();

    ValidacaoService validador = new ValidacaoService();

    private UserRepository repository = new UserRepository();

    public UserService() {

        users = repository.carregarUsuarios();

        if(users.isEmpty()){
            criarAdmin();
            repository.salvarUsuarios(users);
        }
    }


  public void limparTela(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  

  
  private void criarAdmin() {
      User user = new User();
      user.setName("Administrador");
      user.setLogin("Admin");
      user.setPassword("1234");
      user.setCpf("00000000000");
      user.setCargo("Admin");
      user.setEmail("adm@email.com");
      user.setPerfil(PerfilUsuario.ADMINISTRADOR);

      users.add(user);
  }
  
public boolean existeColaborador(){
      for(User user : users){
          if(user.getPerfil() == PerfilUsuario.COLABORADOR){
              return true;
          }
      }
      return false;
  }
public boolean existeGerente(){
      for(User user : users){
          if(user.getPerfil() == PerfilUsuario.GERENTE){
              return true;
          }
      }
      return false;
  }
private boolean cpfExiste(String cpf){
      for(User u :users){
        if(u.getCpf().equals(cpf)){
         return true;
        }
      }      
          return false;
  
  }
private boolean emailExiste(String email){
    for(User u: users){
      if(u.getEmail().equals(email)){
        return true;
      }
    }  

  return false;
}
private boolean loginExiste(String login){
      for(User u: users){
        if(u.getLogin().equals(login)){
          return true;
        }
      }  

    return false;
  }
public void cadastrarUsuario(Scanner sc,ValidacaoService validador){
   
    String nome,cpf,email,login,senha,cargo;
    int op;
    boolean cpfValido=false;
    boolean emailValido=false;
    boolean loginValido=false;
    boolean loop=true;
    boolean nomeValido=false;
    User user = new User();

  
      limparTela();
  
      System.out.println("Cadastro de colaborador");
      do{ 
      System.out.println("Digite o nome completo: ");
      nome = sc.nextLine();
        if(!validador.nomeValido(nome)){
          System.out.println("Nome invalido. Use apenas letras.");
          continue;
        }
        nomeValido = true;
      }while(!nomeValido);
    
    do{ 
      System.out.println("Digite o CPF: ");
      cpf = sc.nextLine();

      if (!validador.cpfValido(cpf)) {
          System.out.println("CPF inválido");
          continue;
      }
      if (cpfExiste(cpf)) {
          System.out.println("CPF já existe");
          continue;
      }
           cpfValido = true;
      }while(!cpfValido);
     
    do{
      System.out.println("Digite o e-mail: ");
      email = sc.nextLine();
       
      if(!validador.emailValido(email)) {
      System.out.println("E-mail inválido");
      continue;
      }

      if (emailExiste(email)){
        System.out.println("E-mail já existe");
        continue;
      }  
        emailValido = true;
          
     }while (!emailValido);

      do{
        System.out.println("Digite o login: ");
        login = sc.nextLine();

            if(loginExiste(login)){
              System.out.println("Login já existe");
            }else{
              loginValido = true;
            }

       }while (!loginValido);

        System.out.println("Digite a senha: ");
        senha = sc.nextLine();
        
        System.out.println("Digite o cargo do colaborador: ");
        cargo = sc.nextLine();  
  
        do{ 
        System.out.println("Diga qual o perfil do colaborador: ");
        System.out.println("1 - Administrador");
        System.out.println("2 - Gerente");
        System.out.println("3 - Colaborador");
        op = sc.nextInt();
        sc.nextLine();
        switch(op){
          case 1:
            user.setPerfil(PerfilUsuario.ADMINISTRADOR);
            loop = false;
            break;
          case 2:
            user.setPerfil(PerfilUsuario.GERENTE);
            loop = false;
            break;
          case 3:
            user.setPerfil(PerfilUsuario.COLABORADOR);
            loop = false;
            break;
          default:
            System.out.println("Opção invalida");
        }
        }while(loop);
      System.out.println("Usuário cadastrado com sucesso!");

      user.setName(nome);
      user.setCpf(cpf);
      user.setEmail(email);
      user.setPassword(senha);
      user.setLogin(login);
      user.setCargo(cargo);
      users.add(user);
    repository.salvarUsuarios(users);
  }
public boolean login(Scanner sc){
    String username,senha;
    boolean logado = false;
    int cont=0;

    while(!logado){
    limparTela();  
    boolean encontrou = false; 
    System.out.println("Login: ");
    username = sc.nextLine();
    System.out.println("senha: ");
    senha = sc.nextLine();
      
      for(User user: users){ 
        if(user.getLogin().equals(username)&& user.getPassword().equals(senha))
        {
          logado = true;
          encontrou = true;
          System.out.println("Bem vindo " + user.getName());
          break;
        }
      }        
        if(!logado){
          System.out.println("Login ou senha incorretos, tentativas restantes: "+(3-cont)+"\n") ;
        }
        if(cont==3){
          System.out.println("Bloqueado");
          break;
        }
        if(!encontrou){
            cont++;
        }
    }  
      
   return logado; 
}
public void listarUser(){
  if (users.isEmpty()) {
          System.out.println("Nenhum usuário cadastrado.");
          return;
      }
      
      for (User user : users){          
              System.out.println("--------------------------------");
              System.out.println("Nome: " + user.getName());
              System.out.println("Email: " + user.getEmail());
              System.out.println("CPF: " + user.getCpf());
              System.out.println("Cargo: "+ user.getCargo());
              System.out.println("Perfil: "+ user.getPerfil());
              System.out.println("--------------------------------");
          
      }

  }
public void removerUsuario(Scanner sc){
      
      
      if(users.isEmpty()){
          System.out.println("Nenhum usuário cadastrado.");
          return;
      }

      for(int i = 0; i < users.size(); i++){
          System.out.println(i + " = " + users.get(i).getName());
      }

      System.out.println("Digite o índice do usuário: ");
      int id = sc.nextInt();
      sc.nextLine();

      if(id < 0 || id >= users.size()){
          System.out.println("Índice inválido");
          return;
      }
      
      if(users.get(id).getPerfil() == PerfilUsuario.ADMINISTRADOR || users.get(id).getPerfil() == PerfilUsuario.GERENTE){
          System.out.println("Não é permitido remover o administrador e gerentes.");
          return;
      }
      
    String nome = users.get(id).getName();
    users.remove(id);
    repository.salvarUsuarios(users);
    System.out.println("Usuário "+ nome + " removido com sucesso!");
  }


public ArrayList<User> getUsers(){

  return users;
}
  
  
}
  
    

