/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import br.edu.ifpb.pod.sharedq4.Numero;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jose2
 */
public class Node4Servidor {

    private static ServerSocket servidor = null;
    private static Socket cliente = null;
    private static ObjectInputStream input4 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta 
        servidor = new ServerSocket(1234);

        while (true) {
            //se conectando ao cliente (node1)
            System.out.println("Node4 esperando cliente...");
            cliente = servidor.accept();

            //Recebendo o objeto
            input4 = new ObjectInputStream(cliente.getInputStream());
            Numero numero = (Numero) input4.readObject();
            if (numero.getId() == 1) {
                System.out.println("Sum(x,y): x=" + numero.getX() + " y=" + numero.getY());
                int soma = numero.getX() + numero.getY();
                System.out.println("É igua a: " + soma);

                cliente.getOutputStream().write(soma);
                System.out.println("Retornando resposta...");
            } else if (numero.getId() == 2) {
                System.out.println("Diff(x,y): x=" + numero.getX() + " y=" + numero.getY());
                int diff = numero.getX() - numero.getY();
                System.out.println("É igua a: " + diff);
                Integer d = new Integer (diff);
                cliente.getOutputStream().write(d.byteValue());

                System.out.println("Enviando resposta...");
            }
            input4.close();
            cliente.close();

        }

    }
}
