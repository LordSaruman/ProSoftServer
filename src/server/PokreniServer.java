/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forma.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import kontrolor.Kontrolor;

/**
 *
 * @author filip_000
 */
public class PokreniServer extends Thread{
    
    ServerskaForma sf;

    public PokreniServer(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server je pokrenut");
            sf.serverPokrenut();
            NitZatvaranje nz = new NitZatvaranje(ss, this);
            nz.start();
            
            while (!isInterrupted()) {                
                Socket s = ss.accept();
                System.out.println("Klijent se povezao");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                okz.start();
            }
        } catch (IOException ex) {
            sf.serverNijePokrenut();
            System.out.println("Server je zaustavljen");
        }
    }
    
    
}
