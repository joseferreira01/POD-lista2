/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.servidor.conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexaoBDR implements Conexao {

    private Connection conn;
    private static Properties prop = null;

    public ConexaoBDR(String banco) throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
        try {

            prop = new Properties();
            prop.load(new FileInputStream(getClass().getResource(banco).toURI().getPath()));

            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String driver =prop.getProperty("driver");
// carrega as propriedades do arquivo connectionPostgres.properties da pasta resources / bd           
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }

    @Override
    public void closeAll(PreparedStatement stat) throws DataBaseException {
        try {
            stat.close();
            this.conn.close();

        } catch (SQLException e) {
            throw new DataBaseException("Falha ao fechar conexões: " + e.getMessage());
        }

    }

    @Override
    public void closeAll(Statement stat) throws DataBaseException {
        try {
            stat.close();
            this.conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("Falha ao fechar conexões: " + e.getMessage());
        }
    }

}
