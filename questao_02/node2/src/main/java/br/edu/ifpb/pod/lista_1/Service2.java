/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.lista_1;

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
public class Service2 {
    public static void main(String[] args) {
        rum();
    }

    private static void rum() {
        try {
            // esperando conexão
            ServerSocket service1 = new ServerSocket(10992);
            //se conectando ao cliente
            System.err.println("servidor 2 ativo");
            Socket cliente = service1.accept();
            
            //recebendo dados do servidor
            ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());
            
            //converte os dados do cliente em Operando
            Operando operando = (Operando) read.readObject();
            if(operando.getOperacao()==1){
                String resutado = "Resolvido em node 2 \n dados  x = " + operando.getValor1() + " e y = " + operando.getValor2() +
                        "\n A soma entre eles é igual a: " + (operando.getValor1() + operando.getValor2());
                ObjectOutputStream wirite = new ObjectOutputStream(cliente.getOutputStream());
            
                 wirite.writeUTF(resutado);
                 wirite.flush();
                 wirite.close();
            }
            
             Socket node3 = new Socket("127.0.0.1", 10993);
            // manda os dados  
            ObjectOutputStream wirite = new ObjectOutputStream(node3.getOutputStream());
            System.out.println("mandondo os dados ao node 3.");
            wirite.writeObject(operando);
            wirite.flush();
            //fechando conexão com serviço.
            wirite.close();
            node3.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Service2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
