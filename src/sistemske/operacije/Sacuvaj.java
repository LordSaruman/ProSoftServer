/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Filip
 */
public class Sacuvaj extends AbstractSistemskaOperacija{

    private OpstiDomenskiObjekat list;

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        list = database.DBBroker.getInstance().sacuvaj((OpstiDomenskiObjekat) object);
    }

    public OpstiDomenskiObjekat getObject() {
        return list;
    }
}
