/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milosjelic
 */
public class Inventar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Pocetna().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Inventar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
