/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author milosjelic
 */
public class Evidencija extends javax.swing.JFrame {

    private static int id;
    private RacunariPregled rp;
    private RacunariEdit re;
    private Prijem prijem;
    private Izdavanje izdavanje;
    private Otpis otpis;
    private String korisnik;
    private String datum;
    private String username;
    private int tabbedCount;
    private ToneriPregled tp;
    private ToneriPrijem tprijem;

    /**
     * Creates new form Interface
     */
    public Evidencija() {
        initComponents();
    }

    Evidencija(String username) throws SQLException {
        initComponents();

        this.username = username;
        Kategorija kategorija = new Kategorija();
        List<String> sveKategorije = new ArrayList();
        sveKategorije = kategorija.getAll();
        LocalDate today = LocalDate.now();
        korisnik = username;
        datum = today.toString();
        userInfoLabel.setText(username);
        dateLabel.setText(today.toString());
        // AutoCompleteDecorator.decorate(kategorijeComboBox);
        // kategorijeComboBox.setModel(new DefaultComboBoxModel<>(sveKategorije.toArray(new String[0])));
        prijem = new Prijem(korisnik, datum);
        izdavanje = new Izdavanje();
        otpis = new Otpis();
        tp = new ToneriPregled();
        tprijem = new ToneriPrijem(korisnik, datum);

//        jTabbedPane1.addTab("Prijem", icon, prijem);
//        jTabbedPane1.addTab("Izdavanje", icon, izdavanje);
//        jTabbedPane1.addTab("Otpis", icon, otpis);
        jTabbedPane1.add(prijem, "Prijem");
        initTabComponent(0);
        jTabbedPane1.add(izdavanje, "Izdavanje");
        initTabComponent(1);
        jTabbedPane1.add(otpis, "Otpis");
        initTabComponent(2);

    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userInfoLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        naslovLabel = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        userInfoLabel.setText("jLabel1");

        dateLabel.setText("0000-00-00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(userInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateLabel))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(userInfoLabel)
                .addComponent(dateLabel))
        );

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(842, 495));

        naslovLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        naslovLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        naslovLabel.setText("Prijem/Izdavanje/Otpis");

        infoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Prijem/Otipis/Izdavanje");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu2MouseReleased(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem10.setText("Prijem");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Otpis");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem9.setText("Izdavanje");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Toneri");

        jMenuItem3.setText("Pretraga");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem13.setText("Prijem");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem4.setText("Izmena");
        jMenu4.add(jMenuItem4);

        jMenuItem12.setText("Poručivanje");
        jMenu4.add(jMenuItem12);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Računari");

        jMenuItem5.setText("Pretraga");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem7.setText("Izmena");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Štampači");

        jMenuItem6.setText("Pretraga");
        jMenu6.add(jMenuItem6);

        jMenuItem8.setText("Izmena");
        jMenu6.add(jMenuItem8);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Profil");

        jMenuItem1.setText("Promena lozinke");
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Odjava");
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(naslovLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(naslovLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoLabel)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleParent(this);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Prijem");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseReleased


    }//GEN-LAST:event_jMenu2MouseReleased

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseReleased
        try {
            tp = new ToneriPregled(korisnik, datum);
            jTabbedPane1.add(tp, "Toneri pregled");
            tabbedCount = jTabbedPane1.getTabCount();
            initTabComponent(tabbedCount - 1);
            jTabbedPane1.setSelectedIndex(tabbedCount - 1);
        } catch (SQLException ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3MouseReleased

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        otpis = new Otpis();
        jTabbedPane1.add(otpis, "Otpis");
        tabbedCount = jTabbedPane1.getTabCount();
        initTabComponent(tabbedCount - 1);
        jTabbedPane1.setSelectedIndex(tabbedCount - 1);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            izdavanje = new Izdavanje();
            jTabbedPane1.add(izdavanje, "Izdavanje");
            tabbedCount = jTabbedPane1.getTabCount();
            initTabComponent(tabbedCount - 1);
            jTabbedPane1.setSelectedIndex(tabbedCount - 1);
        } catch (SQLException ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try {
            prijem = new Prijem(username, datum);
            jTabbedPane1.add(prijem, "Prijem");
            tabbedCount = jTabbedPane1.getTabCount();
            initTabComponent(tabbedCount - 1);
            jTabbedPane1.setSelectedIndex(tabbedCount - 1);
        } catch (SQLException ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            rp = new RacunariPregled();
            jTabbedPane1.add(rp, "Računari Pregled");
            tabbedCount = jTabbedPane1.getTabCount();
            initTabComponent(tabbedCount - 1);
            jTabbedPane1.setSelectedIndex(tabbedCount - 1);
        } catch (SQLException ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            re = new RacunariEdit(korisnik);
            jTabbedPane1.add(re, "Računari Izmena");
            tabbedCount = jTabbedPane1.getTabCount();
            initTabComponent(tabbedCount - 1);
            jTabbedPane1.setSelectedIndex(tabbedCount - 1);
        } catch (SQLException ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        try {
            tprijem = new ToneriPrijem(korisnik, datum);
            jTabbedPane1.add(tprijem, "Toneri Prijem");
            tabbedCount = jTabbedPane1.getTabCount();
            initTabComponent(tabbedCount - 1);
            jTabbedPane1.setSelectedIndex(tabbedCount - 1);
        } catch (SQLException ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Evidencija.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Evidencija().setVisible(true);
        });
    }

    String getUser() {
        String user = korisnik;

        return user;
    }

    String getDatum() {
        String datum1 = datum;

        return datum1;
    }

    private void initTabComponent(int i) {
        jTabbedPane1.setTabComponentAt(i, new ButtonTabComponent(jTabbedPane1));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel naslovLabel;
    private javax.swing.JLabel userInfoLabel;
    // End of variables declaration//GEN-END:variables

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
