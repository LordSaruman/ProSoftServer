/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Korisnik;

/**
 *
 * @author filip_000
 */
public class SacuvajKorisnika extends AbstractSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        database.DBBroker.getInstance().sacuvajKorisnika((Korisnik) object);
    }
    
}
