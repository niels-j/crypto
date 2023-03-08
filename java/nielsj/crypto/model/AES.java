package nielsj.crypto.model;

import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
// import javax.crypto.spec.IvParameterSpec;

import nielsj.crypto.control.*;

public class AES implements SymmetricEncryption {

  public AES() {
    initECB();
  //  initCBC();
  }


  private Cipher cipher;
  private SecretKeySpec key;

  byte[] iv = Hex.hexStringToByteArray("9f741fdb5d8845bdb48a94394e84f8a3");

  /*******************
   * public methods  *
   *******************/


  // encryption

  // encrypt() is a wrapper
  // that accepts user-written input
  // and returns human readable output

  public String encrypt(String plaintext) {
    byte[] plainBytes = plaintext.getBytes();
    byte[] cipherBytes = aesEncrypt(plainBytes);
    String ciphertext = Hex.byteArrayToHexString(cipherBytes);
    String blocks = Hex.hexStringToMultiLine(ciphertext,32);
    return blocks;
   // return ciphertext;
  }

  // aesEncrypt() is the actual encryption

  public byte[] aesEncrypt(byte[] plainBytes) {
    byte[] cipherBytes = {};
    try {
     cipher.init(Cipher.ENCRYPT_MODE, key);
     // cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv) );

      cipherBytes = cipher.doFinal(plainBytes);
    } catch (Exception e) {
      System.out.println("AES: encryption error");
      System.out.println(e);
    }
    return cipherBytes;
  }

  // decryption

  // decrypt() is a wrapper
  public String decrypt(String ct) {
    String ciphertext = Hex.hexStringToSingleLine(ct);
    byte[] cipherBytes = Hex.hexStringToByteArray(ciphertext);
    byte[] plainBytes = aesDecrypt(cipherBytes);
    String plaintext = new String(plainBytes);
    return plaintext;
  }

  // aesDecrypt is the actual decryption

  public byte[] aesDecrypt(byte[] cipherBytes) {
    byte[] plainBytes = {};
    try {
      cipher.init(Cipher.DECRYPT_MODE, key);
     // cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv) );
      plainBytes = cipher.doFinal(cipherBytes);
    } catch (Exception e) {
      System.out.println("AES: decryption error");
      System.out.println(e);
    }
    return plainBytes;
  }

  /*******************
   * private methods *
   *******************/
  private void initECB() {
    try {
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
    } catch (Exception e) {
      System.out.println("AES: cipher generation error");
      System.out.println(e);
    }
    key = generateKey();
  }

  private void initCBC() {
    try {
      cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
    } catch (Exception e) {
      System.out.println("AES: cipher generation error");
      System.out.println(e);
    }
    key = generateKey();
  }
  private SecretKeySpec generateKey() {
    SecretKeySpec key = null;
    try {
      String keyHexString = "000102030405060708090A0B0C0D0E0F";
      byte[] keyBytes = Hex.hexStringToByteArray(keyHexString);
      key = new SecretKeySpec(keyBytes, "AES");
    } catch (Exception e) {
      System.out.println("AES: key generation error");
      System.out.println(e);
    }
    return key;
  }

  private void listProviders() {
    System.out.println("Listing available providers:");
    Provider[] installedProvs = Security.getProviders();
    for (int i = 0; i != installedProvs.length; i++)  {
      System.out.print(installedProvs[i].getName());
      // System.out.print(": ");
      // System.out.print(installedProvs[i].getInfo());
      System.out.println();
    }
  }


}
