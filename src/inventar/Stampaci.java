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


/**
 *
 * @author milosjelic
 */
public class Stampaci {

    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";
    private static String BULLET_SEPARATOR = " • ";

    public Stampaci() {

        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    String getId(String modelStampac, String markaStampac) throws SQLException {
        String id = null;
        String sqlIdStampac = "SELECT id_stampaci from stampaci WHERE aktivan AND vazeci AND model ='" + modelStampac + "' AND marka = '" + markaStampac + "'";
        PreparedStatement pstIdStampac = conSQL.prepareStatement(sqlIdStampac);
        ResultSet rsIdStampac = pstIdStampac.executeQuery();

        if (rsIdStampac.next()) {

            id = rsIdStampac.getString("id_stampaci");

        } else {
            id = "Nije pronadjen stampac";
        }
        return id;
    }

    String getId(int inventarskiBroj) throws SQLException {
        String id = null;
        String sqlIdStampac = "SELECT id_stampaci from stampaci WHERE aktivan AND vazeci AND  inv_broj=" + inventarskiBroj;
        PreparedStatement pstIdStampac = conSQL.prepareStatement(sqlIdStampac);
        ResultSet rsIdStampac = pstIdStampac.executeQuery();

        if (rsIdStampac.next()) {

            id = rsIdStampac.getString("id_stampaci");

        } else {
            id = "Nije pronadjen stampac";
        }
        return id;
    }
    
    
    
    ArrayList<String> getAllIds()  throws  SQLException{
        String getAllIds = "SELECT id_stampaci FROM stampaci WHERE  aktivan AND vazeci";
        PreparedStatement pstAllStampaci = conSQL.prepareStatement(getAllIds);
        ResultSet rsAllStampaci = pstAllStampaci.executeQuery();
        ArrayList<String> stampaci = new ArrayList();
        while (rsAllStampaci.next()) {
            stampaci.add(rsAllStampaci.getString("marka") + " " + rsAllStampaci.getString("model"));
        }

        return stampaci;
    }

    ArrayList<String> getAll() throws SQLException {
        String sqlAllStampaci = "SELECT stmp.model, stmp.marka, stmp.inv_broj, lok.naziv FROM stampaci as stmp JOIN lokacija as lok WHERE stmp.aktivan AND stmp.vazeci AND  stmp.id_lokacija = lok.id_lokacija";
        PreparedStatement pstAllStampaci = conSQL.prepareStatement(sqlAllStampaci);
        ResultSet rsAllStampaci = pstAllStampaci.executeQuery();
        ArrayList<String> stampaci = new ArrayList();
        while (rsAllStampaci.next()) {
            stampaci.add(rsAllStampaci.getString("stmp.inv_broj")+BULLET_SEPARATOR+rsAllStampaci.getString("stmp.marka") + " " + rsAllStampaci.getString("stmp.model") + BULLET_SEPARATOR+rsAllStampaci.getString("lok.naziv"));
        }

        return stampaci;

    }

    boolean add(int idKategorija, int idLokacija, int invBroj, String model, String marka, String toner, String vrsta, String mrezni, String ipAdresa, String lokacija, String uneo, String datum) throws SQLException {
        String sqlAddStampac = "INSERT INTO stampaci (id_kategorija,id_lokacija,inv_broj,model,marka,toner,vrsta,mrezni,ip_adresa,lokacijaKorisnik,uneo,datum) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstInsertStampac = conSQL.prepareStatement(sqlAddStampac);
        pstInsertStampac.setInt(1, idKategorija);
        pstInsertStampac.setInt(2, idLokacija);
        pstInsertStampac.setInt(3, invBroj);
        pstInsertStampac.setString(4, model);
        pstInsertStampac.setString(5, marka);
        pstInsertStampac.setString(6, toner);
        pstInsertStampac.setString(7, vrsta);
        pstInsertStampac.setString(8, mrezni);
        pstInsertStampac.setString(9, ipAdresa);
        pstInsertStampac.setString(10, lokacija);
        pstInsertStampac.setString(11, uneo);
        pstInsertStampac.setString(12, datum);

        pstInsertStampac.addBatch();
        int i = pstInsertStampac.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean edit(int idStampac, int idLokacija, int invBroj, String model, String marka, String toner, String vrsta, String mrezni, String ipAdresa, String lokacija, String editovao, String datum) throws SQLException {
        String sqlEditStampaci
                = "UPDATE stampaci SET id_lokacija=" + idLokacija + ",inv_broj = " + invBroj + ",model = '" + model + "',"
                + "marka = '" + marka + "',toner = '" + toner + "'"
                + ",vrsta = '" + vrsta + "',mrezni = '" + mrezni + "',"
                + "ip_adresa = '" + ipAdresa + "',lokacija = '" + lokacija + "',"
                + "editovao = '" + editovao + "',datum = '" + datum + "' WHERE id_stampaci=" + idStampac;

        PreparedStatement pstUpdateStampaci = conSQL.prepareStatement(sqlEditStampaci);
        int i = pstUpdateStampaci.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean delete(int idStampac) throws SQLException {
        String sqlDeleteStampaci = "DELETE FROM stampaci WHERE  aktivan AND vazeci AND id_stampaci =" + idStampac;
        PreparedStatement pstDeleteStampaci = conSQL.prepareStatement(sqlDeleteStampaci);
        int i = pstDeleteStampaci.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean delete(String model, String marka) throws SQLException {
        String sqlDeleteStampaci = "DELETE FROM stampaci WHERE  aktivan AND vazeci AND marka ='" + marka + "' AND model = '" + model + "'";
        PreparedStatement pstDeleteStampaci = conSQL.prepareStatement(sqlDeleteStampaci);
        int i = pstDeleteStampaci.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    String getModel(int idStampac) throws SQLException {
        String sqlModelStampaci = "SELECT model FROM stampaci WHERE  aktivan AND vazeci AND id_stampaci =" + idStampac;
        PreparedStatement pstModel = conSQL.prepareStatement(sqlModelStampaci);
        String model;
        ResultSet rsIdStampac = pstModel.executeQuery();

        if (rsIdStampac.next()) {

            model = rsIdStampac.getString("model");

        } else {
            model = null;
        }
        return model;
    }

    String getMarka(int idStampac) throws SQLException {
        String sqlModelStampaci = "SELECT marka FROM stampaci WHERE  aktivan AND vazeci AND id_stampaci =" + idStampac;
        PreparedStatement pstMarka = conSQL.prepareStatement(sqlModelStampaci);
        String marka;
        ResultSet rsIdStampac = pstMarka.executeQuery();

        if (rsIdStampac.next()) {

            marka = rsIdStampac.getString("marka");

        } else {
            marka = null;
        }
        return marka;
    }

    String getLokacija(int idStampac) throws SQLException {
        String sqlLokacijaStampaci = "SELECT naziv FROM lokacija as lok JOIN stampaci as stmp WHERE lok.aktivan AND lok.vazeci AND  lok.id_lokacija = stmp.id_lokacija AND stmp.id_stampaci =" + idStampac;
        PreparedStatement pstLokacija = conSQL.prepareStatement(sqlLokacijaStampaci);
        String lokacija;
        ResultSet rsIdStampac = pstLokacija.executeQuery();

        if (rsIdStampac.next()) {

            lokacija = rsIdStampac.getString("naziv");

        } else {
            lokacija = "Nije uneta lokacija za stampac!";
        }
        return lokacija;
    }

    String getToner(int idStampac) throws SQLException {
        String sqlTonerStampaci = "SELECT toner FROM stampaci WHERE aktivan AND vazeci AND  id_stampaci =" + idStampac;
        PreparedStatement pstToner = conSQL.prepareStatement(sqlTonerStampaci);
        String toner;
        ResultSet rsTonerStampac = pstToner.executeQuery();
        if (rsTonerStampac.next()) {
            toner = rsTonerStampac.getString("toner");
        } else {
            toner = "Stampac nema unesen toner!";
        }

        return toner;
    }

    String getVrsta(int idStampac) throws SQLException {
        String sqlVrstaStampaci = "SELECT vrsta FROM stampaci WHERE aktivan AND vazeci AND  id_stampaci =" + idStampac;
        PreparedStatement pstVrsta = conSQL.prepareStatement(sqlVrstaStampaci);
        String vrsta;
        ResultSet rsTonerStampac = pstVrsta.executeQuery();
        if (rsTonerStampac.next()) {
            vrsta = rsTonerStampac.getString("vrsta");
        } else {
            vrsta = "Stampac nema unesenu vrstu!";
        }

        return vrsta;
    }

    String getIpAdresa(int idStampac) throws SQLException {
        String sqlIpAdresaStampaci = "SELECT ip_adresa FROM stampaci WHERE aktivan AND vazeci AND  id_stampaci=" + idStampac;
        PreparedStatement pstIpAdresa = conSQL.prepareStatement(sqlIpAdresaStampaci);
        String ipAdresa;
        ResultSet rsIpAdresaStampac = pstIpAdresa.executeQuery();
        if (rsIpAdresaStampac.next()) {
            ipAdresa = rsIpAdresaStampac.getString("ip_adresa");
        } else {
            ipAdresa = "Stampac nema unesenu ip adresu!";
        }

        return ipAdresa;
    }

    String getLokacijaDetail(int idStampac) throws SQLException {
        String sqlLokacijaDetailStampaci = "SELECT stmp.lokacijaKorisnik, lok.naziv FROM stampaci AS stmp "
                + " JOIN lokacija as lok WHERE stmp.aktivan AND stmp.vazeci AND lok.aktivan AND lok.vazeci  AND  stmp.id_stampaci = " + idStampac + " AND stmp.id_lokacija = lok.id_lokacija";
        PreparedStatement pstLokacijaDetail = conSQL.prepareStatement(sqlLokacijaDetailStampaci);
        String lokacijaDetail;
        ResultSet rsLokacijaDetailStampac = pstLokacijaDetail.executeQuery();
        if (rsLokacijaDetailStampac.next()) {
            lokacijaDetail = rsLokacijaDetailStampac.getString("stmp.lokacijaKorisnik") + " " + rsLokacijaDetailStampac.getString("lok.naziv");
        } else {
            lokacijaDetail = "Stampac nema unesenu ip adresu!";
        }

        return lokacijaDetail;
    }

    ArrayList<String> getAllInfo(int idStampac) throws SQLException {
        String sqlAllStampaci = "SELECT * FROM stampaci WHERE aktivan AND vazeci AND  id_stampaci=" + idStampac;
        PreparedStatement pstAllStampaci = conSQL.prepareStatement(sqlAllStampaci);
        ResultSet rsAllStampaci = pstAllStampaci.executeQuery();
        ArrayList<String> stampaci = new ArrayList();
        ResultSetMetaData rsmd = rsAllStampaci.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rsAllStampaci.next()) {
            int i = 1;
            while (i <= columnCount) {
                stampaci.add(rsAllStampaci.getString(i++));
            }
        }

        return stampaci;

    }

}
