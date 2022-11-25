/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author milosjelic
 */
public class RacunariEdit extends javax.swing.JPanel {

    Racunari racunari;
    Lokacija lokacija;
    Prijem prijem;
    ArrayList<String> sveLokacije;
    int idRacunar;
    String korisnik;
    String ipAdresaRez;
    String macAdresaRez;
    String lokacijaRez;
    String osRez;
    String officeRez;
    String osKeyRez;
    String officeKeyRez;
    String korisnikRez;
    String cpuRez;
    String ramRez;
    String hddRez;
    String gpuRez;
    String psuRez;
    String invRez;
    /**
     * Creates new form RacunariPregled
     *
     * @param username
     * @throws java.sql.SQLException
     */
    public RacunariEdit(String username) throws SQLException {
        initComponents();
        racunari = new Racunari();
        lokacija = new Lokacija();
        prijem = new Prijem();
        sveLokacije = lokacija.getAll();
        this.korisnik = username;

    }
    
    class Data {

        String korisnik;
        String invBroj;
        String ipAdresa;
        String macAdresa;
        String os;
        String osKey;
        String office;
        String officeKey;
        String procesor;
        String ram;
        String hdd;
        String gpu;
        String napajanje;
        String specifikacija;
        String lokacija;

        public Data(String korisnik, String invBroj, String ipAdresa,String lokacija, String macAdresa, String os, String osKey, String office, String officeKey, String procesor, String ram, String hdd, String gpu, String napajanje, String specifikacija) {
            this.korisnik = korisnik;
            this.invBroj = invBroj;
            this.ipAdresa = ipAdresa;
            this.macAdresa = macAdresa;
            this.os = os;
            this.osKey = osKey;
            this.office = office;
            this.officeKey = officeKey;
            this.procesor = procesor;
            this.ram = ram;
            this.hdd = hdd;
            this.gpu = gpu;
            this.napajanje = napajanje;
            this.specifikacija = specifikacija;
            this.lokacija = lokacija;
        }
    }

