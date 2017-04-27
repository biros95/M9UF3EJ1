/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9uf1ej3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author MarcosPortatil
 */
public class Test {
    public static void main(String[] args) throws IOException, FileNotFoundException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //Creacion de usuarios 
        Usuari u = new Usuari("77129725F", "Marcos", "Fuentes Aguilera");
        Usuari u2 = new Usuari("12345678A", "Prueba", "Apellido1 Apellido2");
        Usuari u3 = new Usuari("12345678B", "Prueba2", "Apellido1 Apellido2");
        Usuari u4 = new Usuari("12345678C", "Prueba3", "Apellido1 Apellido2");
        //Declaramos la SecreyKey
        SecretKey key;
        
       
        //Añadimos los usuarios.
        fitxerUsuaris fitxerUsuaris = new fitxerUsuaris();
        fitxerUsuaris.addUsers(u);
        fitxerUsuaris.addUsers(u2);
        fitxerUsuaris.addUsers(u3);
        fitxerUsuaris.addUsers(u4);
        //Generamos la clave.
        key = fitxerUsuaris.addKey("DAMM9UF1EJ3");
        //Ciframos el fichero.
        fitxerUsuaris.encryptFile("users.txt", key);
        //Desciframos el fichoer.
        fitxerUsuaris.dectyptFile("usuariosEncriptados.txt", key, "archivoUsauriosDecrypt.txt");
        
        
    }
    
}
