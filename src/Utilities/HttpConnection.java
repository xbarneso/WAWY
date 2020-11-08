/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import MainClasses.AdminUser;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xbarn
 */
public class HttpConnection {

    private final static CloseableHttpClient httpClient = HttpClients.createDefault();
    private String idSession;
    private JSON json;
    private AdminUser user;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    public AdminUser getUser() {
        return user;
    }

    public void setUser(AdminUser user) {
        this.user = user;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    private void close() throws IOException {
        httpClient.close();
    }

    /**
     * Aquest mètode chequejarà que l'usuari estigui enregistrat al servidor.
     *
     * @param username username escrit a la pantalla de login
     * @param password password escrita a la pantalla de login
     *
     * @return boolean true en cas de que sigui un usuari valid, false en cas de que sigui incorrecte.
     */
    public boolean checkUser_Validation(String username, String password) {
        try {
            //Creem l'objecte http amb l'api
            HttpPost post = new HttpPost("https://api.wawy.es/login");
            String answer;
            //Llista amb els parametres que farem servir. 
            List<NameValuePair> urlParameters = new ArrayList<>();
            JSON json = new JSON();
            //Paremetres per l'api
            urlParameters.add(new BasicNameValuePair("username", username));
            urlParameters.add(new BasicNameValuePair("password", password));
            //Fem el post i rebrem la resposta avall
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            //Rebem la resposta i la processem
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(post)) {
                answer = EntityUtils.toString(response.getEntity());
                //Creem un objecte json
                json.JSON_receiver_Simple(answer);
                user = json.isUser_Valid(username);
                //Si l'user es null, no estara autenticat
                if (user == null) {
                    return false;
                }
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * Aquest mètode tancarà la conexió amb el server. Bàicament necessita el
     * token que treurà de l'usuari objecte.
     */
    public void logOut() {
        try {

            HttpPost post = new HttpPost("https://api.wawy.es/logout");
            String answer;
            List<NameValuePair> urlParameters = new ArrayList<>();

            urlParameters.add(new BasicNameValuePair("X-Auth-Token", this.getUser().getIdToken()));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(post)) {

            }

        } catch (Exception e) {
            System.out.println("");
        }
    }

    /**
     * Aquest mètode aconseguirà totes les dades requestades per l'admin i les
     * enviarà al Controller. totes relacionades amb les connexions rebudes i
     * enregistrades al servidor
     *
     * @param dateFrom desde la qual volem rebre les dades.
     * @return Object un objecte bidemensional amb una entitat per a cada
     * conexió per a poder pintar-la després.
     */
    
    public Object[][] getConnections(String dateFrom) {
        try {
            //Creem l'objecte http amb l'api
            HttpGet post = new HttpGet("https://api.wawy.es/v1/records/processed?filter=created_at,gt," + dateFrom);
            String answer;
            //Objecte on guardarem les dades
            Object data[][];
            JSON json = new JSON();
            //Necessitem el token per a demanar dades-
            post.addHeader("x-Auth-Token", this.user.getIdToken());
            //Rebem la resposta    
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(post)) {
                answer = EntityUtils.toString(response.getEntity());
                json.JSON_receiver_Simple(answer);
                data = json.getDataConnections();

                return data;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Aquest mètode aconseguirà totes les dades requestades per l'admin i les
     * enviarà al Controller.
     *
     * @param token el qual es qui ha iniciat la sessió per tant ho necessitem
     * per demanar dades al server.
     * @param username string amb l'username del qual volem les dades.
     * @return String[] un array amb totes les dades de l'usuari.
     */
    public String[] getUserData(String username, String token) {

        try {
            //Instanciem l'objecte que cridarà l'api del servidor.
            HttpGet post = new HttpGet("https://api.wawy.es/user?username=" + username);
            String answer;
            String[] data;
            JSON json = new JSON();
            //I hem d'afegir al objecte http el token per a realitzar la conexio i autenticar-la.
            post.addHeader("X-Auth-Token", token);

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(post)) {
                //rebem la resposta de l'api
                answer = EntityUtils.toString(response.getEntity());
                //li pasem a al json object per a que el rebi.
                json.JSON_receiver_Simple(answer);
                //ara, demanem les dades ja formatades. 
                data = json.getUser_data();

                return data;

            } catch (Exception e) {
                return null;
            }

        } catch (Exception e) {
            return null;
        }

    }

}
