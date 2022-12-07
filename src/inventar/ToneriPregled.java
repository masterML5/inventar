/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author milosjelic
 */
public class ToneriPregled extends javax.swing.JPanel {
    
    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";
    private static DefaultTableModel tm;
    Lokacija lokacija = new Lokacija();
    ArrayList<String> sveLokacije = lokacija.getAll();
    ToneriIzdavanje tizdavanje = new ToneriIzdavanje();
    private static String korisnik;
    private static String datum;

    /**
     * Creates new form ToneriPregled
     *
     * @throws java.sql.SQLException
     */
    public ToneriPregled() throws SQLException {
        initComponents();
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ToneriPregled(String username, String datum) throws SQLException {
        initComponents();
        tm = (DefaultTableModel) toneriTable.getModel();
        TableColumnModel tcm = toneriTable.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(50);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(450);
        tcm.getColumn(3).setPreferredWidth(50);
        tcm.removeColumn(tcm.getColumn(4));
        tcm.removeColumn(tcm.getColumn(4));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        toneriTable.setDefaultRenderer(String.class, centerRenderer);
        toneriTable.setDefaultRenderer(Integer.class, centerRenderer);
        ImageIcon refreshII = new ImageIcon(getClass().getResource("/res/refresh.png"));
        Image refreshI = refreshII.getImage();
        Image newRefreshBtn = refreshI.getScaledInstance(20, 20,java.awt.Image.SCALE_SMOOTH);
        Icon iconBtn = new ImageIcon(newRefreshBtn);
        
        refreshBtn.setIcon(iconBtn);
        korisnik = username;
        this.datum = datum;
        sveLokacije.add(0, null);
        lokacijaComboBox.setModel(new DefaultComboBoxModel<>(sveLokacije.toArray(new String[0])));
        AutoCompleteDecorator.decorate(lokacijaComboBox);
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        puniTable();

        Image red = createImageIcon("/res/red.png", "redIcon").getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon redIconPng = new ImageIcon(red);
        redIcon.setIcon(redIconPng);
        Image green = createImageIcon("/res/green.png", "greenIcon").getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon greenIconPng = new ImageIcon(green);
        greenIcon.setIcon(greenIconPng);
        Image orange = createImageIcon("/res/orange.png", "orangeIcon").getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon orangeIconPng = new ImageIcon(orange);
        orangeIcon.setIcon(orangeIconPng);
    }

    ArrayList getToneri() throws SQLException {
        String sql = "SELECT ton.id_toner,ton.naziv, ton.prep_kolicina, ton.kolicina, GROUP_CONCAT(distinct stmp.marka,\" \",stmp.model SEPARATOR '----') as mm,"
                + " GROUP_CONCAT(DISTINCT lok.naziv) as lokacija FROM toneri AS ton, stampaci AS stmp, lokacija AS lok WHERE"
                + " ton.id_toner = stmp.id_toner AND stmp.id_lokacija = lok.id_lokacija GROUP BY ton.id_toner";
        PreparedStatement pstAllToneri = conSQL.prepareStatement(sql);
        ResultSet rsAllToneri = pstAllToneri.executeQuery();
        ArrayList toneri = new ArrayList();
        while (rsAllToneri.next()) {
            toneri.add(rsAllToneri.getInt("ton.id_toner") + " • " + rsAllToneri.getString("ton.naziv") + " • " + rsAllToneri.getString("mm") + " • " + rsAllToneri.getInt("ton.kolicina")
                    + " • " + rsAllToneri.getInt("ton.prep_kolicina") + " • " + rsAllToneri.getString("lokacija"));
        }

        return toneri;
    }

    ArrayList getAllToneri() throws SQLException {
        String sqlToneri = "SELECT naziv FROM toneri WHERE aktivan AND vazeci GROUP BY naziv";
        PreparedStatement pstToneri = conSQL.prepareStatement(sqlToneri);
        ResultSet rsToneri = pstToneri.executeQuery();
        ArrayList toneriAll = new ArrayList();
        while (rsToneri.next()) {
            toneriAll.add(rsToneri.getString("naziv"));
        }

        return toneriAll;
    }

    void puniTable() throws SQLException {
       
        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ArrayList toneri = new ArrayList<>();
         toneri = getToneri();
        for (int i = 0; i < toneri.size(); i++) {
            String data = toneri.get(i).toString();
            String[] a = data.split("•");
            tm.addRow(a);
        }
        for (int j = 0; j < tm.getRowCount(); j++) {
            int kolicina = Integer.parseInt(tm.getValueAt(j, 3).toString().trim());
            int prep_kolicina = Integer.parseInt(tm.getValueAt(j, 4).toString().trim());
            if (kolicina < prep_kolicina) {
                toneriTable.setValueAt("<html><p color='red'><b>" + kolicina + "</b></p></html>", j, 3);
            } else if (kolicina == prep_kolicina) {
                toneriTable.setValueAt("<html><p color='FF8C00'><b>" + kolicina + "</b></p></html>", j, 3);
            } else {
                toneriTable.setValueAt("<html><p color='green'><b>" + kolicina + "</b></p></html>", j, 3);
            }

        }
        
    }
    
    void refreshTable() throws SQLException{
        
       DefaultTableModel dm = (DefaultTableModel) toneriTable.getModel();
        int rowCount = dm.getRowCount();
       
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        tm = new DefaultTableModel();
        tm = (DefaultTableModel) toneriTable.getModel();
        
        puniTable();
        toneriTable.repaint();
        
    }

    String getTonerById(int idToner) throws SQLException {
        String sqlIdToner = "SELECT naziv from toneri where aktivan and vazeci and id_toner =" + idToner;
        PreparedStatement pstIdToner = conSQL.prepareStatement(sqlIdToner);
        String toner;
        ResultSet rsIdToner = pstIdToner.executeQuery();

        if (rsIdToner.next()) {

            toner = rsIdToner.getString("naziv");

        } else {
            toner = "Nema rezultata";
        }

        return toner;
    }

    int getKolicinaById(int idToner) throws SQLException {
        String sqlIdToner = "SELECT kolicina from toneri where aktivan and vazeci and id_toner =" + idToner;
        PreparedStatement pstIdToner = conSQL.prepareStatement(sqlIdToner);
        int kolicina;
        ResultSet rsIdToner = pstIdToner.executeQuery();

        if (rsIdToner.next()) {

            kolicina = rsIdToner.getInt("kolicina");

        } else {
            kolicina = 0;
        }

        return kolicina;
    }

    public void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(tm);
        toneriTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));

    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        toneriTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lokacijaComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        redIcon = new javax.swing.JLabel();
        greenIcon = new javax.swing.JLabel();
        orangeIcon = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Toneri");

        toneriTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Toner", "Toner", "Štampač", "Količina", "PrepKol", "Lokacija"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        toneriTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(toneriTable);
        if (toneriTable.getColumnModel().getColumnCount() > 0) {
            toneriTable.getColumnModel().getColumn(0).setResizable(false);
            toneriTable.getColumnModel().getColumn(3).setResizable(false);
            toneriTable.getColumnModel().getColumn(4).setResizable(false);
            toneriTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Pretraga")));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pretraga po lokaciji"));

        lokacijaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Filtriraj");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lokacijaComboBox, 0, 140, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lokacijaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setText("Izdavanje");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Legenda"));

        redIcon.setText("Potrebno naručiti");

        greenIcon.setText("Dovoljno ");

        orangeIcon.setText("Uskoro naručiti");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(redIcon)
                    .addComponent(greenIcon)
                    .addComponent(orangeIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(redIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orangeIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(refreshBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(7, 7, 7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String query = jTextField1.getText();
        filter(query);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        String query;
        if (lokacijaComboBox.getSelectedIndex() == -1) {
            query = "";
        } else {
            String lok = lokacijaComboBox.getSelectedItem().toString();
            query = lok;
        }
        filter(query);
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       
            
        if(toneriTable.getSelectedRow() != -1){
        try {
            int tonerRow = toneriTable.getSelectedRow();
            int tonerCol = 0;
            int tonerColPrepKol = 4;
            String idTonerStr = (String) toneriTable.getValueAt(tonerRow, tonerCol);
            String prepKol = (String) tm.getValueAt(tonerRow, tonerColPrepKol);

            int idToner = Integer.parseInt(idTonerStr.trim());
            int kolicina = getKolicinaById(idToner);
            if(kolicina == 0){
                JOptionPane.showMessageDialog(null, "Nemate na stanju izabrani toner", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int prep_kolicina = Integer.parseInt(prepKol.trim());
            tizdavanje = new ToneriIzdavanje(korisnik, getTonerById(idToner), idToner, kolicina, prep_kolicina, datum);
            tizdavanje.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(ToneriPregled.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Morate izabrati toner", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_jButton2MouseClicked

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        try {
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ToneriPregled.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel greenIcon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> lokacijaComboBox;
    private javax.swing.JLabel orangeIcon;
    private javax.swing.JLabel redIcon;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable toneriTable;
    // End of variables declaration//GEN-END:variables
}
