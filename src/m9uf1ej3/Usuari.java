/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9uf1ej3;

import java.util.Random;

/**
 *
 * @author MarcosPortatil
 */
public class Usuari {
    
    private String dni;
    private String nom;
    private String cognoms;
    private String usuari;
    private String password;

    public Usuari(String dni, String nom, String cognoms) {
        this.dni = dni;
        this.nom = nom;
        this.cognoms = cognoms;  
        dni = dni.substring(0, dni.length()-1);
        this.usuari = dni.substring(dni.length()-3);
        this.password = getSaltString();
        
    }

    @Override
    public String toString() {
        return "Usuari{" + "dni=" + dni + ", nom=" + nom + ", cognoms=" + cognoms + ", usuari=" + usuari + ", password=" + password + '}';
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getPassword() {
        return password;
    }

   
    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
    
}
