/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.servidor.repositorio;

import br.edu.ifpb.pod.Usuario;
import br.edu.ifpb.pod.servidor.conexao.ConexaoBDR;
import br.edu.ifpb.pod.servidor.conexao.DataBaseException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.edu.ifpb.pod.servidor.conexao.Conexao;

/**
 *
 * @author jose2
 */
public class RepositorioBDR implements Repositorio {

    private Conexao conn;
    /**
     * Atributo identifica qual banco deve conectar-se
     */
    private String banco;
    public static final String conectionPostgres = "/bd/contionPostgres.properties";
    //public static final String conectionMaySql = "/bd/conectionMaySql.properties";

    public RepositorioBDR(String banco) {
        this.banco = banco;
    }

    @Override
    public boolean seve(Usuario usuario) throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
        boolean result =false;
        System.err.println("------------------------------->");
        String query = "INSERT INTO tb_user(nome) VALUES(?)";
        this.conn = new ConexaoBDR(banco);
        PreparedStatement stat = conn.getConnection().prepareStatement(query);
        stat.setString(1, usuario.getNome());
        if (stat.executeUpdate() > 0) {
            System.err.println("metodo r");
            conn.closeAll(stat);
            result = true;
        }
        conn.closeAll(stat);
        return result;
    }

}
