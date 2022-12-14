package inventar;

import inventar.StampaciFrame.Data;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.text.NumberFormatter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author milosjelic
 */
public class Prijem extends javax.swing.JPanel {

    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";
    Stampaci stampaci = new Stampaci();
    Racunari racunari = new Racunari();
    Kategorija kategorija = new Kategorija();
    Lokacija lokacija = new Lokacija();
    Podkategorija podkat = new Podkategorija();
    ArrayList<String> sviStampaci = stampaci.getAll();
    String korisnikG;
    String datumG;
    DefaultListModel<String> modelStavka;
    ArrayList<String> sveKategorije = kategorija.getAll();
    ArrayList<String> sveLokacije = lokacija.getAll();
    ArrayList existingStavke = new ArrayList<>();
    Evidencija ev = new Evidencija();

    /**
     * Creates new form Prijem
     *
     * @param korisnik
     * @param datum
     * @throws java.sql.SQLException
     */
    public Prijem(String korisnik, String datum) throws SQLException {

        initComponents();
        resetData();
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        modelStavka = new DefaultListModel<>();

        sviStampaci.add(0, null);
        sveKategorije.add(0, null);
        sveLokacije.add(0, null);
        datumG = datum;
        korisnikG = korisnik;
        JFormattedTextField txt = ((JSpinner.NumberEditor) jSpinner1.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

        // stampaciComboBox.setModel(new DefaultComboBoxModel<>(sviStampaci.toArray(new String[0])));
        kategorijaComboBox.setModel(new DefaultComboBoxModel<>(sveKategorije.toArray(new String[0])));
        OJComboBox.setModel(new DefaultComboBoxModel<>(sveLokacije.toArray(new String[0])));
        podkatComboBox.setEnabled(false);

        AutoCompleteDecorator.decorate(kategorijaComboBox);
        AutoCompleteDecorator.decorate(OJComboBox);

    }

    Prijem() throws SQLException {

    }

    boolean add(String brPrijem, String naziv, int idKategorija, int podkategorija, int idLokacija, int kolicina, String faktura, String uneo, String datum, String napomena, String korisnikLokacija) throws SQLException {
        String sqlAddPrijem = "INSERT INTO prijem (broj_prijem,id_kategorija,id_podkategorija,id_lokacija,naziv,kolicina,faktura,napomena,korisnikLokacija,uneo,datum) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstInsertPrijem = conSQL.prepareStatement(sqlAddPrijem);
        pstInsertPrijem.setString(1, brPrijem);
        pstInsertPrijem.setInt(2, idKategorija);
        pstInsertPrijem.setInt(3, podkategorija);
        pstInsertPrijem.setInt(4, idLokacija);
        pstInsertPrijem.setString(5, naziv);
        pstInsertPrijem.setInt(6, kolicina);
        pstInsertPrijem.setString(7, faktura);
        pstInsertPrijem.setString(8, napomena);
        pstInsertPrijem.setString(9, korisnikLokacija);
        pstInsertPrijem.setString(10, uneo);
        pstInsertPrijem.setString(11, datum);
        pstInsertPrijem.addBatch();
        int i = pstInsertPrijem.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    Long add(String brPrijem, int brojStavki, String faktura, String uneo, String datum) throws SQLException {
        String sqlAddPrijem = "INSERT INTO prijem (broj_prijem,broj_stavki,faktura,uneo,datum) VALUES(?,?,?,?,?)";
        PreparedStatement pstInsertPrijem = conSQL.prepareStatement(sqlAddPrijem, Statement.RETURN_GENERATED_KEYS);
        pstInsertPrijem.setString(1, brPrijem);
        pstInsertPrijem.setInt(2, brojStavki);
        pstInsertPrijem.setString(3, faktura);
        pstInsertPrijem.setString(4, uneo);
        pstInsertPrijem.setString(5, datum);
        pstInsertPrijem.addBatch();
        int i = pstInsertPrijem.executeUpdate();
        Long key = null;
        ResultSet generatedKey = pstInsertPrijem.getGeneratedKeys();
        if (generatedKey.next()) {
            key = generatedKey.getLong(1);

        }
        conSQL.commit();
        return key;

    }

    boolean addRacunar(String brPrijem, String naziv, int idKategorija, int idLokacija, int kolicina, int idRacunar, String faktura, String uneo, String datum, String napomena, String korisnikLokacija) throws SQLException {
        String sqlAddPrijem = "INSERT INTO prijem (broj_prijem,id_kategorija,id_lokacija,id_racunar,naziv,kolicina,faktura,napomena,korisnikLokacija,uneo,datum) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstInsertPrijem = conSQL.prepareStatement(sqlAddPrijem);
        pstInsertPrijem.setString(1, brPrijem);
        pstInsertPrijem.setInt(2, idKategorija);
        pstInsertPrijem.setInt(3, idLokacija);
        pstInsertPrijem.setInt(4, idRacunar);
        pstInsertPrijem.setString(5, naziv);
        pstInsertPrijem.setInt(6, kolicina);
        pstInsertPrijem.setString(7, faktura);
        pstInsertPrijem.setString(8, napomena);
        pstInsertPrijem.setString(9, korisnikLokacija);
        pstInsertPrijem.setString(10, uneo);
        pstInsertPrijem.setString(11, datum);
        pstInsertPrijem.addBatch();
        int i = pstInsertPrijem.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean addStampac(String brPrijem, String naziv, int idKategorija, int idLokacija, int kolicina, int idStampac, String faktura, String uneo, String datum, String napomena, String korisnikLokacija) throws SQLException {
        String sqlAddPrijem = "INSERT INTO prijem (broj_prijem,id_kategorija,id_lokacija,id_stampac,naziv,kolicina,faktura,napomena,korisnikLokacija,uneo,datum) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstInsertPrijem = conSQL.prepareStatement(sqlAddPrijem);
        pstInsertPrijem.setString(1, brPrijem);
        pstInsertPrijem.setInt(2, idKategorija);
        pstInsertPrijem.setInt(3, idLokacija);
        pstInsertPrijem.setInt(4, idStampac);
        pstInsertPrijem.setString(5, naziv);
        pstInsertPrijem.setInt(6, kolicina);
        pstInsertPrijem.setString(7, faktura);
        pstInsertPrijem.setString(8, napomena);
        pstInsertPrijem.setString(9, korisnikLokacija);
        pstInsertPrijem.setString(10, uneo);
        pstInsertPrijem.setString(11, datum);
        pstInsertPrijem.addBatch();
        int i = pstInsertPrijem.executeUpdate();
        conSQL.commit();
        return i > 0;
    }

    boolean insertStavka(int idPrijem, String brojPrijema, String kategorijaStavka, String podkategorijaStavka, String lokacijaStavka, String nazivStavka, String korisnikStavka, String napomenaStavka, int kolicinaStavkaInt) throws SQLException {
        if ("".equals(podkategorijaStavka)) {
            podkategorijaStavka = null;
        }
        String sqlStavka = "INSERT INTO prijem_stavka (id_prijem,id_kategorija,id_podkategorija,id_lokacija,broj_prijem,naziv,korisnikLokacija,napomena,kolicina,uneo,datum) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstStavka = conSQL.prepareStatement(sqlStavka);
        pstStavka.setInt(1, idPrijem);
        pstStavka.setInt(2, Integer.parseInt(kategorija.getId(kategorijaStavka)));
        pstStavka.setInt(3, podkat.getId(podkategorijaStavka));
        pstStavka.setInt(4, Integer.parseInt(lokacija.getId(lokacijaStavka)));
        pstStavka.setString(5, brojPrijema);
        pstStavka.setString(6, nazivStavka);
        pstStavka.setString(7, korisnikStavka);
        pstStavka.setString(8, napomenaStavka);
        pstStavka.setInt(9, kolicinaStavkaInt);
        pstStavka.setString(10, korisnikG);
        pstStavka.setString(11, datumG);
        pstStavka.addBatch();
        int i = pstStavka.executeUpdate();
        conSQL.commit();
        return i > 0;

    }

    boolean insertStavka(int idPrijem, String brojPrijema, String kategorijaStavka, String lokacijaStavka, String nazivStavka, String korisnikStavka, String napomenaStavka, int kolicinaStavkaInt) throws SQLException {

        String sqlStavka = "INSERT INTO prijem_stavka (id_prijem,id_kategorija,id_lokacija,broj_prijem,naziv,korisnikLokacija,napomena,kolicina,uneo,datum) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstStavka = conSQL.prepareStatement(sqlStavka);
        pstStavka.setInt(1, idPrijem);
        pstStavka.setInt(2, Integer.parseInt(kategorija.getId(kategorijaStavka)));

        pstStavka.setInt(3, Integer.parseInt(lokacija.getId(lokacijaStavka)));
        pstStavka.setString(4, brojPrijema);
        pstStavka.setString(5, nazivStavka);
        pstStavka.setString(6, korisnikStavka);
        pstStavka.setString(7, napomenaStavka);
        pstStavka.setInt(8, kolicinaStavkaInt);
        pstStavka.setString(9, korisnikG);
        pstStavka.setString(10, datumG);
        pstStavka.addBatch();
        int i = pstStavka.executeUpdate();
        conSQL.commit();
        return i > 0;

    }

    public String brojPrijema(String datum) throws SQLException {
        String datumDok = datum.replace("-", "");
        String id;
        String sqlPrijem = "SELECT id_prijem FROM prijem WHERE aktivan AND vazeci AND id_prijem = (SELECT MAX(id_prijem) FROM prijem)";
        PreparedStatement pstIdStampac = conSQL.prepareStatement(sqlPrijem);
        ResultSet rsIdStampac = pstIdStampac.executeQuery();

        if (rsIdStampac.next()) {

            id = rsIdStampac.getString("id_prijem");

        } else {
            id = "0";
        }
        String brojPrijema = datumDok + "-" + "PRIJEM-" + id;

        return brojPrijema;
    }

    String brojFakture(int idRacunar) throws SQLException {
        String brojFakture;
        String sqlbrojFakture = "SELECT p.faktura FROM prijem as p JOIN prijem_stavka as ps WHERE p.aktivan AND p.vazeci AND ps.aktivan AND ps.vazeci AND p.id_prijem = ps.id_prijem and ps.id_racunar =" + idRacunar;
        PreparedStatement pstbrojFakture = conSQL.prepareStatement(sqlbrojFakture);
        ResultSet rsBrojFakture = pstbrojFakture.executeQuery();

        if (rsBrojFakture.next()) {

            brojFakture = rsBrojFakture.getString("p.faktura");

        } else {
            brojFakture = null;
        }
        return brojFakture;
    }

    private void resetData() {
        kategorijaComboBox.setSelectedIndex(0);
        OJComboBox.setSelectedIndex(0);
        nazivField.setText(null);
        jSpinner1.setValue(0);
        korisnikLokacijaField.setText(null);
        fakturaField.setText(null);
        napomenaField.setText(null);
    }

    ArrayList fakture() throws SQLException {

        String sqlFak = "SELECT faktura FROM prijem WHERE aktivan and vazeci";
        PreparedStatement pstFak = conSQL.prepareStatement(sqlFak);
        ResultSet rsFak = pstFak.executeQuery();
        ArrayList fakture = new ArrayList<>();
        while (rsFak.next()) {
            fakture.add(rsFak.getString("faktura"));
        }
        return fakture;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        kategorijaComboBox = new javax.swing.JComboBox<>();
        OJComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        nazivField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        napomenaField = new javax.swing.JTextArea();
        podkatComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        korisnikLokacijaField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fakturaField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stavkaList = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        obrisiBtn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(842, 495));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRIJEM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Novi prijem"));

        kategorijaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        kategorijaComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kategorijaComboBoxItemStateChanged(evt);
            }
        });
        kategorijaComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                kategorijaComboBoxFocusLost(evt);
            }
        });

        OJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        jLabel2.setText("* Kategorija");

        jLabel3.setText("* OJ/Lokacija");

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Dodaj");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setText("Poni??ti");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });

        jLabel4.setText("* Naziv");

        jLabel5.setText("* Koli??ina");

        jLabel6.setText("Napomena");

        napomenaField.setColumns(20);
        napomenaField.setLineWrap(true);
        napomenaField.setRows(5);
        jScrollPane1.setViewportView(napomenaField);

        podkatComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel7.setText("Podkategorija");

        jLabel8.setText("Korisnik/Pod-lokacija");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel9.setText("Polja sa * su obavezna!");

        jLabel10.setText("* Broj fakture / Zavodni broj - prijema");
        jLabel10.setToolTipText("<html><b>Primer :</b><br> \nR-PSU-002312/2022<br>\nR-PBG-000445/2021</html>");

        fakturaField.setToolTipText("Zavodni broj fakture ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(215, 215, 215))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(OJComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(podkatComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(kategorijaComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nazivField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(korisnikLokacijaField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(fakturaField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nazivField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kategorijaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fakturaField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(podkatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(korisnikLokacijaField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Stavke"));

        jScrollPane2.setViewportView(stavkaList);

        jButton3.setBackground(new java.awt.Color(204, 255, 204));
        jButton3.setText("Unesi prijem");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        obrisiBtn.setBackground(new java.awt.Color(255, 153, 153));
        obrisiBtn.setText("Obri??i");
        obrisiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiBtnActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 102, 102));
        jButton6.setText("Obri??i sve");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(obrisiBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(obrisiBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void kategorijaComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kategorijaComboBoxItemStateChanged
        try {
            String kat = String.valueOf(kategorijaComboBox.getSelectedItem());
            ArrayList<String> podkateg = podkat.getAllByCategory(kat);

            if (podkateg.size() > 0) {
                podkatComboBox.setEnabled(true);
            } else {
                podkatComboBox.setEnabled(false);
            }
            podkatComboBox.setModel(new DefaultComboBoxModel<>(podkateg.toArray(new String[0])));
            AutoCompleteDecorator.decorate(podkatComboBox);

            switch (kat) {
                case "Racunari":
                    nazivField.setEnabled(true);
                    nazivField.setText(null);
                    korisnikLokacijaField.setText("unosi se posle");
                    jSpinner1.setValue(1);
                    jSpinner1.setEnabled(false);
                    korisnikLokacijaField.setEnabled(false);
                    break;
                case "Stampaci":
                    korisnikLokacijaField.setText("unosi se posle");
                    jSpinner1.setValue(1);
                    jSpinner1.setEnabled(false);
                    korisnikLokacijaField.setEnabled(false);
                    nazivField.setEnabled(false);
                    nazivField.setText("unosi se posle");
                    break;
                default:
                    korisnikLokacijaField.setText("");
                    korisnikLokacijaField.setEnabled(true);
                    nazivField.setEnabled(true);
                    nazivField.setText(null);
                    jSpinner1.setEnabled(true);
                    break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Prijem.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_kategorijaComboBoxItemStateChanged

    private void kategorijaComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kategorijaComboBoxFocusLost


    }//GEN-LAST:event_kategorijaComboBoxFocusLost

    @SuppressWarnings("UnusedAssignment")
    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        int podkategorija;
        String naziv = nazivField.getText();
        String faktura = fakturaField.getText();
        String kat = String.valueOf(kategorijaComboBox.getSelectedItem());
        int kolicina2 = parseInt(jSpinner1.getValue().toString());
        if (naziv == null || naziv.equals("") || faktura == null || kat.equals("") || kolicina2 == 0) {
            JOptionPane.showMessageDialog(null, "Morate popuniti sva obavezna polja!", "validator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            int kategorija2 = parseInt(kategorija.getId(kategorijaComboBox.getSelectedItem().toString()));
            if (podkatComboBox.getSelectedItem() != null) {
                podkategorija = podkat.getId(podkatComboBox.getSelectedItem().toString());
            } else {
                podkategorija = 0;
            }
            int OJ2 = parseInt(lokacija.getId(OJComboBox.getSelectedItem().toString()));
            String naziv2 = nazivField.getText();
            String brfakture = fakturaField.getText();
//            if(fakture().contains(brfakture)){
//                  JOptionPane.showMessageDialog(null, "Faktura ve?? postoji!","Gre??ka",JOptionPane.ERROR_MESSAGE);
//                  return;
//            }

            String napomena = napomenaField.getText();
            String korisnikLokacija = korisnikLokacijaField.getText();

            switch (kategorijaComboBox.getSelectedItem().toString()) {
                case "Racunari":
                    new RacunariFrame(kategorija2, OJ2, kolicina2, brfakture, naziv2, napomena, korisnikG, datumG).setVisible(true);
                    break;
                case "Stampaci":
                    new StampaciFrame(kategorija2, OJ2, kolicina2, brfakture, napomena, korisnikG, datumG).setVisible(true);
                    StampaciFrame sf = new StampaciFrame();
                    Data dat = sf.getData();
                    naziv2 = dat.marka + " " + dat.model;
                    break;
                default:
                    if (kategorijaComboBox.getSelectedIndex() != 1 && OJComboBox.getSelectedIndex() != -1 && !nazivField.getText().equals("") && !"0".equals(jSpinner1.getValue().toString())) {

                        String kategorijaStavka = kategorijaComboBox.getSelectedItem().toString();
                        String podkategorijaStavka;
                        if (podkatComboBox.getSelectedIndex() != -1) {
                            podkategorijaStavka = podkatComboBox.getSelectedItem().toString();
                        } else {
                            podkategorijaStavka = "";
                        }

                        String lokacijaStavka = OJComboBox.getSelectedItem().toString();
                        String nazivStavka = nazivField.getText();
                        String kolicinaStavka = jSpinner1.getValue().toString();

                        String stavka = "Kategorija : " + kategorijaStavka + " | Podkategorija : " + podkategorijaStavka + " | Lokacija : " + lokacijaStavka + " | Naziv : " + nazivStavka + " | Korisnik : " + korisnikLokacija + " | Napomena :" + napomena + " | Kolicina : " + kolicinaStavka;
                        String[] exStavka = stavka.split("Kolicina");
                        if (existingStavke.contains(exStavka[0].trim())) {
                            int index = existingStavke.indexOf(exStavka[0].trim());
                            String postojecaStavka = modelStavka.get(index);
                            String[] exKolicina = postojecaStavka.split("Kolicina :");
                            int exKolicinaInt = Integer.parseInt(exKolicina[1].trim());
                            int novaKolicina = exKolicinaInt + Integer.parseInt(kolicinaStavka.trim());
                            String[] novaStavka1 = stavka.split("Kolicina :");
                            String novaStavka2 = novaStavka1[0];
                            String novaStavka3 = novaStavka2 + "Kolicina : " + novaKolicina;

                            modelStavka.remove(index);
                            modelStavka.add(index, novaStavka3);
                            System.out.println(exKolicinaInt);

                        } else {

                            modelStavka.addElement(stavka);
                            existingStavke.add(exStavka[0].trim());
                            stavkaList.setModel(modelStavka);
                        }
                    } else {
                        JOptionPane.showMessageDialog(podkatComboBox, "Morate popuniti sva obavezna polja!", "Gre??ka", JOptionPane.ERROR_MESSAGE);
                    }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prijem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MouseReleased


    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        resetData();
    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (modelStavka.size() < 1) {
            JOptionPane.showMessageDialog(null, "Lista je prazna");
        } else {
            int q = JOptionPane.showConfirmDialog(null, "Da li ??elite da izbri??ete sve stavke", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (q == 0) {
                modelStavka.removeAllElements();
                existingStavke.removeAll(existingStavke);
                stavkaList.setModel(modelStavka);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void obrisiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiBtnActionPerformed
        if (modelStavka.size() < 1) {
            JOptionPane.showMessageDialog(null, "Lista je prazna");
        } else {
            @SuppressWarnings("UnusedAssignment")
            int items_to_delete[] = null;

            if (!stavkaList.isSelectionEmpty()) {
                items_to_delete = stavkaList.getSelectedIndices();
            } else {
                JOptionPane.showMessageDialog(null, "Morate izabrati jedan element");
                return;
            }

            if (items_to_delete != null);
            {

                for (int i = items_to_delete.length - 1; i > -1; i--) {
                    modelStavka.remove(items_to_delete[i]);
                    existingStavke.remove(items_to_delete[i]);
                }
                stavkaList.setModel(modelStavka);
            }
        }


    }//GEN-LAST:event_obrisiBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String faktura = fakturaField.getText();
        if (faktura.isEmpty() || faktura.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Morate uneti fakturu prijema!", "Gre??ka", JOptionPane.ERROR_MESSAGE);
        } else if (modelStavka.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Morate uneti stavke prijema!", "Gre??ka", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                String brPrijema = brojPrijema(datumG);
                int brojStavki = stavkaList.getVisibleRowCount();

                Long idPrijemL = add(brPrijema, brojStavki, faktura, korisnikG, datumG);
                int idPrijem = idPrijemL.intValue();

                if (idPrijemL != null) {
                    JOptionPane.showMessageDialog(null, "Uspe??no ste uneli novi prijem " + brPrijema);
                } else {
                    JOptionPane.showMessageDialog(null, "Do??lo je do gre??ke poku??ajte ponovo");
                }

                for (int i = 0; i < modelStavka.getSize(); i++) {
                    String stavka = modelStavka.get(i);
                    String[] stavkaKomp = stavka.split("\\|");

                    String kat1 = stavkaKomp[0];
                    String kategorijaStavka = kat1.split(":")[1].trim();

                    String podkat1 = stavkaKomp[1];
                    String podkategorijaStavka = podkat1.split(":")[1].trim();

                    String oj1 = stavkaKomp[2];
                    String lokacijaStavka = oj1.split(":")[1].trim();

                    String naz1 = stavkaKomp[3];
                    String nazivStavka = naz1.split(":")[1].trim();

                    String kor1 = stavkaKomp[4];
                    String korisnikStavka = kor1.split(":")[1].trim();

                    String nap1 = stavkaKomp[5];
                    String napomenaStavka = nap1.split(":")[1].trim();

                    String kol1 = stavkaKomp[6];
                    String kolicinaStavka = kol1.split(":")[1].trim();
                    int kolicinaStavkaInt = Integer.parseInt(kolicinaStavka);
                    if (podkategorijaStavka.equals("") || podkategorijaStavka.isEmpty()) {
                        insertStavka(idPrijem, brPrijema, kategorijaStavka, lokacijaStavka, nazivStavka, korisnikStavka, napomenaStavka, kolicinaStavkaInt);
                    } else {

                        insertStavka(idPrijem, brPrijema, kategorijaStavka, podkategorijaStavka, lokacijaStavka, nazivStavka, korisnikStavka, napomenaStavka, kolicinaStavkaInt);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Prijem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> OJComboBox;
    private javax.swing.JTextField fakturaField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JComboBox<String> kategorijaComboBox;
    private javax.swing.JTextField korisnikLokacijaField;
    private javax.swing.JTextArea napomenaField;
    private javax.swing.JTextField nazivField;
    private javax.swing.JButton obrisiBtn;
    private javax.swing.JComboBox<String> podkatComboBox;
    private javax.swing.JList<String> stavkaList;
    // End of variables declaration//GEN-END:variables

}
