/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Tim;

/**
 *
 * @author filip_000
 */
public class SacuvajTim extends AbstractSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        database.DBBroker.getInstance().sacuvajTim((Tim) object);
    }
    
}
