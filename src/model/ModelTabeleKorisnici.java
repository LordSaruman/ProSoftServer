/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filip_000
 */
public class ModelTabeleKorisnici extends AbstractTableModel{

    String kolone[] = new String[]{"Name of The User", "Username of The User", "Username"};
    ArrayList<OpstiDomenskiObjekat> spisakKorisnika;

    public ModelTabeleKorisnici(ArrayList<OpstiDomenskiObjekat> spisakKorisnika) {
        this.spisakKorisnika = spisakKorisnika;
    }
    
    @Override
    public int getRowCount() {
        if (spisakKorisnika == null) {
            return 0;
        }
        return spisakKorisnika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik korisnik = (Korisnik) spisakKorisnika.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return korisnik.getImeKorisnika();
            case 1:
                return korisnik.getPrezimeKorisnika();
            case 2:
                return korisnik.getUsername();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void setSpisakKorisnika(ArrayList<OpstiDomenskiObjekat> spisakKorisnika){
        this.spisakKorisnika = spisakKorisnika;
        fireTableDataChanged();
    }
    
}
