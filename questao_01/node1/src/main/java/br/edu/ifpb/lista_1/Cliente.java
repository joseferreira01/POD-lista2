/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.lista_1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author jose2
 */
public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        execute();

    }

    public static void execute() throws IOException {
        //gerando os números
        Random random = new Random();
        int x = random.nextInt(100) + 1;
        int y = random.nextInt(100) + 1;
        //atribuindo os aleatorios a um objetos chamando numeros 
        Operando n = new Operando(x, y);

        //conectando ao servidor
        Socket servidor = new Socket("host-node2", 1099);
        //mandando os números
        ObjectOutputStream write = new ObjectOutputStream(servidor.getOutputStream());
        write.writeObject(n);
        write.flush();

        //retorno do do servidor
        int resultado = servidor.getInputStream().read();
        if (resultado == 0) {
            System.out.println("Retorno do servidor:  "+ resultado );
        } 

        write.close();
        servidor.close();
    }
}
