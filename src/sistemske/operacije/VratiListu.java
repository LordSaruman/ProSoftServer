/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class VratiListu extends AbstractSistemskaOperacija{

    private ArrayList<OpstiDomenskiObjekat> list;

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        list = database.DBBroker.getInstance().vratiListu((OpstiDomenskiObjekat) object);
    }

    public ArrayList<OpstiDomenskiObjekat> getList() {
        return list;
    }
}
