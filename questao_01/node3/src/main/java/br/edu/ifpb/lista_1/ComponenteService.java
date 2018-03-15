/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.lista_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class ComponenteService {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            // esperando conexão
            ServerSocket service = new ServerSocket(10991);
            System.err.println("Node 3 ativo  ");
            Socket cliente = service.accept();

            //recebendo dados do servidor
            ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());

            //converte os dados do cliente em Operando
            Operando dadoCliente = (Operando) read.readObject();
            int x =dadoCliente.getValor1();
            int y =dadoCliente.getValor2();
            
            System.out.println("Sabendo que f(x, y) = x^y+y^x ");
            System.err.println("x = " + x + " y = " + y + " temos:");
            
            double resultado = Math.pow(dadoCliente.getValor1(), dadoCliente.getValor2())
                    + Math.pow(dadoCliente.getValor1(), dadoCliente.getValor2());
            DecimalFormat formato = new DecimalFormat("#.##"); 
            System.out.println("f(x,y) = " +  Double.valueOf(formato.format(resultado)));

            //fechando os recursos 
            read.close();
            service.close();
            cliente.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ComponenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
