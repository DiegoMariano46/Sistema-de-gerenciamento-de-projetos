package model;
import java.util.ArrayList;


public class Equipe {

    private String nomeEquipe;
    private String descricao;
    
   

    private ArrayList<User> membros = new ArrayList<User>();

    public ArrayList<User> getMembros(){
        return membros;
    }

    public String getNomeEquipe(){
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe){
        this.nomeEquipe = nomeEquipe;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

   



}