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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author milosjelic
 */
public class Racunari {

    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";
    Stampaci stm = new Stampaci();

    public Racunari() throws SQLException {

        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    String getId(int inventarskiBroj) throws SQLException {
        @SuppressWarnings("UnusedAssignment")
        String id = null;
        String sqlIdRacunar = "SELECT id_racunar from racunari WHERE aktivan and vazeci and inv_broj=" + inventarskiBroj;
        PreparedStatement pstIdRacunar = conSQL.prepareStatement(sqlIdRacunar);
        ResultSet rsIdRacunar = pstIdRacunar.executeQuery();

        if (rsIdRacunar.next()) {

            id = rsIdRacunar.getString("id_racunar");

        } else {
            id = "Nema rezultata";
        }
        return id;
    }

    Long add(int idKategorija, int idLokacija, String invBroj, String specifikacija, String os, String office, String korisnik, String ipAdresa, String macAdresa, String os_key, String office_key, String uneo, String datum) throws SQLException {
        Long key = null;
        String sqlAddRacunar = "INSERT INTO racunari (id_kategorija,id_lokacija,inv_broj,specifikacija,os,office,"
                + "korisnik,ip_adresa,mac_adresa,os_key,office_key,datum,uneo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstInsertRacunar = conSQL.prepareStatement(sqlAddRacunar, Statement.RETURN_GENERATED_KEYS);
        pstInsertRacunar.setInt(1, idKategorija);
        pstInsertRacunar.setInt(2, idLokacija);
        pstInsertRacunar.setString(3, invBroj);
        pstInsertRacunar.setString(4, specifikacija);
        pstInsertRacunar.setString(5, os);
        pstInsertRacunar.setString(6, office);
        pstInsertRacunar.setString(7, korisnik);
        pstInsertRacunar.setString(8, ipAdresa);
        pstInsertRacunar.setString(9, macAdresa);
        pstInsertRacunar.setString(10, os_key);
        pstInsertRacunar.setString(11, office_key);
        pstInsertRacunar.setString(12, datum);
        pstInsertRacunar.setString(13, uneo);
        pstInsertRacunar.addBatch();
        int i = pstInsertRacunar.executeUpdate();
        ResultSet generatedKey = pstInsertRacunar.getGeneratedKeys();
        if (generatedKey.next()) {
            key = generatedKey.getLong(1);

        }
        conSQL.commit();
        return key;
    }

    boolean edit(int idRacunar, int idKategorija, int idLokacija, String invBroj, String specifikacija, String os, String office, String korisnik, String ipAdresa, String macAdresa, String os_key, String office_key, String editovao) throws SQLException {
        String sqlEditRacunar
                = "UPDATE racunari SET id_kategorija = " + idKategorija + ", id_lokacija=" + idLokacija + ",inv_broj = '" + invBroj + "',specifikacija = '" + specifikacija + "',"
                + "os = '" + os + "',office = '" + office + "'"
                + ",korisnik = '" + korisnik + "',mac_adresa = '" + macAdresa + "',"
                + "ip_adresa = '" + ipAdresa + "',os_key = '" + os_key + "', office_key ='" + office_key + "'"
                + ",editovao = '" + editovao + "',edit_ts = CURRENT_TIMESTAMP WHERE id_racunar=" + idRacunar;

        PreparedStatement pstUpdateRacunar = conSQL.prepareStatement(sqlEditRacunar);
        int i = pstUpdateRacunar.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    String getIdByIp(String ipAddr) throws SQLException {
        String sqlInvBroj = "SELECT id_racunar FROM racunari WHERE aktivan and vazeci and ip_adresa='" + ipAddr + "'";
        PreparedStatement pstId = conSQL.prepareStatement(sqlInvBroj);
        String id;
        ResultSet rsId = pstId.executeQuery();
        if (rsId.next()) {
            id = rsId.getString("id_racunar");
        } else {
            id = "Nema rezultata";
        }

        return id;
    }

    String getInventarskiBroj(int idRacunar) throws SQLException {
        String sqlInvBroj = "SELECT inv_broj FROM racunari WHERE aktivan and vazeci and id_racunar=" + idRacunar;
        PreparedStatement pstInvBroj = conSQL.prepareStatement(sqlInvBroj);
        String invBroj;
        ResultSet rsInvBroj = pstInvBroj.executeQuery();
        if (rsInvBroj.next()) {
            invBroj = rsInvBroj.getString("inv_broj");
        } else {
            invBroj = "Nema rezultata";
        }

        return invBroj;
    }

    String getIpAdresa(int idRacunar) throws SQLException {
        String sqlIpAdresaRacunar = "SELECT ip_adresa FROM racunari WHERE aktivan and vazeci and id_racunar=" + idRacunar;
        PreparedStatement pstIpAdresa = conSQL.prepareStatement(sqlIpAdresaRacunar);
        String ipAdresa;
        ResultSet rsIpAdresaRacunar = pstIpAdresa.executeQuery();
        if (rsIpAdresaRacunar.next()) {
            ipAdresa = rsIpAdresaRacunar.getString("ip_adresa");
        } else {
            ipAdresa = "Nema rezultata";
        }

        return ipAdresa;
    }

    String getIpAdresaInv(int invBroj) throws SQLException {
        String sqlIpAdresaRacunar = "SELECT ip_adresa FROM racunari WHERE aktivan and vazeci and inv_broj=" + invBroj;
        PreparedStatement pstIpAdresa = conSQL.prepareStatement(sqlIpAdresaRacunar);
        String ipAdresa;
        ResultSet rsIpAdresaRacunar = pstIpAdresa.executeQuery();
        if (rsIpAdresaRacunar.next()) {
            ipAdresa = rsIpAdresaRacunar.getString("ip_adresa");
        } else {
            ipAdresa = "Nema rezultata";
        }

        return ipAdresa;
    }

    String getMacAdresa(int idRacunar) throws SQLException {
        String sqlMacAdresaRacunar = "SELECT mac_adresa FROM racunari WHERE aktivan and vazeci and id_racunar=" + idRacunar;
        PreparedStatement pstMacAdresa = conSQL.prepareStatement(sqlMacAdresaRacunar);
        String ipAdresa;
        ResultSet rsMacAdresaRacunar = pstMacAdresa.executeQuery();
        if (rsMacAdresaRacunar.next()) {
            ipAdresa = rsMacAdresaRacunar.getString("mac_adresa");
        } else {
            ipAdresa = "Nema rezultata";
        }

        return ipAdresa;
    }

    String getMacAdresaInv(int invBroj) throws SQLException {
        String sqlMacAdresaRacunar = "SELECT mac_adresa FROM racunari WHERE aktivan and vazeci and inv_broj=" + invBroj;
        PreparedStatement pstMacAdresa = conSQL.prepareStatement(sqlMacAdresaRacunar);
        String ipAdresa;
        ResultSet rsMacAdresaRacunar = pstMacAdresa.executeQuery();
        if (rsMacAdresaRacunar.next()) {
            ipAdresa = rsMacAdresaRacunar.getString("mac_adresa");
        } else {
            ipAdresa = "Nema rezultata";
        }

        return ipAdresa;
    }

    String getLokacija(int idRacunar) throws SQLException {
        String sqlLokacijaStampaci = "SELECT naziv FROM lokacija as lok JOIN racunari as rac WHERE lok.aktivan and lok.vazeci and lok.id_lokacija = rac.id_lokacija AND rac.id_racunar =" + idRacunar;
        PreparedStatement pstLokacija = conSQL.prepareStatement(sqlLokacijaStampaci);
        String lokacija;
        ResultSet rsIdLokRacunar = pstLokacija.executeQuery();

        if (rsIdLokRacunar.next()) {

            lokacija = rsIdLokRacunar.getString("naziv");

        } else {
            lokacija = "Nema rezultata";
        }
        return lokacija;
    }

    String getLokacijaId(int idRacunar) throws SQLException {
        String sqlLokacijaStampaci = "SELECT id_lokacija FROM lokacija as lok JOIN racunari as rac WHERE aktivan and vazeci and lok.id_lokacija = rac.id_lokacija AND rac.id_racunar =" + idRacunar;
        PreparedStatement pstLokacija = conSQL.prepareStatement(sqlLokacijaStampaci);
        String lokacija;
        ResultSet rsIdLokRacunar = pstLokacija.executeQuery();

        if (rsIdLokRacunar.next()) {

            lokacija = rsIdLokRacunar.getString("id_lokacija");

        } else {
            lokacija = "Nema rezultata";
        }
        return lokacija;
    }

    int getLokacijaId(String nazivlok) throws SQLException {
        String sqlLokacijaStampaci = "SELECT id_lokacija FROM lokacija WHERE aktivan and vazeci and naziv='" + nazivlok + "'";
        PreparedStatement pstLokacija = conSQL.prepareStatement(sqlLokacijaStampaci);
        int lokacija;
        ResultSet rsIdLokRacunar = pstLokacija.executeQuery();

        if (rsIdLokRacunar.next()) {

            lokacija = rsIdLokRacunar.getInt("id_lokacija");

        } else {
            lokacija = 0;
        }
        return lokacija;
    }

    String getLokacijaInv(int invBroj) throws SQLException {
        String sqlLokacijaStampaci = "SELECT naziv FROM lokacija as lok JOIN racunari as rac WHERE lok.aktivan and lok.vazeci and lok.id_lokacija = rac.id_lokacija AND rac.inv_broj =" + invBroj;
        PreparedStatement pstLokacija = conSQL.prepareStatement(sqlLokacijaStampaci);
        String lokacija;
        ResultSet rsIdLokRacunar = pstLokacija.executeQuery();

        if (rsIdLokRacunar.next()) {

            lokacija = rsIdLokRacunar.getString("naziv");

        } else {
            lokacija = "Nema rezultata";
        }
        return lokacija;
    }

    String getSpec(int idRacunar) throws SQLException {
        String sqlSpec = "SELECT specifikacija FROM racunari where aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstSpec = conSQL.prepareStatement(sqlSpec);
        String spec;
        ResultSet rsSpec = pstSpec.executeQuery();

        if (rsSpec.next()) {

            spec = rsSpec.getString("specifikacija");

        } else {
            spec = "Nema rezultata";
        }
        return spec;
    }

    String getSpecInv(int invBroj) throws SQLException {
        String sqlSpec = "SELECT specifikacija FROM racunari where aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstSpec = conSQL.prepareStatement(sqlSpec);
        String spec;
        ResultSet rsSpec = pstSpec.executeQuery();

        if (rsSpec.next()) {

            spec = rsSpec.getString("specifikacija");

        } else {
            spec = "Nema rezultata";
        }
        return spec;
    }

    String getOs(int idRacunar) throws SQLException {
        String sqlOs = "SELECT os FROM racunari where aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstOs = conSQL.prepareStatement(sqlOs);
        String os;
        ResultSet rsOs = pstOs.executeQuery();

        if (rsOs.next()) {

            os = rsOs.getString("os");

        } else {
            os = "Nema rezultata";
        }
        return os;
    }

    String getOsInv(int invBroj) throws SQLException {
        String sqlOs = "SELECT os FROM racunari where aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstOs = conSQL.prepareStatement(sqlOs);
        String os;
        ResultSet rsOs = pstOs.executeQuery();

        if (rsOs.next()) {

            os = rsOs.getString("os");

        } else {
            os = "Nema rezultata";
        }
        return os;
    }

    String getOsKey(int idRacunar) throws SQLException {
        String sqlOsKey = "SELECT os_key FROM racunari where aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstOsKey = conSQL.prepareStatement(sqlOsKey);
        String osKey;
        ResultSet rsOsKey = pstOsKey.executeQuery();

        if (rsOsKey.next()) {

            osKey = rsOsKey.getString("os_key");

        } else {
            osKey = "Nema rezultata";
        }
        return osKey;
    }

    String getOsKeyInv(int invBroj) throws SQLException {
        String sqlOsKey = "SELECT os_key FROM racunari where  aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstOsKey = conSQL.prepareStatement(sqlOsKey);
        String osKey;
        ResultSet rsOsKey = pstOsKey.executeQuery();

        if (rsOsKey.next()) {

            osKey = rsOsKey.getString("os_key");

        } else {
            osKey = "Nema rezultata";
        }
        return osKey;
    }

    String getOffice(int idRacunar) throws SQLException {
        String sqlOffice = "SELECT office FROM racunari where aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstOffice = conSQL.prepareStatement(sqlOffice);
        String office;
        ResultSet rsOffice = pstOffice.executeQuery();

        if (rsOffice.next()) {

            office = rsOffice.getString("office");

        } else {
            office = "Nema rezultata";
        }
        return office;
    }

    String getOfficeInv(int invBroj) throws SQLException {
        String sqlOffice = "SELECT office FROM racunari where aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstOffice = conSQL.prepareStatement(sqlOffice);
        String office;
        ResultSet rsOffice = pstOffice.executeQuery();

        if (rsOffice.next()) {

            office = rsOffice.getString("office");

        } else {
            office = "Nema rezultata";
        }
        return office;
    }

    String getDatum(int idRacunar) throws SQLException {
        String sqlDatum = "SELECT datum FROM racunari where aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstDatum = conSQL.prepareStatement(sqlDatum);
        String datum;
        ResultSet rsDatum = pstDatum.executeQuery();

        if (rsDatum.next()) {

            datum = rsDatum.getString("datum");

        } else {
            datum = "Nema rezultata";
        }

        return datum;
    }

    String getDatumInv(int invBroj) throws SQLException {
        String sqlDatum = "SELECT datum FROM racunari where aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstDatum = conSQL.prepareStatement(sqlDatum);
        String datum;
        ResultSet rsDatum = pstDatum.executeQuery();

        if (rsDatum.next()) {

            datum = rsDatum.getString("datum");

        } else {
            datum = "Nema rezultata";
        }

        return datum;
    }

    String getOfficeKey(int idRacunar) throws SQLException {
        String sqlOfficeKey = "SELECT office_key FROM racunari where aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstOfficeKey = conSQL.prepareStatement(sqlOfficeKey);
        String officeKey;
        ResultSet rsOfficeKey = pstOfficeKey.executeQuery();

        if (rsOfficeKey.next()) {

            officeKey = rsOfficeKey.getString("office_key");

        } else {
            officeKey = "Nema rezultata";
        }
        return officeKey;
    }

    String getOfficeKeyInv(int invBroj) throws SQLException {
        String sqlOfficeKey = "SELECT office_key FROM racunari where aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstOfficeKey = conSQL.prepareStatement(sqlOfficeKey);
        String officeKey;
        ResultSet rsOfficeKey = pstOfficeKey.executeQuery();

        if (rsOfficeKey.next()) {

            officeKey = rsOfficeKey.getString("office_key");

        } else {
            officeKey = "Nema rezultata";
        }
        return officeKey;
    }

    ArrayList<String> getAllInfo(int idRacunar) throws SQLException {
        String sqlGetAllInfo = "SELECT * FROM racunari WHERE aktivan and vazeci and id_racunar =" + idRacunar;
        PreparedStatement pstAllRacunari = conSQL.prepareStatement(sqlGetAllInfo);
        ResultSet rsAllRacunari = pstAllRacunari.executeQuery();
        ArrayList<String> racunari = new ArrayList();
        ResultSetMetaData rsmd = rsAllRacunari.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rsAllRacunari.next()) {
            int i = 1;
            while (i <= columnCount) {
                racunari.add(rsAllRacunari.getString(i++));
            }
        }

        return racunari;
    }

    ArrayList<String> getAllInfoByUser(String user) throws SQLException {
        String sqlGetAllInfoByUser = "SELECT id_racunar,inv_broj,korisnik, ip_adresa FROM racunari WHERE aktivan and vazeci and korisnik like '%" + user + "%'";
        PreparedStatement pstAllRacunari = conSQL.prepareStatement(sqlGetAllInfoByUser);
        ResultSet rsAllRacunari = pstAllRacunari.executeQuery();
        ArrayList racunari = new ArrayList();
        while (rsAllRacunari.next()) {
            racunari.add((Integer) rsAllRacunari.getObject("id_racunar") + " • " + rsAllRacunari.getString("ip_adresa") + " • " + rsAllRacunari.getString("korisnik") + " • " + rsAllRacunari.getString("inv_broj"));
        }

        return racunari;
    }

    ArrayList<String> getAllInfoInv(int invBroj) throws SQLException {
        String sqlGetAllInfo = "SELECT * FROM racunari WHERE aktivan and vazeci and inv_broj =" + invBroj;
        PreparedStatement pstAllRacunari = conSQL.prepareStatement(sqlGetAllInfo);
        ResultSet rsAllRacunari = pstAllRacunari.executeQuery();
        ArrayList<String> racunari = new ArrayList();
        ResultSetMetaData rsmd = rsAllRacunari.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rsAllRacunari.next()) {
            int i = 1;
            while (i <= columnCount) {
                racunari.add(rsAllRacunari.getString(i++));
            }
        }

        return racunari;
    }

