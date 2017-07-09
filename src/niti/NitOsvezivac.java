/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forma.ServerskaForma;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.NitZatvaranje;
import server.PokreniServer;

/**
 *
 * @author Filip
 */
public class NitOsvezivac extends Thread {

    private final ServerskaForma forma;

    public NitOsvezivac(ServerskaForma forma) {
        this.forma = forma;
    }

    @Override
    public void run() {
        while (true) {
            forma.osveziFormu();

            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitZatvaranje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
