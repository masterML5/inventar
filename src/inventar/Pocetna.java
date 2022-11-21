/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author milosjelic
 */
public class Pocetna extends javax.swing.JFrame {

    private static Connection conSQL;
    private static final String connectionUrlMySQL = "jdbc:mysql://localhost:3306/it-inventar?user=root&password=";

    /**
     * Creates new form Pocetna
     * @throws java.io.IOException
     */
    public Pocetna() throws IOException {
        initComponents();

        try {
            conSQL = DriverManager.getConnection(connectionUrlMySQL);
            conSQL.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        Image logo = createImageIcon("/res/pionir-logo.png", "logo").getImage().getScaledInstance(90, 48, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logo);
        logoLabel.setIcon(logoIcon);

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
        logoLabel = new javax.swing.JLabel();
        naslovLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        podnaslovLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        lozinkaField = new javax.swing.JPasswordField();
        korisnickoTextField = new javax.swing.JTextField();
        korisnickoLabel = new javax.swing.JLabel();
        lozinkaLabel = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();
        registracijaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IT Inventar");
        setResizable(false);

        logoLabel.setText("jLabel1");

        naslovLabel.setFont(new java.awt.Font("Tahoma", 3, 30)); // NOI18N
        naslovLabel.setText("IT INVENTAR PIONIR D.O.O");

        podnaslovLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        podnaslovLabel.setText("Prijava");

        loginButton.setText("Login");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loginButtonKeyReleased(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetButtonMouseClicked(evt);
            }
        });
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        korisnickoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                korisnickoTextFieldActionPerformed(evt);
            }
        });

        korisnickoLabel.setText("Username");

        lozinkaLabel.setText("Password");

        showPassword.setText("Show password");
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(korisnickoTextField)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lozinkaField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(korisnickoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lozinkaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(showPassword)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(korisnickoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(korisnickoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lozinkaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lozinkaField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(showPassword)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(resetButton))
                .addGap(79, 79, 79))
        );

        registracijaButton.setText("Registracija");
        registracijaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registracijaButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(podnaslovLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                .addComponent(naslovLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(278, 278, 278))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(registracijaButton)
                                .addGap(51, 51, 51))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(naslovLabel))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(podnaslovLabel)
                .addGap(62, 62, 62)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(registracijaButton)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetButtonActionPerformed

    private void resetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseClicked
        korisnickoTextField.setText(null);
        lozinkaField.setText(null);
    }//GEN-LAST:event_resetButtonMouseClicked

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        try {
            String username = korisnickoTextField.getText();
            String password = lozinkaField.getText();

            // String generatedSecuredPasswordHash = BCrypt.hashpw(test, BCrypt.gensalt(12));
            String sqlCheck = "SELECT * FROM login WHERE aktivan AND vazeci AND username = '" + username + "'";
            PreparedStatement pstCheck = conSQL.prepareStatement(sqlCheck);
            ResultSet rsCheck = pstCheck.executeQuery();
            String password2 = "";
            if (rsCheck.next()) {
                password2 = rsCheck.getString("password");
            }

            if (password2.isEmpty() || password2 == null) {
                JOptionPane.showMessageDialog(null, "Pogresna lozinka ili korisnicko ime!");
            } else {
                boolean matched = BCrypt.checkpw(password, password2);
                if (matched) {
                    new Evidencija(username).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Pogresna lozinka ili korisnicko ime!");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginButtonMouseClicked
  
    private void korisnickoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_korisnickoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_korisnickoTextFieldActionPerformed

    private void registracijaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registracijaButtonMouseClicked
        try {
            JPasswordField pwd = new JPasswordField();
            int passwordFrame = JOptionPane.showConfirmDialog(null, pwd, "Unesite Lozinku", JOptionPane.OK_CANCEL_OPTION);
            String password = "";
            if (passwordFrame == 0) {
                password = new String(pwd.getPassword());
            }
            String sqlCheckAdmin = "SELECT * FROM login WHERE aktivan AND vazeci AND username = 'administrator'";
            PreparedStatement pstCheck = conSQL.prepareStatement(sqlCheckAdmin);
            ResultSet rsCheck = pstCheck.executeQuery();
            String password2 = "";
            if (rsCheck.next()) {
                try {
                    password2 = rsCheck.getString("password");
                } catch (SQLException ex) {
                    Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            boolean matched = BCrypt.checkpw(password, password2);
            if (matched) {
                new Register().setVisible(true);
                this.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registracijaButtonMouseClicked

    private void loginButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyReleased
        
    }//GEN-LAST:event_loginButtonKeyReleased

    private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
      if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             try {
            String username = korisnickoTextField.getText();
            String password = lozinkaField.getText();

            // String generatedSecuredPasswordHash = BCrypt.hashpw(test, BCrypt.gensalt(12));
            String sqlCheck = "SELECT * FROM login WHERE aktivan AND vazeci AND username = '" + username + "'";
            PreparedStatement pstCheck = conSQL.prepareStatement(sqlCheck);
            ResultSet rsCheck = pstCheck.executeQuery();
            String password2 = "";
            if (rsCheck.next()) {
                password2 = rsCheck.getString("password");
            }

            if (password2.isEmpty() || password2 == null) {
                JOptionPane.showMessageDialog(null, "Pogresna lozinka ili korisnicko ime!");
            } else {
                boolean matched = BCrypt.checkpw(password, password2);
                if (matched) {
                    new Evidencija(username).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Pogresna lozinka ili korisnicko ime!");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_loginButtonKeyPressed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
       if(showPassword.isSelected()){
           lozinkaField.setEchoChar((char) 0);
       }else{
           lozinkaField.setEchoChar('*');
       }
    }//GEN-LAST:event_showPasswordActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pocetna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pocetna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pocetna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pocetna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Pocetna().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel korisnickoLabel;
    private javax.swing.JTextField korisnickoTextField;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPasswordField lozinkaField;
    private javax.swing.JLabel lozinkaLabel;
    private javax.swing.JLabel naslovLabel;
    private javax.swing.JLabel podnaslovLabel;
    private javax.swing.JButton registracijaButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JCheckBox showPassword;
    // End of variables declaration//GEN-END:variables
}
