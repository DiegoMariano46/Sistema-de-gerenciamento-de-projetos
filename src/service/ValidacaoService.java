package service;

public class ValidacaoService{


public boolean cpfValido(String cpf){

    return cpf !=null && cpf.matches("\\d{11}");
  
}

public boolean emailValido(String email){
    
    return email !=null && email.contains("@") && email.contains(".");
    
}

public boolean nomeValido(String nome){
    return nome != null && nome.matches("[A-Za-zÀ-ÿ\\s]+");
}

  
}