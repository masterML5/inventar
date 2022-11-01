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
public class Podkategorija {

    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";

    public Podkategorija() {
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    ArrayList<String> getAll() throws SQLException {
        String sqlGetAll = "SELECT * FROM podkategorija WHERE aktivan AND vazeci";
        PreparedStatement pstAllPodkat = conSQL.prepareStatement(sqlGetAll);
        ResultSet rsAllPodKat = pstAllPodkat.executeQuery();
        ArrayList<String> podkat = new ArrayList();
        while (rsAllPodKat.next()) {
            podkat.add(rsAllPodKat.getString("naziv"));
        }

        return podkat;
    }

    ArrayList<String> getAllByCategory(int idKategorija) throws SQLException {
        String sqlByCategory = "SELECT * FROM podkategorija WHERE aktivan AND vazeci AND id_kategorija=" + idKategorija;
        PreparedStatement pstAllPodkat = conSQL.prepareStatement(sqlByCategory);
        ResultSet rsAllPodKat = pstAllPodkat.executeQuery();
        ArrayList<String> podkat = new ArrayList();
        while (rsAllPodKat.next()) {
            podkat.add(rsAllPodKat.getString("naziv"));
        }

        return podkat;
    }
    
    int getId(String naziv) throws SQLException{
        int id;
        String sqlId = "SELECT id_podkategorija FROM podkategorija WHERE naziv = '"+naziv+"'";
        PreparedStatement pstId = conSQL.prepareStatement(sqlId);
        ResultSet rsId = pstId.executeQuery();
        if(rsId.next()){
            id = rsId.getInt("id_podkategorija");
        }else{
            id=0;
        }
        
        return id;
    }

    ArrayList<String> getAllByCategory(String kategorija) throws SQLException {
        String sqlByCategory = "SELECT * FROM podkategorija as pk JOIN kategorija as kat WHERE kat.id_kategorija = pk.id_kategorija AND pk.aktivan AND pk.vazeci AND kat.naziv='" + kategorija+"'";
        PreparedStatement pstAllPodkat = conSQL.prepareStatement(sqlByCategory);
        ResultSet rsAllPodKat = pstAllPodkat.executeQuery();
        ArrayList<String> podkat = new ArrayList();
        while (rsAllPodKat.next()) {
            podkat.add(rsAllPodKat.getString("naziv"));
        }

        return podkat;
    
    }

    String getOne(int idPodkategorija) throws SQLException {
        String sqlGetOne = "SELECT * FROM podkategorija WHERE aktivan AND vazeci AND id_podkategorija=" + idPodkategorija;
        PreparedStatement pstPodkat = conSQL.prepareStatement(sqlGetOne);
        ResultSet rsPodkat = pstPodkat.executeQuery();
        String naziv = null;
        if(rsPodkat.next()){
            
         naziv = rsPodkat.getString("naziv");
        
        }else{
            naziv = "Nema kategorija sa tim id-jem";
        }
        return naziv;
    }

    boolean add(int idKategorija, String naziv, String uneo, String datum) throws SQLException {
        String sqlAdd = "INSERT INTO podkategorija (id_kategorija, naziv, uneo, datum) VALUES (?,?,?,?)";
        PreparedStatement pstInsertPodkategorija = conSQL.prepareStatement(sqlAdd);
        pstInsertPodkategorija.setInt(1, idKategorija);
        pstInsertPodkategorija.setString(2, naziv);
        pstInsertPodkategorija.setString(3, uneo);
        pstInsertPodkategorija.setString(4, datum);
        pstInsertPodkategorija.addBatch();
        int i = pstInsertPodkategorija.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean edit(int idPodkategorija, int idKategorija, String naziv, String editovao) throws SQLException {
        String sqlEdit = "UPDATE podkategorija SET id_kategorija=" + idKategorija + ",naziv='" + naziv + "',editovao='" + editovao + "' WHERE aktivan AND vazeci AND id_podkategorija=" + idPodkategorija;
        PreparedStatement pstUpdatePodkategorija = conSQL.prepareStatement(sqlEdit);
        int i = pstUpdatePodkategorija.executeUpdate();
        conSQL.commit();
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    boolean delete(int idPodkategorija) throws SQLException {
        String sqlDelete = "DELETE FROM podkategorija WHERE aktivan AND vazeci AND id_podkategorija" + idPodkategorija;
        PreparedStatement pstDelete = conSQL.prepareStatement(sqlDelete);
        int i = pstDelete.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean delete(String naziv) throws SQLException {
        String sqlDelete = "DELETE FROM podkategorija WHERE aktivan AND vazeci AND naziv" + naziv;
        PreparedStatement pstDelete = conSQL.prepareStatement(sqlDelete);
        int i = pstDelete.executeUpdate();
        conSQL.commit();
        return i > 0;
    }
    
    

}
