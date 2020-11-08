/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import Utilities.HttpConnection;

/**
 *
 * @author xbarn
 */
public class Controller {

    private String passw;
    private String username;
    private HttpConnection connection_http;
    private AdminUser user;

    public AdminUser getUser() {
        return user;
    }

    public void setUser(AdminUser user) {
        this.user = user;
    }

    public Controller(){
        this.connection_http = new HttpConnection();
    }
    
    public HttpConnection getConn() {
        return connection_http;
    }
    
    public void setConn(HttpConnection conn) {
        this.connection_http = conn;
    }
    
    public void setController(String username, String passw) {
        this.passw = passw;
        this.username = username;
    }
     /**
     * Aquest mètode mirarà si l'usuari es vàlid i està registrat a la db
     * @return retornarà si l'usuari introduit es valid. es a dir, farà l'autenticació
     */
    public boolean isValid(){
        
        //Create the connection to check if is valid
        boolean isValid;
        //Cridarà l'api per a veure si es valid
        isValid= connection_http.checkUser_Validation(this.getUsername(),this.getPassw());
        this.user = connection_http.getUser();
        
        return isValid;
        
    }
      /**
     * Aquest mètode retornarà les dades de les conexions. 
     * @param dateFrom data desde la qual volem les dades.
     * @return retornarà un objecte bidimensional amb totes les dades 
     */
    public Object[][] getConnections(String dateFrom){
        
        return connection_http.getConnections(dateFrom);
        
    }
     /**
     * Aquest mètode aconseguirà totes les dades requestades per l'admin. 
     * @param user objecte user amb les dades de l'usuari (necessitarem el token) 
     * @param username string amb l'username del qual volem les dades.
     * @return retornarà un array amb totes les dades de l'usuari. 
     */
    public String[] getDataUser(AdminUser user, String username){
        
        try{
            
            return connection_http.getUserData(username,user.getIdToken());
            
        }catch(Exception e){
            return null;
        }
        
    }
    
    /**
     * @return the passw
     */
    public String getPassw() {
        return passw;
    }

    /**
     * @param passw the passw to set
     */
    public void setPassw(String passw) {
        this.passw = passw;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
