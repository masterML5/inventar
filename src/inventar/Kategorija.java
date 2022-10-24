/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Milos
 */
public class Kategorija {

    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";

    public Kategorija() {
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    boolean add(String kategorija) throws SQLException {
        String sqlAddKategorija = "INSERT INTO kategorija (naziv) VALUES (?)";
        PreparedStatement pstInsertKategorija = conSQL.prepareStatement(sqlAddKategorija);
        pstInsertKategorija.setString(1, kategorija);
        pstInsertKategorija.addBatch();
        int i = pstInsertKategorija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean edit(int idKategorija,String noviNaziv) throws SQLException {
        String sqlEditKategorija = "UPDATE kategorija SET naziv='"+noviNaziv+"' WHERE id_kategorija="+idKategorija;
        PreparedStatement pstUpdateKategorija = conSQL.prepareStatement(sqlEditKategorija);
        int i = pstUpdateKategorija.executeUpdate();
        conSQL.commit();
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }
    
    boolean edit(String stariNaziv, String noviNaziv) throws SQLException{
        String sqlEditKategorija = "UPDATE kategorija SET naziv='"+noviNaziv+"' WHERE naziv='"+stariNaziv+"'";
        PreparedStatement pstUpdateKategorija = conSQL.prepareStatement(sqlEditKategorija);
        int i = pstUpdateKategorija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean delete(int idKategorija) throws SQLException {
        String sqlDeleteKategorija = "DELETE FROM kategorija WHERE id_kategorija ="+idKategorija;
         PreparedStatement pstDeleteKategorija = conSQL.prepareStatement(sqlDeleteKategorija);
        int i = pstDeleteKategorija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }
    
     boolean delete(String kategorija) throws SQLException {
        String sqlDeleteKategorija = "DELETE FROM kategorija WHERE id_kategorija ='"+kategorija+"'";
         PreparedStatement pstDeleteKategorija = conSQL.prepareStatement(sqlDeleteKategorija);
        int i = pstDeleteKategorija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    ArrayList<String> getAll() throws SQLException {
        String sqlAllKategorije = "SELECT * FROM kategorija";
        PreparedStatement pstAllKategorije = conSQL.prepareStatement(sqlAllKategorije);
        ResultSet rsAllKategorije = pstAllKategorije.executeQuery();
        ArrayList<String> kategorije = new ArrayList();
        while (rsAllKategorije.next()) {
            kategorije.add(rsAllKategorije.getString("naziv"));
        }

        return kategorije;

    }
    
    String getId(String nazivKategorije) throws SQLException{
        String sqlIdKategorija = "SELECT id_kategorija FROM kategorija WHERE naziv ='"+nazivKategorije+"'";
        PreparedStatement pstIdKategorija = conSQL.prepareStatement(sqlIdKategorija);
        ResultSet rsIdKategorija = pstIdKategorija.executeQuery();
        String id = null;
        if(rsIdKategorija.next()){
            
         id = rsIdKategorija.getString("id_kategorija");
        
        }else{
            id = "0";
        }
        return id;
    }
    String getKategorija(int idKategorije) throws SQLException{
        String sqlKategorija = "SELECT naziv FROM kategorija WHERE id ="+idKategorije;
        PreparedStatement pstKategorija = conSQL.prepareStatement(sqlKategorija);
        ResultSet rsKategorija = pstKategorija.executeQuery();
        String naziv;
        if(rsKategorija.next()){
            naziv = rsKategorija.getString("naziv");
        }else{
            naziv = null;
        }
        return naziv;
    }

}
