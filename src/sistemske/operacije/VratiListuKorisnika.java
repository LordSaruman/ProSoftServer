/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author filip_000
 */
public class VratiListuKorisnika extends AbstractSistemskaOperacija{

    private ArrayList<Korisnik> lista;
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        lista = database.DBBroker.getInstance().getListuKorisnika();
    }
    
    public ArrayList<Korisnik> getListuKorisnika(){
        return lista;
    }
}
