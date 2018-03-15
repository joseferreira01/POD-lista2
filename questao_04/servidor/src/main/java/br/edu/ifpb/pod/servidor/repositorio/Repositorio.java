/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.servidor.repositorio;

import br.edu.ifpb.pod.Usuario;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 *
 * @author jose2
 */
public interface Repositorio {

    boolean seve(Usuario mes) throws SQLException, IOException, ClassNotFoundException, URISyntaxException;
    
}
