/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import java.util.ArrayList;

/**
 *
 * @author filip_000
 */
public class VratiIgre extends AbstractSistemskaOperacija{

    private ArrayList<String> listaIgara;
    
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        listaIgara = database.DBBroker.getInstance().getListuIgara();
    }

    public ArrayList<String> getListaIgara() {
        return listaIgara;
    }
}