    String getKorisnik(int idRacunar) throws SQLException {
        String sqlKorisnik = "SELECT korisnik FROM racunari WHERE aktivan and vazeci and  id_racunar=" + idRacunar;
        PreparedStatement pstKorisnik = conSQL.prepareStatement(sqlKorisnik);
        String korisnik;
        ResultSet rsKorisnik = pstKorisnik.executeQuery();

        if (rsKorisnik.next()) {

            korisnik = rsKorisnik.getString("korisnik");

        } else {
            korisnik = "Nema rezultata";
        }
        return korisnik;
    }

    String getKorisnikInv(int invBroj) throws SQLException {
        String sqlKorisnik = "SELECT korisnik FROM racunari WHERE aktivan and vazeci and inv_broj=" + invBroj;
        PreparedStatement pstKorisnik = conSQL.prepareStatement(sqlKorisnik);
        String korisnik;
        ResultSet rsKorisnik = pstKorisnik.executeQuery();

        if (rsKorisnik.next()) {

            korisnik = rsKorisnik.getString("korisnik");

        } else {
            korisnik = "Nema rezultata";
        }
        return korisnik;
    }

    String specifikacija(String procesor, String ram, String hdd, String gpu, String napajanje) {
        String specifikacija = "{\"procesor\":\"" + procesor + "\",\"ram\":\"" + ram + "\",\"hdd\":\"" + hdd + "\",\"gpu\":\"" + gpu + "\",\"napajanje\":\"" + napajanje + "\"}";

        return specifikacija;
    }

