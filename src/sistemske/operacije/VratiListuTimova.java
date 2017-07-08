/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Tim;
import java.util.ArrayList;

/**
 *
 * @author filip_000
 */
public class VratiListuTimova extends AbstractSistemskaOperacija{

    private ArrayList<Tim> listaTimova;
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        listaTimova = database.DBBroker.getInstance().getListuTimova();
    }

    public ArrayList<Tim> getListuTimova() {
        return listaTimova;
    }
    
}
