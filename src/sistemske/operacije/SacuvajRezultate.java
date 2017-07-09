/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.OpstiDomenskiObjekat;
import domen.Rezultat;
import java.util.List;

/**
 *
 * @author Filip
 */
public class SacuvajRezultate extends AbstractSistemskaOperacija {

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        List<OpstiDomenskiObjekat> lista = (List<OpstiDomenskiObjekat>) object;
        for (int i = 0; i < lista.size(); i++) {
            database.DBBroker.getInstance().sacuvajRezultat((Rezultat) lista.get(i), i + 1);
        }
    }

}