    ArrayList sviInventarski() throws SQLException {
        String sqlInv = "SELECT inv_broj FROM racunari WHERE aktivan and vazeci";
        PreparedStatement pstInv = conSQL.prepareStatement(sqlInv);
        ResultSet rsInv = pstInv.executeQuery();
        ArrayList inventarskiBrojevi = new ArrayList<>();
        while (rsInv.next()) {
            inventarskiBrojevi.add(rsInv.getString("inv_broj"));
        }
        return inventarskiBrojevi;
    }

    ArrayList sviId() throws SQLException {
        String sqlId = "SELECT id_racunar FROM racunari WHERE aktivan and vazeci";
        PreparedStatement pstId = conSQL.prepareStatement(sqlId);
        ResultSet rsId = pstId.executeQuery();
        ArrayList idBrojevi = new ArrayList<>();
        while (rsId.next()) {
            idBrojevi.add(rsId.getInt("id_racunar"));
        }
        return idBrojevi;
    }

    ArrayList<String> sveIpAdrese() throws SQLException {
        String sqlInv = "SELECT ip_adresa FROM racunari WHERE aktivan and vazeci";
        PreparedStatement pstIp = conSQL.prepareStatement(sqlInv);
        ResultSet rsIp = pstIp.executeQuery();
        ArrayList ipAdrese = new ArrayList<>();
        while (rsIp.next()) {
            ipAdrese.add(rsIp.getString("ip_adresa"));
        }
        return ipAdrese;
    }

