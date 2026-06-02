package model;
import model.PerfilUsuario;

public class User {

    private String login;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private PerfilUsuario perfil;
    private String cargo;

    public String getCargo(){
        return cargo;
    }

    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public PerfilUsuario getPerfil(){
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil){
        this.perfil = perfil;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCpf(String cpf){
        this.cpf=cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}