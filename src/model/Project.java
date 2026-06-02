package model;
import java.util.ArrayList;


public class Project {

    private String nomeProjeto;
    private String descricao;
    private String dataInicio;
    private String dataFimPrevista;
    private StatusProject status;
    private User gerenteResponsavel;

    ArrayList<User> colaboradores = new ArrayList<User>();
    ArrayList<Equipe> equipes = new ArrayList<Equipe>();
    

    public ArrayList<User> getColaboradores(){
        return colaboradores;
    }
    
    public ArrayList <Equipe> getEquipes(){
        return equipes;
    }

    public String getNomeProjeto(){
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto){
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getDataInicio(){
        return dataInicio;
    }

    public void setDataInicio(String dataInicio){
        this.dataInicio = dataInicio;
    }

    public String getDataFimPrevista(){
        return dataFimPrevista;
    }

    public void setDataFimPrevista(String dataFimPrevista){
        this.dataFimPrevista = dataFimPrevista;
    }

    public StatusProject getStatus(){
        return status;
    }

    public void setStatus(StatusProject status){
        this.status = status;
    }

    public User getGerenteResponsavel(){
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(User gerenteResponsavel){
        this.gerenteResponsavel = gerenteResponsavel;
    }

        

}