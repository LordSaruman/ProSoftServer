/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author filip_000
 */
public class DBUtil {
    
    private Properties properties;

    public DBUtil() throws FileNotFoundException, IOException {
        properties = new Properties();
        properties.load(new FileInputStream("database.config"));
    }

    public String vratiUrl() {
        return properties.getProperty(Constants.URL);
    }

    public String vratiKorisnika() {
        return properties.getProperty(Constants.USERNAME);
    }

    public String vratiSifru() {
        return properties.getProperty(Constants.PASSWORD);
    }

    public void postaviParametre(String url, String username, String password) throws FileNotFoundException, IOException {
        properties.setProperty(Constants.URL, url);
        properties.setProperty(Constants.USERNAME, username);
        properties.setProperty(Constants.PASSWORD, password);

        FileOutputStream fos = new FileOutputStream("database.config");
        properties.store(fos, "Database Configuration");
    }
}
