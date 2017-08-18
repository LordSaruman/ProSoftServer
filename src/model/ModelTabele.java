/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.OpstiDomenskiObjekat;
import domen.Rezultat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filip_000
 */
public class ModelTabele extends AbstractTableModel {

    String kolone[] = new String[]{"Rang", "Team", "Tournament", "Result"};
    ArrayList<OpstiDomenskiObjekat> spisakRezultata;

    public ModelTabele(ArrayList<OpstiDomenskiObjekat> spisakRezultata) {
        this.spisakRezultata = spisakRezultata;
    }

    @Override
    public int getRowCount() {
        return spisakRezultata.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Rezultat rezultat = (Rezultat) spisakRezultata.get(row);

        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return rezultat.getTim().getNaziv();
            case 2:
                return rezultat.getTurnir();
            case 3:
                return rezultat.getRezultat();
            default:
                return "N/A";
        }
    }

    public void setSpisakRezultata(ArrayList<OpstiDomenskiObjekat> spisakRezultata) {
        this.spisakRezultata = spisakRezultata;
        fireTableDataChanged();
    }

}
