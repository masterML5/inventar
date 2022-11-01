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
 * @author milosjelic
 */
public class Lokacija {
    
        private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";

    public Lokacija() {
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    boolean add(String lokacija) throws SQLException {
        String sqlAddlokacija = "INSERT INTO lokacija (naziv) VALUES (?)";
        PreparedStatement pstInsertlokacija = conSQL.prepareStatement(sqlAddlokacija);
        pstInsertlokacija.setString(1, lokacija);
        pstInsertlokacija.addBatch();
        int i = pstInsertlokacija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean edit(int idlokacija,String noviNaziv) throws SQLException {
        String sqlEditlokacija = "UPDATE lokacija SET naziv='"+noviNaziv+"' WHERE id_lokacija="+idlokacija;
        PreparedStatement pstUpdatelokacija = conSQL.prepareStatement(sqlEditlokacija);
        int i = pstUpdatelokacija.executeUpdate();
        conSQL.commit();
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }
    
    boolean edit(String stariNaziv, String noviNaziv) throws SQLException{
        String sqlEditlokacija = "UPDATE lokacija SET naziv='"+noviNaziv+"' WHERE naziv='"+stariNaziv+"'";
        PreparedStatement pstUpdatelokacija = conSQL.prepareStatement(sqlEditlokacija);
        int i = pstUpdatelokacija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean delete(int idlokacija) throws SQLException {
        String sqlDeletelokacija = "DELETE FROM lokacija WHERE id_lokacija ="+idlokacija;
         PreparedStatement pstDeletelokacija = conSQL.prepareStatement(sqlDeletelokacija);
        int i = pstDeletelokacija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }
    
     boolean delete(String lokacija) throws SQLException {
        String sqlDeletelokacija = "DELETE FROM lokacija WHERE naziv ='"+lokacija+"'";
         PreparedStatement pstDeletelokacija = conSQL.prepareStatement(sqlDeletelokacija);
        int i = pstDeletelokacija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    ArrayList<String> getAll() throws SQLException {
        String sqlAlllokacija = "SELECT * FROM lokacija";
        PreparedStatement pstAlllokacija = conSQL.prepareStatement(sqlAlllokacija);
        ResultSet rsAlllokacija = pstAlllokacija.executeQuery();
        ArrayList<String> lokacija = new ArrayList();
        while (rsAlllokacija.next()) {
            lokacija.add(rsAlllokacija.getString("naziv"));
        }

        return lokacija;

    }
    
    String getId(String nazivlokacija) throws SQLException{
        String sqlIdlokacija = "SELECT id_lokacija FROM lokacija WHERE naziv ='"+nazivlokacija+"'";
        PreparedStatement pstIdlokacija = conSQL.prepareStatement(sqlIdlokacija);
        ResultSet rsIdlokacija = pstIdlokacija.executeQuery();
        String id = null;
        if(rsIdlokacija.next()){
            
         id = rsIdlokacija.getString("id_lokacija");
        
        }else{
            id = "0";
        }
        return id;
    }
    String getlokacija(int idlokacija) throws SQLException{
        String sqllokacija = "SELECT naziv FROM lokacija WHERE id_lokacija ="+idlokacija;
        PreparedStatement pstlokacija = conSQL.prepareStatement(sqllokacija);
        ResultSet rslokacija = pstlokacija.executeQuery();
        String naziv;
        if(rslokacija.next()){
            naziv = rslokacija.getString("naziv");
        }else{
            naziv = null;
        }
        return naziv;
    }


    
    
    
}
