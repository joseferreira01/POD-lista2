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
public class Service3 {

    public static void main(String[] args) {
        rum();
    }

    private static void rum() {
        try {
            // esperando conexão
            ServerSocket service3 = new ServerSocket(10993);
            //se conectando ao cliente
            System.err.println("servidor 3 ativo");
            Socket cliente = service3.accept();

            //recebendo dados do servidor
            ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());

            //converte os dados do cliente em Operando
            Operando operando = (Operando) read.readObject();
            if (operando.getOperacao() == 1) {
                String resutado = "Resolvido em node 3 \n Dados  x = " + operando.getValor1() + " e y = " + operando.getValor2()
                        + "\n A diferênça entre eles é igual a: " + (operando.getValor1() - operando.getValor2());
                ObjectOutputStream wirite = new ObjectOutputStream(cliente.getOutputStream());

                 wirite.writeUTF(resutado);
                wirite.flush();
                wirite.close();
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Service3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
