/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.servidor.servico;

import br.edu.ifpb.pod.Usuario;
import br.edu.ifpb.pod.servidor.repositorio.Repositorio;
import br.edu.ifpb.pod.servidor.repositorio.RepositorioBDR;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 *
 * @author jose2
 */
public class servico {

    private Repositorio repositorioBDR;

    /**
     * Método salva mensagem no postgresSql
     *
     * @param mes
     * @return Retorna valor 1 se tiver sucesso caso contrario retona o valor 0
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws URISyntaxException
     */
    public int seveOnPostgres(Usuario mes) throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
        repositorioBDR = new RepositorioBDR(RepositorioBDR.conectionPostgres);

        if (validaMesangem(mes) && repositorioBDR.seve(mes)) {
            System.err.println("ento servico postgres");
            return 1;
        }
        return 0;
    }

    /**
     * Método salva mensagem no MaySql
     *
     * @param mes
     * @return Retorna valor 1 se tiver sucesso caso contrario retona o valor 0
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws URISyntaxException
     */
    public int seveOnMaySql(Usuario mes) throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
        System.err.println("abrindo conecão maySql");
     //   repositorioBDR = new RepositorioBDR(RepositorioBDR.conectionMaySql);
        if (validaMesangem(mes) && repositorioBDR.seve(mes)) {
            return 1;
        }
        return 0;
    }

    private boolean validaMesangem(Usuario mes) {
        if (mes == null) {
            System.err.println("mensagem não encontrado");
            return false;
        }
        return true;
    }

}
