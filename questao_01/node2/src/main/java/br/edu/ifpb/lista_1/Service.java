/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.lista_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class Service {
    public static void main(String[] args){
        try {
            rum();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private static void rum() throws IOException, ClassNotFoundException {
        // esperando conexão
        ServerSocket service = new ServerSocket(1099);
        //se conectando ao cliente
        System.err.println("Node 2 ativo");
        Socket cliente = service.accept();

          //recebendo dados do servidor
        ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());

         //converte os dados do cliente em Operando
        Operando operando = (Operando) read.readObject();

        if (operando.getValor1()== operando.getValor2()) {
            cliente.getOutputStream().write(0);
            System.out.println("Retorno 0.");
        } else {
            //conecdando com o serviço de resolver função f(x,y)
            Socket componenteService = new Socket("host-node3", 10991);
            // manda os dados  
            ObjectOutputStream wirite = new ObjectOutputStream(componenteService.getOutputStream());
            System.out.println("mandondo os dados ao serviço.");
            wirite.writeObject(operando);
            wirite.flush();
            //fechando conexão com serviço.
            wirite.close();
            componenteService.close();

        }

        //fechando as conexões node1
        read.close();
        service.close();
        cliente.close();

    }
}
