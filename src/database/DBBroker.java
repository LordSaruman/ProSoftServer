/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domen.Korisnik;
import domen.Lokacija;
import domen.OpstiDomenskiObjekat;
import domen.Region;
import domen.Rezultat;
import domen.Serija;
import domen.Tim;
import domen.Turnir;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBUtil;

/**
 *
 * @author filip_000
 */
public class DBBroker {

    private static DBBroker dbbInstance;

    private DBBroker() {
    }

    public static DBBroker getInstance() {
        if (dbbInstance == null) {
            dbbInstance = new DBBroker();
        }
        return dbbInstance;
    }

    Connection konekcija;

    public void ucitajDrajver() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Nije ucitan drajver");
        }
    }

    public void otvoriKonekciju() throws IOException {
        try {
            DBUtil dBUtil = new DBUtil();
            String url = dBUtil.vratiUrl();
            String user = dBUtil.vratiKorisnika();
            String password = dBUtil.vratiSifru();
            konekcija = DriverManager.getConnection(url, user, password);
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Nije otvorena konekcija");
        }
    }

    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            System.err.println("Nije zatvorena konekcija");
        }
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            System.out.println("Nije izvrsen commit");
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            System.out.println("Nije izvrsen rollback");
        }
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        try {
            String sql = "SELECT * FROM " + odo.vratiJoin();
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);

            return (ArrayList<OpstiDomenskiObjekat>) odo.vratiListu(rs);
        } catch (SQLException e) {
            throw new Exception("Neuspesno vadjenje podataka o korisniku", e);
        }
    }

    public OpstiDomenskiObjekat sacuvaj(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES (" + odo.vratiVrednostiZaInsert() + ")";
            Statement statement = konekcija.createStatement();
            statement.executeUpdate(upit);
            statement.close();
            return odo;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    public ArrayList<Region> getListuRegiona() throws SQLException {
        ArrayList<Region> listaRegiona = new ArrayList<>();

        String sql = "SELECT * FROM region";

        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            int idRegiona = rs.getInt("idRegiona");
            String nazivRegiona = rs.getString("nazivRegiona");

            Region r = new Region(idRegiona, nazivRegiona);
            listaRegiona.add(r);
        }
        return listaRegiona;
    }

    public ArrayList<Lokacija> getListuLokacija() throws SQLException {
        ArrayList<Lokacija> listaLokacija = new ArrayList<>();
        ArrayList<Region> listaRegiona = new ArrayList<>();
        Region r = null;

        listaRegiona = getListuRegiona();
        String sql = "SELECT * FROM lokacija";

        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            int idLokacije = rs.getInt("idLokacije");
            String nazivLokacije = rs.getString("nazivLokacije");
            int idRegiona = rs.getInt("idRegiona");

            for (Region region : listaRegiona) {
                if (region.getIdRegiona() == idRegiona) {
                    r = region;
                }
            }

            Lokacija l = new Lokacija(idLokacije, r, nazivLokacije);
            listaLokacija.add(l);
        }
        return listaLokacija;
    }

    public ArrayList<Tim> getListuTimova() throws SQLException {
        ArrayList<Tim> listaTimova = new ArrayList<>();
        ArrayList<Lokacija> listaLokacija = getListuLokacija();
        ArrayList<Region> listaRegiona = getListuRegiona();
        Region r = null;
        Lokacija l = null;

        String sql = "SELECT * FROM tim";

        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            int idTima = rs.getInt("idTima");
            String naziv = rs.getString("naziv");
            String trener = rs.getString("trener");
            String menadzer = rs.getString("menadzer");
            String sponzor = rs.getString("sponzor");
            String igre = rs.getString("igre");
            Double novac = rs.getDouble("zaradjenNovac");
            int idRegiona = rs.getInt("idRegiona");
            int idLokacije = rs.getInt("idLokacije");

            for (Region region : listaRegiona) {
                if (region.getIdRegiona() == idRegiona) {
                    r = region;
                }
            }

            for (Lokacija lokacija : listaLokacija) {
                if (lokacija.getIdLokacije() == idLokacije) {
                    l = lokacija;
                }
            }

            Tim t = new Tim(idTima, naziv, trener, menadzer, sponzor, igre, novac, r, l);
            listaTimova.add(t);
        }
        return listaTimova;
    }

    public void sacuvajTim(Tim tim) throws SQLException {
        String upit = "INSERT INTO tim (naziv, trener, menadzer, sponzor, igre, zaradjenNovac, idRegiona, idLokacije) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);

        try {
            ps.setString(1, tim.getNaziv());
            ps.setString(2, tim.getTrener());
            ps.setString(3, tim.getMenadzer());
            ps.setString(4, tim.getSponzor());
            ps.setString(5, tim.getIgre());
            ps.setDouble(6, tim.getZaradjenNovac());
            ps.setInt(7, tim.getRegion().getIdRegiona());
            ps.setInt(8, tim.getLokacije().getIdLokacije());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }

    }

    public ArrayList<String> getListaManagera() throws SQLException {
        ArrayList<String> listaTimova = new ArrayList<>();

        String sql = "SELECT * FROM tim join region on tim.idRegiona = region.idRegiona join lokacija on tim.idLokacije = lokacija.idLokacije";

        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            String naziv = rs.getString("menadzer");
            listaTimova.add(naziv);
        }
        return listaTimova;
    }

    public ArrayList<String> getListuSponzora() throws SQLException {
        ArrayList<String> listaSponzora = new ArrayList<>();
        String sql = "SELECT * FROM tim join region on tim.idRegiona = region.idRegiona join lokacija on tim.idLokacije = lokacija.idLokacije";

        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            String sponzor = rs.getString("sponzor");
            listaSponzora.add(sponzor);
        }
        return listaSponzora;
    }

    public ArrayList<String> getListuIgara() throws SQLException {
        ArrayList<String> listaIgara = new ArrayList<>();
        String sql = "SELECT * FROM tim join region on tim.idRegiona = region.idRegiona join lokacija on tim.idLokacije = lokacija.idLokacije";

        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            String sponzor = rs.getString("igre");
            listaIgara.add(sponzor);
        }
        return listaIgara;
    }

    public void sacuvajKorisnika(Korisnik korisnik) throws SQLException {
        String upit = "INSERT INTO korisnik (imeKorisnika, prezimeKorisnika, username, password) VALUES (?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);

        try {
            ps.setString(1, korisnik.getImeKorisnika());
            ps.setString(2, korisnik.getPrezimeKorisnika());
            ps.setString(3, korisnik.getUsername());
            ps.setString(4, korisnik.getPassword());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
    }

    public ArrayList<Turnir> getListuTurnira() {
        ArrayList<Turnir> listaTurnira = new ArrayList<>();
        String sql = "SELECT * FROM `turnir` t JOIN serija s ON t.`idSerije` = s.`idSerije` JOIN `lokacija` l ON t.`idLokacije` = l.`idLokacije` JOIN region r ON t.`idRegiona` = r.`idRegiona`";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int idTurnira = rs.getInt("idTurnira");
                Date pocetak = rs.getDate("pocetak");
                Date kraj = rs.getDate("kraj");
                String naziv = rs.getString("naziv");
                double nagradniFond = rs.getDouble("nagradniFond");

                //serija
                int idSerije = rs.getInt("s.idSerije");
                String nazivSerije = rs.getString("s.nazivSerije");

                Serija serija = new Serija(idSerije, nazivSerije);

                //region
                int idRegiona = rs.getInt("r.idRegiona");
                String nazivRegiona = rs.getString("r.nazivRegiona");

                Region r = new Region(idRegiona, nazivRegiona);

                //lokacija
                int lokacijaID = rs.getInt("l.idLokacije");
                String nazivLokacije = rs.getString("l.nazivLokacije");

                Lokacija lokacija = new Lokacija(lokacijaID, r, nazivLokacije);

                //turnir
                Turnir turnir = new Turnir(idTurnira, pocetak, kraj, naziv, nagradniFond, serija, lokacija, r);
                listaTurnira.add(turnir);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTurnira;
    }

    public ArrayList<Korisnik> getListuKorisnika() throws Exception {
        ArrayList<Korisnik> listaK = new ArrayList<>();
        try {
            String sql = "SELECT * FROM korisnik";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int idKorisnika = rs.getInt("idKorisnika");
                String imeKorisnika = rs.getString("imeKorisnika");
                String prezimeKorisnika = rs.getString("prezimeKorisnika");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Korisnik korisnik = new Korisnik(idKorisnika, imeKorisnika, prezimeKorisnika, username, password);
                listaK.add(korisnik);
            }
        } catch (SQLException e) {
            throw new Exception("Neuspesno vadjenje podataka o korisniku", e);
        }
        return listaK;
    }

    public void sacuvajRezultat(Rezultat rezultat, int id) throws SQLException {
        try {
            String sql;
            int korisnikId = rezultat.getKorisnik().getIdKorisnika();
            //int korisnikId = 1;
            if (rezultat.getIdRezultat() != 0) {
                sql = String.format("UPDATE rezultat SET rezultat = '%s' WHERE idtima=%d and idturnira=%d and idrezultat=%d",
                        rezultat.getRezultat(), rezultat.getTim().getIdTima(), rezultat.getTurnir().getIdTurnira(), rezultat.getIdRezultat());
            } else {
                sql = String.format("insert into rezultat values(%d,%d,%d,%d,'%s')",
                        rezultat.getTim().getIdTima(), rezultat.getTurnir().getIdTurnira(), id,
                        korisnikId, rezultat.getRezultat());
            }

            Statement statement = konekcija.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public boolean obrisiTim(Tim tim) throws SQLException {
        boolean flag = true;
        String sqp = "DELETE FROM tim  WHERE `idTima` = " + tim.getIdTima();
        Statement s = konekcija.createStatement();
        s.executeUpdate(sqp);
        s.close();
        return flag;
    }
}