    ArrayList<String> sveMacAdrese() throws SQLException {
        String sqlInv = "SELECT mac_adresa FROM racunari WHERE aktivan AND vazeci";
        PreparedStatement pstMac = conSQL.prepareStatement(sqlInv);
        ResultSet rsMac = pstMac.executeQuery();
        ArrayList macAdrese = new ArrayList<>();
        while (rsMac.next()) {
            macAdrese.add(rsMac.getString("mac_adresa"));
        }
        return macAdrese;
    }

    boolean checkInventarski(String invBroj) throws SQLException {
        if (invBroj != null && !"".equals(invBroj)) {

            boolean result;
            String sqlInv = "SELECT inv_broj FROM racunari WHERE aktivan AND vazeci AND inv_broj ='" + invBroj + "'";
            PreparedStatement pstInv = conSQL.prepareStatement(sqlInv);
            ResultSet rsInv = pstInv.executeQuery();
            result = rsInv.next();
            return result;
        } else {
            return false;
        }
    }

    boolean checkIp(String ipAdresa) throws SQLException {
        if (ipAdresa != null && !"".equals(ipAdresa)) {

            boolean result;
            String sqlIp = "SELECT ip_adresa FROM racunari WHERE aktivan AND vazeci AND ip_adresa ='" + ipAdresa + "'";
            PreparedStatement pstIp = conSQL.prepareStatement(sqlIp);
            ResultSet rsIp = pstIp.executeQuery();
            result = rsIp.next();
            return result;
        } else {
            return false;
        }
    }

