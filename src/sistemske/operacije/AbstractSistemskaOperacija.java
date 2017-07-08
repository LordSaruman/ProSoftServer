/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import database.DBBroker;

/**
 *
 * @author filip_000
 */
public abstract class AbstractSistemskaOperacija {
    
    public final void izvrsiOperaciju(Object object) throws Exception {
        try {
            ucitajDrajver();
            otvoriKonekciju();
            proveriPreduslove(object);
            izvrsiKonretnuSO(object);
            commitTransaction();
        } catch (Exception e) {
            rollBackTransaction();
            throw e;
        }finally{
            zatvoriKonekciju();
        }
    }
    
    private void ucitajDrajver() throws Exception{
        DBBroker.getInstance().ucitajDrajver();
    }
    
    private void otvoriKonekciju() throws Exception{
        DBBroker.getInstance().otvoriKonekciju();
    }
    
    protected abstract void proveriPreduslove(Object object) throws Exception;
    
    protected abstract void izvrsiKonretnuSO(Object object) throws Exception;
    
    private void commitTransaction() throws Exception{
        DBBroker.getInstance().commit();
    }
    
    private void rollBackTransaction() throws Exception{
        DBBroker.getInstance().rollback();
    }
    
    private void zatvoriKonekciju() throws Exception{
        DBBroker.getInstance().zatvoriKonekciju();
    }

}
