/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author milosjelic
 */
public class Izdavanje extends javax.swing.JPanel {
 private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";
    String selectKategorija;
    String selectLokacija;
    DefaultTreeModel modelKat;
    DefaultTreeModel modelLok;
    Kategorija kat = new Kategorija();
    Lokacija lok = new Lokacija();
    Podkategorija podkat = new Podkategorija();
    ArrayList<String> sveKategorije = kat.getAll();
    ArrayList<String> svePodkat = podkat.getAll();
    ArrayList<String> sveLokacije = lok.getAll();
    /**
     * Creates new form Izdavanje
     * @throws java.sql.SQLException
     */
    public Izdavanje() throws SQLException {
        initComponents();
        LoadKategorije();
        
       
        
         try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
    }
    DefaultMutableTreeNode kategorije = new DefaultMutableTreeNode("Kategorije");
    public void LoadKategorije() throws SQLException{
     
     
     for(int i = 0; i<sveKategorije.size(); i++){
         String nazivKat = sveKategorije.get(i);
         
         DefaultMutableTreeNode kategorijaBr = new DefaultMutableTreeNode(nazivKat);
         ArrayList pk = podkat.getAllByCategory(nazivKat);
         if(pk.size() > 0){
             for(int j =0; j<pk.size(); j++){
                 kategorijaBr.add(new DefaultMutableTreeNode(pk.get(j)));
             }
         }
         kategorije.add(kategorijaBr);
     }  
     
     modelKat = (DefaultTreeModel)jTree1.getModel();
     modelKat.setRoot(kategorije);
     jTree1.setModel(modelKat);
    }
    
    public void LoadLokacije() throws SQLException{
     DefaultMutableTreeNode lokacije = new DefaultMutableTreeNode("Lokacija");
     
     for(int i = 0; i<sveLokacije.size(); i++){
         String nazivLok = sveLokacije.get(i);
         
         DefaultMutableTreeNode lokacijaBr = new DefaultMutableTreeNode(nazivLok);
         
         lokacije.add(lokacijaBr);
     }  
     
     modelLok = (DefaultTreeModel)jTree2.getModel();
     modelLok.setRoot(lokacije);
     jTree2.setModel(modelLok);
    }
    public void puniTable(int idLokacija, int idKategorija) throws SQLException{
         DefaultListModel listModel = new DefaultListModel();
        ArrayList data = listData(idLokacija, idKategorija);
         if (data.size() > 0) {
                   
                    listModel.add(0, "Naziv | ");
                    for (int i = 0; i < data.size(); i++) {
                        listModel.addElement(data.get(i));
                    }
                   
                }
     
     
          dataList.setModel(listModel);
    }
    
    private void puniTablePodKat(int idlok, int idkat) throws SQLException {
       DefaultListModel listModel = new DefaultListModel();
        ArrayList data = listDataPodKat(idlok, idkat);
         if (data.size() > 0) {
                   
                    listModel.add(0, "Naziv | ");
                    for (int i = 0; i < data.size(); i++) {
                        listModel.addElement(data.get(i));
                    }
                   
                }
     
     
          dataList.setModel(listModel);
    }
    boolean izdavanjeAdd(int idKategorija, String korisnik, int idLokacijaS, int idLokacijaN, int kolicina, String naziv, String brojIzdavanje, String uneo, String datum) throws SQLException{
        String sqlIzdavanje = "INSERT INTO izdavanje (id_kategorija,korisnik,id_lokacija_stara, id_lokacija_nova, kolicina, naziv, brojIzdavanje, uneo,datum) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstIzdavanje = conSQL.prepareStatement(sqlIzdavanje);
        pstIzdavanje.setInt(1, idKategorija);
        pstIzdavanje.setString(2, korisnik);
        pstIzdavanje.setInt(3, idLokacijaS);
        pstIzdavanje.setInt(4,idLokacijaN);
        pstIzdavanje.setInt(5, kolicina);
        pstIzdavanje.setString(6,naziv);
        pstIzdavanje.setString(7,brojIzdavanje);
        pstIzdavanje.setString(8,uneo);
        pstIzdavanje.setString(9, datum);
        pstIzdavanje.addBatch();
        int i = pstIzdavanje.executeUpdate();
        conSQL.commit();
        return i > 0;
    }
    
