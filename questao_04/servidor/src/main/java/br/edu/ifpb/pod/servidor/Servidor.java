/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.servidor;

import br.edu.ifpb.pod.Usuario;
import br.edu.ifpb.pod.servidor.servico.servico;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 *
 * @author jose2
 */
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, URISyntaxException {
        try ( // servidor ouvindo porta 1099
                ServerSocket servidor = new ServerSocket(1099)) {
            System.err.println("Servidor ativo!");
            while (true) {
                try (Socket cliente = servidor.accept(); ObjectInputStream read = new ObjectInputStream(cliente.getInputStream())) {
                    Usuario mensagem = (Usuario) read.readObject();
                    System.err.println("mensagem do " + mensagem.toString());
                    //salvando os dados no postgres
                    servico servico = new servico();
                    int postgres = servico.seveOnPostgres(mensagem);
//
if (postgres == 1) {
    cliente.getOutputStream().write(1);
    System.err.println("Sucesso ao salvar");
} else {
    System.err.println("Erro ao salvar");
}
                }
            }
            
        }
        

    }
}
