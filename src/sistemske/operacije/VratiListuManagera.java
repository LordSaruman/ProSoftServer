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
 * @author Nikola
 */
public class VratiListuManagera extends AbstractSistemskaOperacija{

    private ArrayList<String> listaTimova;
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        listaTimova = database.DBBroker.getInstance().getListaManagera();
    }

    public ArrayList<String> getListaManagera() {
        return listaTimova;
    }
}
