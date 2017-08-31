/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolor;

import domen.DatabaseParameters;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import domen.Tim;
import forma.ServerskaForma;
import java.io.IOException;
import sistemske.operacije.AbstractSistemskaOperacija;
import java.util.ArrayList;
import java.util.List;
import sistemske.operacije.ObrisiTim;
import sistemske.operacije.Sacuvaj;
import sistemske.operacije.SacuvajRezultate;
import sistemske.operacije.VratiListu;
import util.DBUtil;

/**
 *
 * @author filip_000
 */
public class Kontrolor {

    private static Kontrolor instance;
    private ArrayList<OpstiDomenskiObjekat> listaUlogovanihKorisnika;
    private ServerskaForma aplikacija;

    public void setAplikacija(ServerskaForma aplikacija) {
        this.aplikacija = aplikacija;
    }

    private Kontrolor() {
        listaUlogovanihKorisnika = new ArrayList<>();
    }

    public static Kontrolor getInstance() {
        if (instance == null) {
            instance = new Kontrolor();
        }
        return instance;
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat opstiDomenskiObjekat) throws Exception {
        VratiListu aso = new VratiListu();
        aso.izvrsiOperaciju(opstiDomenskiObjekat);
        return aso.getList();
    }

    public OpstiDomenskiObjekat sacuvaj(OpstiDomenskiObjekat opstiDomenskiObjekat) throws Exception {
        AbstractSistemskaOperacija aso = new Sacuvaj();
        aso.izvrsiOperaciju(opstiDomenskiObjekat);
        return opstiDomenskiObjekat;
    }

    public void sacuvajRezultate(List<OpstiDomenskiObjekat> list) throws Exception {
        AbstractSistemskaOperacija aso = new SacuvajRezultate();
        aso.izvrsiOperaciju(list);
    }

    public DatabaseParameters vratiParametre() throws IOException {
        DBUtil dBUtil = new DBUtil();
        String url = dBUtil.vratiUrl();
        String username = dBUtil.vratiKorisnika();
        String password = dBUtil.vratiSifru();
        
        return new DatabaseParameters(url, username, password);
    }

    public void sacuvajParametre(DatabaseParameters databaseParameters) throws IOException {

        DBUtil dBUtil = new DBUtil();
        dBUtil.postaviParametre(
                databaseParameters.getURL(),
                databaseParameters.getUsername(),
                databaseParameters.getPassword()
        );
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListuUlogovanihKorisnika(Korisnik korisnik) {
        return listaUlogovanihKorisnika;
    }

    public void sacuvajListuUlogovanihKorisnika(List<OpstiDomenskiObjekat> list) {
        for (OpstiDomenskiObjekat odo : list) {
            if (!listaUlogovanihKorisnika.contains(odo)) {
                listaUlogovanihKorisnika.add(odo);
            }
        }
        aplikacija.osveziTabeluUlogovanihKorisnika();
    }

    public void obrisiTim(Tim tim) throws Exception {
        AbstractSistemskaOperacija aso = new ObrisiTim();
        aso.izvrsiOperaciju(tim);
    }

}