    public Data getData() {
        // return person details from the method
        String procesor = cpuField.getText();
        String ram = ramField.getText();
        String hdd = hddField.getText();
        String gpu = gpuField.getText();
        String napajanje = psuField.getText();
        String specifikacija = racunari.specifikacija(procesor, ram, hdd, gpu, napajanje);
        String korisnik = korisnikField.getText();
        String invBroj = invBrojField.getText();
        String ipAdresa = ipAdresaField.getText();
        String macAdresa = macAdresaField.getText();
        String os = osComboBox.getSelectedItem().toString();
        String osKey = osKeyField.getText();
        String office = officeComboBox.getSelectedItem().toString();
        String officeKey = officeKeyField.getText();
        String lokacija = lokacijaComboBox.getSelectedItem().toString();

        return new Data(korisnik, invBroj, ipAdresa, lokacija, macAdresa, os, osKey, office, officeKey, procesor, ram, hdd, gpu, napajanje, specifikacija);
    }

   
    void puniRezId(int search) throws SQLException, JSONException {
        ArrayList sviId = racunari.sviId();
        if (sviId.contains(search)) {
            idRacunar = search;
            ipAdresaRez = racunari.getIpAdresa(search);
            lokacijaRez = racunari.getLokacija(search);
            macAdresaRez = racunari.getMacAdresa(search);
            invRez = racunari.getInventarskiBroj(search);
            osRez = racunari.getOs(search);
            officeRez = racunari.getOffice(search);
            osKeyRez = racunari.getOsKey(search);
            officeKeyRez = racunari.getOfficeKey(search);
            korisnikRez = racunari.getKorisnik(search);
            String specifikacija = racunari.getSpec(search);
            JSONObject obj = new JSONObject(specifikacija);
            cpuRez = obj.getString("procesor");
            ramRez = obj.getString("ram");
            hddRez = obj.getString("hdd");
            gpuRez = obj.getString("gpu");
            psuRez = obj.getString("napajanje");
            
            ipAdresaField.setText(ipAdresaRez);
            sveLokacije.add(0, null);
            lokacijaComboBox.setModel(new DefaultComboBoxModel<>(sveLokacije.toArray(new String[0])));
            lokacijaComboBox.setSelectedItem(lokacijaRez);
            macAdresaField.setText(macAdresaRez);
            invBrojField.setText(invRez);
            osComboBox.setSelectedItem(osRez);
            officeComboBox.setSelectedItem(officeRez);
            osKeyField.setText(osKeyRez);
            officeKeyField.setText(officeKeyRez);
            korisnikField.setText(korisnikRez);
            datumLabel.setText(racunari.getDatum(search));
          
            cpuField.setText(cpuRez);
            ramField.setText(ramRez);
            hddField.setText(hddRez);
            gpuField.setText(gpuRez);
            psuField.setText(psuRez);
            fakturaLabel.setText(prijem.brojFakture(search));
        } else {
            JOptionPane.showMessageDialog(null, "Nema rezultata", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    void puniRezInv(int search) throws SQLException, JSONException {
        ArrayList sviInv = racunari.sviInventarski();
        if (sviInv.contains(String.valueOf(search))) {
            String id = racunari.getId(search);
            idRacunar = Integer.parseInt(id);
            ipAdresaRez = racunari.getIpAdresaInv(search);
            lokacijaRez = racunari.getLokacijaInv(search);
            macAdresaRez = racunari.getMacAdresaInv(search);
            invRez = searchField.getText().trim();
            osRez = racunari.getOsInv(search);
            officeRez = racunari.getOfficeInv(search);
            osKeyRez = racunari.getOsKeyInv(search);
            officeKeyRez = racunari.getOfficeKeyInv(search);
            korisnikRez = racunari.getKorisnikInv(search);
            String specifikacija = racunari.getSpecInv(search);
            JSONObject obj = new JSONObject(specifikacija);
            cpuRez = obj.getString("procesor");
            ramRez = obj.getString("ram");
            hddRez = obj.getString("hdd");
            gpuRez = obj.getString("gpu");
            psuRez = obj.getString("napajanje");
            
            ipAdresaField.setText(ipAdresaRez);
            lokacijaComboBox.setModel(new DefaultComboBoxModel<>(sveLokacije.toArray(new String[0])));
            lokacijaComboBox.setSelectedItem(lokacijaRez);
            macAdresaField.setText(macAdresaRez);
            invBrojField.setText(invRez);
            osComboBox.setSelectedItem(osRez);
            officeComboBox.setSelectedItem(officeRez);
            osKeyField.setText(osKeyRez);
            officeKeyField.setText(officeKeyRez);
            korisnikField.setText(korisnikRez);
            datumLabel.setText(racunari.getDatumInv(search));
            
            cpuField.setText(cpuRez);
            ramField.setText(ramRez);
            hddField.setText(hddRez);
            gpuField.setText(gpuRez);
            psuField.setText(psuRez);
            fakturaLabel.setText(prijem.brojFakture(Integer.valueOf(id)));
        } else {
            JOptionPane.showMessageDialog(null, "Nema rezultata", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    boolean checkSearch() {
        String s = searchField.getText().trim();
        return !(s.isEmpty() || s.equals(" "));
    }

    boolean checkSearchNum() {
        String s = searchField.getText().trim();
       return isNumber(s);
        

    }
    boolean validIP(){
    String ip = searchField.getText().trim();
    try {
        if ( ip == null || ip.isEmpty() ) {
            return false;
        }

        String[] parts = ip.split( "\\." );
        if ( parts.length != 4 ) {
            return false;
        }

        for ( String s : parts ) {
            int i = Integer.parseInt( s );
            if ( (i < 0) || (i > 255) ) {
                return false;
            }
        }
        return !ip.endsWith(".");
    } catch (NumberFormatException nfe) {
        return false;
    }
    
    
}
    
    void resetData(){
        ipAdresaField.setText(null);
            lokacijaComboBox.setSelectedIndex(0);
            macAdresaField.setText(null);
            invBrojField.setText(null);
             osComboBox.setSelectedIndex(0);
            officeComboBox.setSelectedIndex(0);
            osKeyField.setText(null);
            officeKeyField.setText(null);
            korisnikField.setText(null);
            datumLabel.setText(null);       
            cpuField.setText(null);
            ramField.setText(null);
            hddField.setText(null);
            gpuField.setText(null);
            psuField.setText(null);
            fakturaLabel.setText(null);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cpuLabel = new javax.swing.JLabel();
        ramLabel = new javax.swing.JLabel();
        hddLabel = new javax.swing.JLabel();
        gpuLabel = new javax.swing.JLabel();
        psuLabel = new javax.swing.JLabel();
        cpuField = new javax.swing.JTextField();
        ramField = new javax.swing.JTextField();
        hddField = new javax.swing.JTextField();
        gpuField = new javax.swing.JTextField();
        psuField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        datumLabel = new javax.swing.JLabel();
        fakturaLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ipAdresaField = new javax.swing.JTextField();
        macAdresaField = new javax.swing.JTextField();
        invBrojField = new javax.swing.JTextField();
        osKeyField = new javax.swing.JTextField();
        officeKeyField = new javax.swing.JTextField();
        korisnikField = new javax.swing.JTextField();
        lokacijaComboBox = new javax.swing.JComboBox<>();
        osComboBox = new javax.swing.JComboBox<>();
        officeComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        inv_brojRadio = new javax.swing.JRadioButton();
        ip_adresaRadio = new javax.swing.JRadioButton();
        korisnikRadio = new javax.swing.JRadioButton();
        jCheckBox124 = new javax.swing.JCheckBox();
        jCheckBox125 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 450));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Računari Izmena");

        searchBtn.setBackground(new java.awt.Color(255, 255, 204));
        searchBtn.setText("Pretraži");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacije"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Specifikacija"));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Procesor -");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("RAM  -");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("HDD/SSD -");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("GPU -");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("PSU -");

        cpuField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpuFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ramLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hddLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(psuField)
                            .addComponent(gpuField)
                            .addComponent(ramField)
                            .addComponent(hddField)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cpuField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gpuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(psuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cpuLabel)
                    .addComponent(cpuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(ramLabel)
                    .addComponent(ramField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(hddLabel)
                    .addComponent(hddField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(gpuLabel)
                    .addComponent(gpuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(psuLabel)
                    .addComponent(psuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Datum unosa -");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Broj fakture -");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("* Lokacija -");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("MAC Adresa -");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("* IP Adresa -");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Inventarski broj -");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("* Operativni sistem -");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("* Office -");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("OS Key -");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Office Key -");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("* Korisnik -");

        osComboBox.setMaximumRowCount(10);
        osComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Windows 98", "Windows XP", "Windows 7", "Windows 8", "Windows 10", "Windows 11", "Linux", "Ostalo" }));
        osComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                osComboBoxItemStateChanged(evt);
            }
        });

        officeComboBox.setMaximumRowCount(10);
        officeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "MS Office 2003", "MS Office 2007", "MS Office 2010", "MS Office 2013", "MS Office 2019", "MS Office 2021", "LibreOffice", "Ostalo" }));
        officeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                officeComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(officeKeyField)
                    .addComponent(osKeyField)
                    .addComponent(invBrojField)
                    .addComponent(macAdresaField)
                    .addComponent(ipAdresaField)
                    .addComponent(lokacijaComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(korisnikField)
                    .addComponent(osComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(officeComboBox, 0, 171, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lokacijaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ipAdresaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(macAdresaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(invBrojField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(osComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(officeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(osKeyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(officeKeyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(korisnikField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel6.setText("Polja sa * su obavezna!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fakturaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(46, 46, 46))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fakturaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(39, 39, 39)
                        .addComponent(jLabel6))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Pretraga po ip adresi, inventarskom broju, korisniku");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Rezultati Pretrage"));

        jScrollPane1.setViewportView(resultList);

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Izaberi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 51, 51));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametar za pretragu"));

        buttonGroup1.add(inv_brojRadio);
        inv_brojRadio.setText("Inventarski broj");
        inv_brojRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inv_brojRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(ip_adresaRadio);
        ip_adresaRadio.setText("IP Adresa");
        ip_adresaRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip_adresaRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(korisnikRadio);
        korisnikRadio.setText("Korisnik");
        korisnikRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                korisnikRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(ip_adresaRadio)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(inv_brojRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(korisnikRadio)
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inv_brojRadio)
                    .addComponent(korisnikRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ip_adresaRadio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCheckBox124.setText("10.11.124");
        jCheckBox124.setEnabled(false);
        jCheckBox124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox124ActionPerformed(evt);
            }
        });

        jCheckBox125.setText("10.11.125");
        jCheckBox125.setEnabled(false);
        jCheckBox125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox125ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("X");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(204, 255, 204));
        editButton.setText("Izmeni");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editButtonMouseReleased(evt);
            }
        });

