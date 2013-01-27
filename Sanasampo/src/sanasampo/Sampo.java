/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;
import sanasampo.logic.Haku;
import sanasampo.ui.Kayttoliittyma;

public class Sampo {

    Kayttoliittyma ui;
    Hakemisto h;
    Ruudukko r;
    String input;
    Haku haku;

    public void kaynnista() throws FileNotFoundException, IOException {
        asenna();
        System.out.println("Prompting user for input..");
        kysyKirjaimet();
        while (!r.alusta(input)) {
            System.out.println("Invalid grid!\nReturned user to prompt.");
            kysyKirjaimet();
        }
        System.out.println("Grid input passed!\nStarting search..");
        hae();
        System.out.println("Search finished succesfully!\nRendering GUI..");
        nayta();
    }

    private void asenna() throws FileNotFoundException, IOException {
        System.out.println("Loading dictionaries..");
        h = new Hakemisto(new Sanakirja());
        System.out.println("Dictionaries loaded!");
        r = new Ruudukko();
    }

    private void kysyKirjaimet() {
        input = JOptionPane.showInputDialog(null, "Anna kirjaimet: ", "Ruudukon valinta", 1);
    }

    private void hae() {
        haku = new Haku(h, r);
        haku.kaynnista();
    }

    private void nayta() {
        ui = new Kayttoliittyma(r, haku.getTulos());
        SwingUtilities.invokeLater(ui);
        System.out.println("GUI Rendered!");
    }
}