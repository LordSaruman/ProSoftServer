/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Lokacija;
import java.util.ArrayList;

/**
 *
 * @author filip_000
 */
public class VratiListuLokacija extends AbstractSistemskaOperacija{

    private ArrayList<Lokacija> listaLokacija;
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        listaLokacija = database.DBBroker.getInstance().getListuLokacija();
    }

    public ArrayList<Lokacija> getListuLokacija() {
        return listaLokacija;
    }
    
}
