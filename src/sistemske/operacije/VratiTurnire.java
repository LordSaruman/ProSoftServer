/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Turnir;
import java.util.ArrayList;

/**
 *
 * @author filip_000
 */
public class VratiTurnire extends AbstractSistemskaOperacija{

    private ArrayList<Turnir> lista = new ArrayList<Turnir>();
    
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        lista = database.DBBroker.getInstance().getListuTurnira();
    }

    public ArrayList<Turnir> getLista() {
        return lista;
    }
}