    boolean checkMAC(String macAdresa) throws SQLException {
        if (macAdresa != null && !"".equals(macAdresa)) {
            boolean result;
            String sqlMac = "SELECT mac_adresa FROM racunari WHERE aktivan AND vazeci AND mac_adresa ='" + macAdresa + "'";
            PreparedStatement pstMac = conSQL.prepareStatement(sqlMac);
            ResultSet rsMac = pstMac.executeQuery();
            result = rsMac.next();
            return result;

        } else {
            return false;
        }
    }

    int getQueryRowCount(String query) throws SQLException {
        try (Statement statement = conSQL.createStatement();
                ResultSet standardRS = statement.executeQuery(query)) {
            int size = 0;
            while (standardRS.next()) {
                size++;
            }
            return size;
        }

    }

    String sqlEditCheck(String key, String param) throws SQLException {
        String sql = null;
        switch (key) {
            case "inventarski":
                sql = "SELECT inv_broj FROM racunari WHERE aktivan AND vazeci AND inv_broj ='" + param + "'";
                break;
            case "ip":
                sql = "SELECT ip_adresa FROM racunari WHERE aktivan AND vazeci AND ip_adresa ='" + param + "'";
                break;
            case "mac":
                sql = "SELECT mac_adresa FROM racunari WHERE aktivan AND vazeci AND mac_adresa ='" + param + "'";
                break;
            default:
                break;
        }
        return sql;
    }
}
