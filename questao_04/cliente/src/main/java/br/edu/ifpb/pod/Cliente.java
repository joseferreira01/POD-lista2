/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class Cliente {

    private  Usuario usuario;
    private static Socket servidor;
    private static ObjectOutputStream write;

    public static boolean execulte(Usuario usuario) {
        boolean resultado = false;
        try {
            System.err.println("cliente");
            // fazento conexão com servidor
            servidor = new Socket("127.0.0.1", 1099);
            System.err.println("conector");
            
            write = new ObjectOutputStream(servidor.getOutputStream());
            // enviando a mensagem ao servidor
            write.writeObject(usuario);
            write.flush();
            System.err.println("Mensagem enviada!");

            if (servidor.getInputStream().read() > 0) {
                resultado = true;
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                write.close();
                servidor.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
       // Scanner lerDados = new Scanner(System.in);
       //  System.err.println("Informe mensagem");
        for (int i = 0; i < 1000; i++) {
            
        
         Usuario u = new Usuario("fernanda");
        if (execulte(u)) {
            System.err.println("Operação realizada com sucesso!");
        } else {
            System.err.println("Erro");
        }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.err.println("tempo de execuçao "+elapsed/1000);
        System.err.println("Fim do progama!");
    }
}
