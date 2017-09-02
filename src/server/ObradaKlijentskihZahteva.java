/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.OpstiDomenskiObjekat;
import domen.Tim;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontrolor.Kontrolor;
import operacije.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.StatusZahteva;

/**
 *
 * @author filip_000
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket s;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        while (!kraj) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            try {
                switch (kz.getOperacija()) {
                    case Operacija.SACUVAJ:
                        OpstiDomenskiObjekat listaODO = Kontrolor.getInstance().sacuvaj((OpstiDomenskiObjekat) kz.getParametar());
                        so.setOdgovor(listaODO);
                        break;

                    case Operacija.VRATI_LISTU:
                        ArrayList<OpstiDomenskiObjekat> listaD = Kontrolor.getInstance().vratiListu((OpstiDomenskiObjekat) kz.getParametar());
                        so.setOdgovor(listaD);
                        break;

                    case Operacija.SACUVAJ_REZULTATE:
                        Kontrolor.getInstance().sacuvajRezultate((List<OpstiDomenskiObjekat>) kz.getParametar());
                        break;

                    case Operacija.PROSLEDI_LISTU_ULOGOVANIH_KORISNIKA:
                        Kontrolor.getInstance().sacuvajListuUlogovanihKorisnika((List<OpstiDomenskiObjekat>) kz.getParametar());
                        break;

                    case Operacija.OBRISI:
                        Kontrolor.getInstance().obrisiTim((Tim) kz.getParametar());
                        break;
                        
                    case Operacija.IZMENI:
                        Kontrolor.getInstance().izmeniTim((OpstiDomenskiObjekat) kz.getParametar());
                        break;
                }
                so.setStatusZahteva(StatusZahteva.USPESAN_ZAHTEV);
            } catch (Exception exception) {

                so.setStatusZahteva(StatusZahteva.NEUSPESAN_ZAHTEV);
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {

        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        return kz;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
