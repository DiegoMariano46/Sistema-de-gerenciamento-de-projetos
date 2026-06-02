package repository;

import java.io.*;
import java.util.ArrayList;
import model.User;
import model.PerfilUsuario;

public class UserRepository {

    public void salvarUsuarios(ArrayList<User> users){

        try(PrintWriter writer = new PrintWriter(new FileWriter("usuarios.txt"))){

            for(User user : users){
                writer.println(
                        user.getName() + ";" +
                                user.getLogin() + ";" +
                                user.getEmail() + ";" +
                                user.getPassword() + ";" +
                                user.getCpf() + ";" +
                                user.getPerfil() + ";" +
                                user.getCargo()
                );
            }

        }catch(IOException e){
            System.out.println("Erro ao salvar usuários.");
        }
    }

    public ArrayList<User> carregarUsuarios(){

        ArrayList<User> users = new ArrayList<>();

        File arquivo = new File("usuarios.txt");

        if(!arquivo.exists()){
            return users;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(arquivo))){

            String linha;

            while((linha = reader.readLine()) != null){

                String[] dados = linha.split(";");

                if(dados.length < 7){
                    continue;
                }

                User user = new User();

                user.setName(dados[0]);
                user.setLogin(dados[1]);
                user.setEmail(dados[2]);
                user.setPassword(dados[3]);
                user.setCpf(dados[4]);
                user.setPerfil(PerfilUsuario.valueOf(dados[5]));
                user.setCargo(dados[6]);

                users.add(user);
            }

        }catch(IOException e){
            System.out.println("Erro ao carregar usuários.");
        }

        return users;
    }
}