    ArrayList listData(int idLokacija, int idKategorija) throws SQLException{
        ArrayList data = new ArrayList<>();
        String sqlData = String.format("SELECT * FROM prijem_stavka WHERE aktivan and vazeci and id_kategorija = %d and id_lokacija = %d",idKategorija,idLokacija);
        System.out.println(sqlData);
        PreparedStatement pstAllData = conSQL.prepareStatement(sqlData);
        ResultSet rsAllData = pstAllData.executeQuery();
        
        while (rsAllData.next()) {
            data.add(rsAllData.getString("naziv") + " • " + rsAllData.getString("korisnikLokacija") + " • " + rsAllData.getString("datum") + " • " + rsAllData.getString("broj_prijem")
            +" • "+rsAllData.getString("napomena"));
        }

        return data;
    }
    ArrayList listDataPodKat(int idLokacija, int idKategorija) throws SQLException{
        ArrayList data = new ArrayList<>();
        String sqlData = String.format("SELECT * FROM prijem_stavka WHERE aktivan and vazeci and id_podkategorija = %d and id_lokacija = %d",idKategorija,idLokacija);
        System.out.println(sqlData);
        PreparedStatement pstAllData = conSQL.prepareStatement(sqlData);
        ResultSet rsAllData = pstAllData.executeQuery();
        
        while (rsAllData.next()) {
            data.add(rsAllData.getString("naziv") + " • " + rsAllData.getString("korisnikLokacija") + " • " + rsAllData.getString("datum") + " • " + rsAllData.getString("broj_prijem")
            +" • "+rsAllData.getString("napomena"));
        }

        return data;
    }
    
    String brojDok(int idKategorija, int idLokacijaS, int idLokacijaN, String datum) throws SQLException{
        String datumDok = datum.replace("-", "");
        String sqlIzdavanje = "SELECT id_izdavanje FROM izdavanje WHERE aktivan AND vazeci AND id_izdavanje = (SELECT MAX(id_izdavanje) FROM izdavanje)";
        PreparedStatement pstIdStampac = conSQL.prepareStatement(sqlIzdavanje);
        ResultSet rsIdStampac = pstIdStampac.executeQuery();
        String id;
        if (rsIdStampac.next()) {

            id = rsIdStampac.getString("id_izdavanje");

        } else {
            id = null;
        }
        String brojDok = datumDok + "-" + "IZDAVANJE" + "-" + valueOf(idKategorija) + "-"+ valueOf(idLokacijaS)+valueOf(idLokacijaN)+ "-"+ id;
        return brojDok;
    }
    
    
    int kolicinaNaStanju(int idKategorija, String naziv) throws SQLException{
        int kolicinaPrijem = 0;
        String sqlKolPrijem = String.format("SELECT SUM(kolicina) as kolicinaPrijem FROM prijem_stavka WHERE aktivan and vazeci and naziv = '%s' and id_kategorija ="+idKategorija,naziv);
        PreparedStatement pstKolPrijem = conSQL.prepareStatement(sqlKolPrijem);
        ResultSet rsKolPrijem = pstKolPrijem.executeQuery();
        if(rsKolPrijem.next()){
            kolicinaPrijem = rsKolPrijem.getInt("kolicinaPrijem");
        }else{
            kolicinaPrijem = 0;
        }
        
        int kolicinaIzdavanje = 0;
        String sqlKolIzdavanje = String.format("SELECT SUM(kolicina) as kolicinaIzdavanje FROM izdavanje WHERE aktivan and vazeci and naziv = '%s' and id_kategorija ="+idKategorija,naziv);
        PreparedStatement pstKolIzdavanje = conSQL.prepareStatement(sqlKolIzdavanje);
        ResultSet rsKolIzdavanje = pstKolIzdavanje.executeQuery();
        if(rsKolIzdavanje.next()){
            kolicinaIzdavanje = rsKolIzdavanje.getInt("kolicinaIzdavanje");
        }else{
            kolicinaIzdavanje = 0;
        }
        
        int kolicina = kolicinaPrijem - kolicinaIzdavanje;
        
        return kolicina;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dataList = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(842, 495));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IZDAVANJE");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree2.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTree2);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Izaberite kategoriju");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Izbarite lokaciju");

        jScrollPane4.setViewportView(dataList);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Rezultat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
     try {
         DefaultMutableTreeNode kategorijeIzbor = (DefaultMutableTreeNode)jTree1.getSelectionPath().getLastPathComponent();
         selectKategorija = kategorijeIzbor.getUserObject().toString();
         System.out.println(selectKategorija);
         
         LoadLokacije();
     } catch (SQLException ex) {
         Logger.getLogger(Izdavanje.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTree2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree2MouseClicked
     try {
         int idkat;
         DefaultMutableTreeNode lokacijaIzbor = (DefaultMutableTreeNode)jTree2.getSelectionPath().getLastPathComponent();
         selectLokacija = lokacijaIzbor.getUserObject().toString();
         String paramKat = kat.getId(selectKategorija);
         int idlok = Integer.parseInt(lok.getId(selectLokacija));
         if(paramKat != null){
              idkat = Integer.parseInt(paramKat);
               puniTable(idlok,idkat);
         }else{
              idkat = podkat.getId(selectKategorija);
               puniTablePodKat(idlok,idkat);
         }
        
       
         
        
     } catch (SQLException ex) {
         Logger.getLogger(Izdavanje.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jTree2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> dataList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    // End of variables declaration//GEN-END:variables

    
}