        resetButton.setBackground(new java.awt.Color(255, 153, 153));
        resetButton.setText("Poništi");
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                resetButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(editButton)
                                        .addGap(41, 41, 41)
                                        .addComponent(resetButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox124)
                    .addComponent(jCheckBox125))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox124)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox125))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton)
                            .addComponent(resetButton)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        try {

            if (inv_brojRadio.isSelected()) {
                if (!checkSearch()) {
                    JOptionPane.showMessageDialog(null, "Morate uneti parametre za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
                } else if (!checkSearchNum()) {
                    JOptionPane.showMessageDialog(null, "Za inventarski broj možete uneti samo brojeve", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    String s = searchField.getText().trim();
                    int search = Integer.parseInt(s);
                    if(racunari.checkInventarski(s)){
                        
                    puniRezInv(search);
                    }else{
                         JOptionPane.showMessageDialog(null, "Nema rezultata", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (ip_adresaRadio.isSelected()) {
                if (!checkSearch()) {
                    JOptionPane.showMessageDialog(null, "Morate uneti parametre za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
                } else if (!validIP()) {
                    JOptionPane.showMessageDialog(null, "Neispravna ip adresa", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    String s = racunari.getIdByIp(searchField.getText().trim());
                    String ip = searchField.getText().trim();
                    if(racunari.checkIp(ip)){
                         int search = Integer.parseInt(s);
                         puniRezId(search);
                    }else{
                        JOptionPane.showMessageDialog(null, "Nema rezultata", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                    
                   
                }

            } else if (korisnikRadio.isSelected()) {
                String search = searchField.getText();
                ArrayList results = racunari.getAllInfoByUser(search);
                if (results.size() > 1) {
                    DefaultListModel listModel = new DefaultListModel();
                    for (int i = 0; i < results.size(); i++) {
                        listModel.addElement(results.get(i));
                    }
                    resultList.setModel(listModel);
                } else if (results.size() == 1) {
                    String rez = results.get(0).toString();
                    String[] id = rez.split("•");
                    Integer idRez = Integer.parseInt(id[0].trim());
                    puniRezId(idRez);
                } else {
                    JOptionPane.showMessageDialog(null, "Nema korisnika pod nazivom " + search, "Pretraga", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Morate izabrati kriterujem za pretragu", "Greška", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | JSONException ex) {
            Logger.getLogger(RacunariPregled.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_searchBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (resultList.getVisibleRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nema rezultata", "Greška", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String selectedRow = resultList.getSelectedValue();
                String[] id = selectedRow.split("•");
                Integer idRez = Integer.parseInt(id[0].trim());
                puniRezId(idRez);
            } catch (JSONException | SQLException ex) {
                Logger.getLogger(RacunariPregled.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ip_adresaRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip_adresaRadioActionPerformed
        jCheckBox125.setEnabled(true);
        jCheckBox124.setEnabled(true);
    }//GEN-LAST:event_ip_adresaRadioActionPerformed

    private void inv_brojRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inv_brojRadioActionPerformed
        jCheckBox125.setEnabled(false);
        jCheckBox124.setEnabled(false);
        jCheckBox125.setSelected(false);
        jCheckBox124.setSelected(false);
        searchField.setText(null);

    }//GEN-LAST:event_inv_brojRadioActionPerformed

    private void korisnikRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_korisnikRadioActionPerformed
        jCheckBox125.setEnabled(false);
        jCheckBox124.setEnabled(false);
        jCheckBox125.setSelected(false);
        jCheckBox124.setSelected(false);
        searchField.setText(null);
    }//GEN-LAST:event_korisnikRadioActionPerformed

    private void jCheckBox124ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox124ActionPerformed
        jCheckBox125.setSelected(false);
        if (jCheckBox124.isSelected()) {

            searchField.setText("10.11.124.");
            searchField.requestFocusInWindow();
        } else {
            searchField.setText(null);
        }
    }//GEN-LAST:event_jCheckBox124ActionPerformed

    private void jCheckBox125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox125ActionPerformed
        jCheckBox124.setSelected(false);
        if (jCheckBox125.isSelected()) {

            searchField.setText("10.11.125.");
            searchField.requestFocusInWindow();
        } else {
            searchField.setText(null);
        }
    }//GEN-LAST:event_jCheckBox125ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        searchField.setText(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultListModel list = (DefaultListModel) resultList.getModel();
        list.removeAllElements();
    }//GEN-LAST:event_jButton3ActionPerformed

    @SuppressWarnings("empty-statement")
    private void editButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseReleased
        try {
            Data data = getData();
            boolean osKeyCheck;
            boolean officeKeyCheck;
            if (osKeyField.isEnabled()) {
                osKeyCheck = data.osKey.isEmpty();
            } else {
                osKeyCheck = false;
            }
            if (officeKeyField.isEnabled()) {
                officeKeyCheck = data.officeKey.isEmpty();
            } else {
                officeKeyCheck = false;
            }
            if (data.os.isEmpty() || data.os.equals(" ") || data.office.isEmpty() || data.ipAdresa.isEmpty() || data.macAdresa.isEmpty() || data.office.equals(" ") || osKeyCheck || officeKeyCheck || data.korisnik.isEmpty() || data.korisnik.equals(" ")) {
                JOptionPane.showMessageDialog(null, "Morate popuniti sva obavezna polja", "Greška", JOptionPane.ERROR_MESSAGE);
            }else if (racunari.getQueryRowCount(racunari.sqlEditCheck("inventarski", data.invBroj)) >= 1 && !invRez.equals(data.invBroj)) {
                JOptionPane.showMessageDialog(null, "Inventarski mora biti jedinstveni za svaki računar", "Greška", JOptionPane.ERROR_MESSAGE);
            } else if (racunari.getQueryRowCount(racunari.sqlEditCheck("ip", data.ipAdresa)) >= 1 && !ipAdresaRez.equals(data.ipAdresa)) {
                JOptionPane.showMessageDialog(null, "IP Adresa mora biti jedinstvena za svaki računar", "Greška", JOptionPane.ERROR_MESSAGE);
            } else if (racunari.getQueryRowCount(racunari.sqlEditCheck("mac", data.macAdresa)) >= 1 && !macAdresaRez.equals(data.macAdresa)) {
                JOptionPane.showMessageDialog(null, "MAC Adresa mora biti jedinstvena za svaki računar", "Greška", JOptionPane.ERROR_MESSAGE);
            } else{
             if(racunari.edit(idRacunar, 1, racunari.getLokacijaId(data.lokacija), data.invBroj,data.specifikacija, data.os, data.office, data.korisnik, data.ipAdresa, data.macAdresa, data.osKey, data.officeKey, korisnik)){
                JOptionPane.showMessageDialog(null, "Uspešno ste izmenili podatke","Edit", JOptionPane.INFORMATION_MESSAGE);
                resetData();
            }else{
                JOptionPane.showMessageDialog(null, "Neuspešno ažuriranje podataka, pokušajte ponovo","Edit", JOptionPane.ERROR_MESSAGE);
            };
        }} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Neuspešno ažuriranje podataka, greška je : \n"+ex,"Edit", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RacunariEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editButtonMouseReleased

    private void resetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseReleased
            resetData();
    }//GEN-LAST:event_resetButtonMouseReleased

    private void cpuFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpuFieldActionPerformed

    private void osComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_osComboBoxItemStateChanged
        String os = osComboBox.getSelectedItem().toString();
        if (os.equals("Ostalo") || os.equals("Linux")) {
            osKeyField.setEnabled(false);
            osKeyField.setText(null);
        } else {
            osKeyField.setEnabled(true);
        }
    }//GEN-LAST:event_osComboBoxItemStateChanged

    private void officeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_officeComboBoxItemStateChanged
        String office = officeComboBox.getSelectedItem().toString();
        if (office.equals("LibreOffice") || office.equals("Ostalo")) {
            officeKeyField.setEnabled(false);
            officeKeyField.setText(null);
        } else {
            officeKeyField.setEnabled(true);
        }
    }//GEN-LAST:event_officeComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cpuField;
    private javax.swing.JLabel cpuLabel;
    private javax.swing.JLabel datumLabel;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel fakturaLabel;
    private javax.swing.JTextField gpuField;
    private javax.swing.JLabel gpuLabel;
    private javax.swing.JTextField hddField;
    private javax.swing.JLabel hddLabel;
    private javax.swing.JTextField invBrojField;
    private javax.swing.JRadioButton inv_brojRadio;
    private javax.swing.JTextField ipAdresaField;
    private javax.swing.JRadioButton ip_adresaRadio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox124;
    private javax.swing.JCheckBox jCheckBox125;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField korisnikField;
    private javax.swing.JRadioButton korisnikRadio;
    private javax.swing.JComboBox<String> lokacijaComboBox;
    private javax.swing.JTextField macAdresaField;
    private javax.swing.JComboBox<String> officeComboBox;
    private javax.swing.JTextField officeKeyField;
    private javax.swing.JComboBox<String> osComboBox;
    private javax.swing.JTextField osKeyField;
    private javax.swing.JTextField psuField;
    private javax.swing.JLabel psuLabel;
    private javax.swing.JTextField ramField;
    private javax.swing.JLabel ramLabel;
    private javax.swing.JButton resetButton;
    private javax.swing.JList<String> resultList;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
