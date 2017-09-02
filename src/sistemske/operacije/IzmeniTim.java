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
public class IzmeniTim extends AbstractSistemskaOperacija{

    boolean flag = true;
    
    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiKonretnuSO(Object object) throws Exception {
        flag = database.DBBroker.getInstance().izmeniTim((Tim) object);
    }
    
}
