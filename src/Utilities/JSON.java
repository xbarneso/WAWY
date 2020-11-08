/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import MainClasses.AdminUser;
import MainClasses.User;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;


/**
 *
 * @author xbarn
 */
public class JSON {

    public JSONObject jsonResponse;

    public JSON() {

    }
    
     /**
     * Aquest mètode chequejarà que l'usuari estigui enregistrat al servidor.
     *
     * @param username username escrit a la pantalla de login
     * @param password password escrita a la pantalla de login
     *
     * @return boolean true en cas de que sigui un usuari valid, false en cas de que sigui incorrecte.
     */
     /**
     * Aquest mètode crearà l'objecte json a partir de la resposta del server per a simplificar el tractament de dades.
     *
     * @param result resposta el servidor en format string
     */
    public void JSON_receiver_Simple(String result) {
        jsonResponse = new JSONObject(result);
    }

     /**
     * Aquest mètode mirarà a partir de la resposta del server si l'user es valid 
     *
     * @param username rebrà l'String de l'username per a guardar-lo.
     *
     * @return Object adminUser en cas de qe hagi estat loguejat. 
     */
    
    public AdminUser isUser_Valid(String username) {

        boolean admin;
        //Agafam els strings de les etiquetes del json
        String token = jsonResponse.getString("X-Auth-Token");
        String adminStr = jsonResponse.getString("admin");
        
        if (adminStr.equals("0")) {
            admin = false;
        } else {
            admin = true;
        }
        //Creem l'user object per retornar-lo
        AdminUser user = new AdminUser(token, admin, username);

        return user;

    }

     /**
     * Aquest mètode agafarà les dades rebudes pel servidor i les transformarà en objecte bidemnsional per al seu tractament. 
     *
     * @return Object bidimensional amb les dades de les conexions. 
     */
    
    
    public Object[][] getDataConnections() {
        
        //El Json de conexions va precedit per la paraula records i es un array. 
        JSONArray results = (JSONArray) jsonResponse.getJSONArray("records");
        //Bàsicament creem un array d'una mida particular. 
        Object dades[][] = new Object[50000][4];
        int loops;
        if (results.length() > 50000) {
            loops = 50000;
        } else {
            loops = results.length();
        }
        //Un per un, anem agafant els records. 
        for (int i = 0; i < loops; i++) {
            JSONObject obj = results.getJSONObject(i);
            dades[i][0] = (obj.get("ipaddress"));
            dades[i][1] = (obj.get("country"));
            dades[i][2] = (obj.get("city"));
            dades[i][3] = (obj.get("created_at"));
        }

        return dades;
    }
     /**
     * Aquest mètode agafarà les dades del server de l'usuari i les transformarà en un array. 
     *
     * @return un array d'strings amb les dades e l'usuari. 
     */
    
    public String[] getUser_data() {

        String[] data = new String[6];
        data[0] = jsonResponse.getString("displayname");
        data[1] = jsonResponse.getString("street");
        data[2] = jsonResponse.getString("city");
        data[3] = jsonResponse.getString("region");
        data[4] = jsonResponse.getString("cp");
        data[5] = jsonResponse.getString("country");
        return data;

    }
}
