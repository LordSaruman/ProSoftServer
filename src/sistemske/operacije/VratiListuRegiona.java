/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Region;
import java.util.ArrayList;

/**
 *
 * @author filip_000
 */
public class VratiListuRegiona extends AbstractSistemskaOperacija{

    private ArrayList<Region> listaRegiona;
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        listaRegiona = database.DBBroker.getInstance().getListuRegiona();
    }
    
    public ArrayList<Region> getListuRegiona(){
        return listaRegiona;
    }
    
}
