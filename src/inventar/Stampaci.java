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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author milosjelic
 */
public class Stampaci {
    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";
    public Stampaci(){
        
      try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }
    
    
    String getId(String modelStampac, String markaStampac) throws SQLException{
        String id = null;
        String sqlIdStampac = "SELECT id_stampaci from stampaci WHERE model ='"+modelStampac+"' AND marka = '"+markaStampac+"'";
        PreparedStatement pstIdStampac = conSQL.prepareStatement(sqlIdStampac);
        ResultSet rsIdStampac = pstIdStampac.executeQuery();
       
        if(rsIdStampac.next()){
            
         id = rsIdStampac.getString("id_stampaci");
        
        }else{
            id = "0";
        }
        return id; 
    }
    
    ArrayList<String> getAll() throws SQLException {
        String sqlAllStampaci = "SELECT model, marka FROM stampaci";
        PreparedStatement pstAllStampaci = conSQL.prepareStatement(sqlAllStampaci);
        ResultSet rsAllStampaci = pstAllStampaci.executeQuery();
        ArrayList<String> stampaci = new ArrayList();
        while (rsAllStampaci.next()) {
            stampaci.add(rsAllStampaci.getString("marka") + " "+ rsAllStampaci.getString("model"));
        }

        return stampaci;

    }
    
    
     boolean add(int idLokacija, int invBroj, String model, String marka, String toner, String vrsta, String mrezni, String ipAdresa, String lokacija, String uneo, String datum) throws SQLException {
        String sqlAddKategorija = "INSERT INTO stampaci (id_lokacija,inv_broj,model,marka,toner,vrsta,mrezni,ip_adresa,lokacijaKorisnik,uneo,datum) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstInsertStampac = conSQL.prepareStatement(sqlAddKategorija);
        pstInsertStampac.setInt(1, idLokacija);
        pstInsertStampac.setInt(2, invBroj);
        pstInsertStampac.setString(3, model);
        pstInsertStampac.setString(4, marka);
        pstInsertStampac.setString(5, toner);
        pstInsertStampac.setString(6, vrsta);
        pstInsertStampac.setString(7, mrezni);
        pstInsertStampac.setString(8, ipAdresa);
        pstInsertStampac.setString(9, lokacija);
        pstInsertStampac.setString(10, uneo);
        pstInsertStampac.setString(11, datum);

        pstInsertStampac.addBatch();
        int i = pstInsertStampac.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean edit(int idStampac,int idLokacija, int invBroj, String model, String marka, String toner, String vrsta, String mrezni, String ipAdresa, String lokacija, String uneo, String datum) throws SQLException {
        String sqlEditStampaci = 
                "UPDATE stampaci SET id_lokacija="+idLokacija+",inv_broj = "+invBroj+",model = '"+model+"',"
                + "marka = '"+marka+"',toner = '"+toner+"'"
                + ",vrsta = '"+vrsta+"',mrezni = '"+mrezni+"',"
                + "ip_adresa = '"+ipAdresa+"',lokacija = '"+lokacija+"',"
                + "uneo = '"+uneo+"',datum = '"+datum+"' WHERE id_stampaci="+idStampac;
        
        PreparedStatement pstUpdateStampaci = conSQL.prepareStatement(sqlEditStampaci);
        int i = pstUpdateStampaci.executeUpdate();
        conSQL.commit();
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }
    
    

    boolean delete(int idStampac) throws SQLException {
        String sqlDeleteStampaci = "DELETE FROM stampaci WHERE id_stampaci ="+idStampac;
         PreparedStatement pstDeleteStampaci = conSQL.prepareStatement(sqlDeleteStampaci);
        int i = pstDeleteStampaci.executeUpdate();
        conSQL.commit();
        return i > 0;
    }
    
     boolean delete(String model, String marka) throws SQLException {
        String sqlDeleteStampaci = "DELETE FROM stampaci WHERE marka ='"+marka+"' AND model = '"+model+"'";
         PreparedStatement pstDeleteStampaci = conSQL.prepareStatement(sqlDeleteStampaci);
        int i = pstDeleteStampaci.executeUpdate();
        conSQL.commit();
        return i > 0;
    }
     
     
     String getModel(int idStampac) throws SQLException{
         String sqlModelStampaci = "SELECT model FROM stampaci WHERE id_stampaci ="+idStampac;
         PreparedStatement pstModel = conSQL.prepareStatement(sqlModelStampaci);
         String model;
         ResultSet rsIdStampac = pstModel.executeQuery();
       
        if(rsIdStampac.next()){
            
         model = rsIdStampac.getString("model");
        
        }else{
            model = null;
        }
        return model; 
     }
      String getMarka(int idStampac) throws SQLException{
         String sqlModelStampaci = "SELECT marka FROM stampaci WHERE id_stampaci ="+idStampac;
         PreparedStatement pstMarka = conSQL.prepareStatement(sqlModelStampaci);
         String marka;
         ResultSet rsIdStampac = pstMarka.executeQuery();
       
        if(rsIdStampac.next()){
            
         marka = rsIdStampac.getString("marka");
        
        }else{
            marka = null;
        }
        return marka; 
     }
      
      String getLokacija(int idStampac) throws SQLException{
         String sqlLokacijaStampaci = "SELECT naziv FROM lokacija as lok JOIN stampaci as stmp WHERE lok.id_lokacija = stmp.id_lokacija AND stmp.id_stampaci ="+idStampac;
         PreparedStatement pstLokacija = conSQL.prepareStatement(sqlLokacijaStampaci);
         String lokacija;
         ResultSet rsIdStampac = pstLokacija.executeQuery();
       
        if(rsIdStampac.next()){
            
         lokacija = rsIdStampac.getString("naziv");
        
        }else{
            lokacija = "Nije uneta lokacija za stampac!";
        }
        return lokacija; 
     }
      
      String getToner(int idStampac) throws SQLException{
          String sqlTonerStampaci = "SELECT toner FROM stampaci WHERE id_stampaci ="+idStampac;
          PreparedStatement pstToner = conSQL.prepareStatement(sqlTonerStampaci);
          String toner;
          ResultSet rsTonerStampac = pstToner.executeQuery();
          if(rsTonerStampac.next()){
              toner = rsTonerStampac.getString("toner");
          }else{
              toner = "Stampac nema unesen toner!";
          }
          
          return toner;        
      }
      
      String getVrsta(int idStampac) throws SQLException{
          String sqlVrstaStampaci = "SELECT vrsta FROM stampaci WHERE id_stampaci ="+idStampac;
          PreparedStatement pstVrsta = conSQL.prepareStatement(sqlVrstaStampaci);
          String vrsta;
          ResultSet rsTonerStampac = pstVrsta.executeQuery();
          if(rsTonerStampac.next()){
              vrsta = rsTonerStampac.getString("vrsta");
          }else{
              vrsta = "Stampac nema unesenu vrstu!";
          }
          
          return vrsta;        
      }
      
      
      String getIpAdresa(int idStampac) throws SQLException{
          String sqlIpAdresaStampaci = "SELECT ip_adresa FROM stampaci WHERE id_stampaci="+idStampac;
          PreparedStatement pstIpAdresa = conSQL.prepareStatement(sqlIpAdresaStampaci);
          String ipAdresa;
          ResultSet rsTonerStampac = pstIpAdresa.executeQuery();
          if(rsTonerStampac.next()){
              ipAdresa = rsTonerStampac.getString("ip_adresa");
          }else{
              ipAdresa = "Stampac nema unesenu ip adresu!";
          }
          
          return ipAdresa;   
      }
    
      String getLokacijaDetail (int idStampac) throws SQLException{
          String sqlLokacijaDetailStampaci = "SELECT stmp.lokacijaKorisnik, lok.naziv FROM stampaci AS stmp "
                  + " JOIN lokacija as lok WHERE stmp.id_stampaci = "+idStampac+" AND stmp.id_lokacija = lok.id_lokacija";
          PreparedStatement pstLokacijaDetail = conSQL.prepareStatement(sqlLokacijaDetailStampaci);
          String lokacijaDetail;
          ResultSet rsLokacijaDetailStampac = pstLokacijaDetail.executeQuery();
          if(rsLokacijaDetailStampac.next()){
              lokacijaDetail = rsLokacijaDetailStampac.getString("stmp.lokacijaKorisnik") + " " + rsLokacijaDetailStampac.getString("lok.naziv");
          }else{
              lokacijaDetail = "Stampac nema unesenu ip adresu!";
          }
          
          return lokacijaDetail;   
      }
      
      ArrayList<String> getAllInfo(int idStampac) throws SQLException{
        String sqlAllStampaci = "SELECT * FROM stampaci WHERE id_stampaci="+idStampac;
        PreparedStatement pstAllStampaci = conSQL.prepareStatement(sqlAllStampaci);
        ResultSet rsAllStampaci = pstAllStampaci.executeQuery();
        ArrayList<String> stampaci = new ArrayList();
        ResultSetMetaData rsmd = rsAllStampaci.getMetaData(); 
        int columnCount = rsmd.getColumnCount();
        while(rsAllStampaci.next()){
            int i = 1;
                while(i <= columnCount) {
                     stampaci.add(rsAllStampaci.getString(i++));
   }
        }

        return stampaci;

      }
      
}
