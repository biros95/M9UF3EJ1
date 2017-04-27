/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9uf1ej3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Marcos
 */
public class fitxerUsuaris {

    private final File archivo = new File("users.txt");
    private SecretKey key;
    private String cadena_iv = "0123456789ABCDEF";
    FileInputStream fileInput = null;
    FileOutputStream fileOutput;

    public fitxerUsuaris() {
        try {
            this.fileOutput = new FileOutputStream(new File("usuariosEncriptados.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fitxerUsuaris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que genera la contraseá en AES y SHA256
     *
     * @param password
     * @return un objeto de tipo SecretKey
     */
    public SecretKey addKey(String password) {
        try {
            System.out.println("Generando clave");
            //Transformamos la contraseña a bytes
            byte[] passordBytes = password.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(passordBytes);

            //Generamos la contraseña AES
            key = new SecretKeySpec(hash, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            System.err.println("Error generando la clave.");

        }
        return key;
    }

    /**
     * Metodo para encriptar un archivo, recibe un String con el archivo que le
     * pasamos al file input y lo encripta
     *
     * @param archivo
     * @param key
     */
    public void encryptFile(String archivo, SecretKey key) {

        try {
            //Recogemos el archivo con los usuarios y creamos otro nuevo en el file
            //outputStream
            fileInput = new FileInputStream(archivo);

            Cipher cypher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //Encriptamos el archivo
            IvParameterSpec cadenaivCrypt = new IvParameterSpec(cadena_iv.getBytes());
            cypher.init(Cipher.ENCRYPT_MODE, key, cadenaivCrypt);
            byte[] buffer = new byte[1000];
            int bytes;
            while ((bytes = fileInput.read(buffer, 0, buffer.length)) != -1) {
                byte[] buffer2 = cypher.update(buffer, 0, bytes);
                fileOutput.write(buffer2);
            }

            System.out.println("Archivo cifrado");
            fileOutput.write(cypher.doFinal());
            fileInput.close();
            fileOutput.close();

        } catch (IOException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            System.out.println("Error");
        } finally {
            try {
                fileInput.close();
            } catch (IOException ex) {
                Logger.getLogger(fitxerUsuaris.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Le llega un archivo encriptado y lo desencripta, la salida la guarda en
     * otro archivo
     *
     * @param archivoEncriptado
     * @param key
     * @param fileToDecrypt
     */
    public void dectyptFile(String archivoEncriptado, SecretKey key, String fileToDecrypt) {
        try {
            //Creamos el file input y file output con la el archivo desencriptado
            fileInput = new FileInputStream(archivoEncriptado);
            fileOutput = new FileOutputStream(new File(fileToDecrypt));

            //Generamos el cypher con el tipo de cifrado
            Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(cadena_iv.getBytes());
            ci.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] buffer = new byte[1000];
            int bytes;
            while ((bytes = fileInput.read(buffer, 0, buffer.length)) != -1) {
                byte[] buffer2 = ci.update(buffer, 0, bytes);
                fileOutput.write(buffer2);
            }

            fileOutput.write(ci.doFinal());
            fileOutput.close();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException ex) {

        }

    }

    /**
     *
     * Metodo para escribir el usuario dentro del archivo
     *
     * @param usuario
     * @throws IOException
     */
    public void addUsers(Usuari usuario) throws IOException {
        FileWriter fw = new FileWriter(archivo, true);
        fw.write(usuario.getNom() + ":" + usuario.getCognoms() + ":" + usuario.getDni() + ":" + usuario.getUsuari() + ":" + usuario.getPassword() + "          ");
        fw.close();
    }

}
