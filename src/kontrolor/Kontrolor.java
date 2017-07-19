/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolor;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import sistemske.operacije.AbstractSistemskaOperacija;

import java.util.ArrayList;
import java.util.List;
import sistemske.operacije.Sacuvaj;
import sistemske.operacije.SacuvajRezultate;
import sistemske.operacije.VratiListu;

/**
 *
 * @author filip_000
 */
public class Kontrolor {

    private static Kontrolor instance;
    private ArrayList<OpstiDomenskiObjekat> listaUlogovanihKorisnika = null;
    
    private Kontrolor() {
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

    public ArrayList<OpstiDomenskiObjekat> vratiListuUlogovanihKorisnika(Korisnik korisnik) {
        return listaUlogovanihKorisnika;
    }

    public void sacuvajListuUlogovanihKorisnika(List<OpstiDomenskiObjekat> list) {
        for (OpstiDomenskiObjekat odo : list) {
            if (!listaUlogovanihKorisnika.contains(odo)) {
                listaUlogovanihKorisnika.add(odo);
            }
        }
    }

}
