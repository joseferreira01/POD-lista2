/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.lista_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class Cliente {

    private static Operando operando;
    private static String result;
    private static ObjectInputStream resultado;

    public static void main(String[] args) {

        try {
            rum();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void rum() throws IOException {
        ObjectOutputStream output = null;
        Socket node1 = null;
        Socket node2 = null;
        Socket node3 = null;

        try {
            //abrindo comunicação 

            Random r = new Random();
            int op = r.nextInt(3) + 1;
            lerDado();
            if (operando == null) {
                System.err.println("Erro ao ler os dados");
            }

            //enviando o objeto
            

            System.err.println("opção " + op);
            switch (op) {
                case 1:
                    node1 = new Socket("localhost", 10991);
                    output = new ObjectOutputStream(node1.getOutputStream());
                    output.writeObject(operando);
                    output.flush();

                    resultado = new ObjectInputStream(node1.getInputStream());

                    System.out.println(resultado.readUTF());
                    node1.close();
                    break;
                case 2:
                    node2 = new Socket("localhost", 10992);
                    output = new ObjectOutputStream(node2.getOutputStream());
                    output.writeObject(operando);
                    output.flush();
                    resultado = new ObjectInputStream(node2.getInputStream());
                    System.out.println(resultado.readUTF());
                    node2.close();
                    break;
                case 3:
                    node3 = new Socket("localhost", 10993);
                    output = new ObjectOutputStream(node3.getOutputStream());
                    output.writeObject(operando);
                    output.flush();
                    resultado = new ObjectInputStream(node3.getInputStream());
                    System.out.println(resultado.readUTF());
                    node3.close();
                    break;

            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void lerDado() {
        int x, y, op;
        Random r = new Random();
        x = r.nextInt(100) + 1;
        y = r.nextInt(100) + 1;
        op = r.nextInt(2) + 1;
        System.err.println("valores gerados x " + x + " y " + y + "  opca dados" + op);
        operando = new Operando(x, y, op);

    }

}
