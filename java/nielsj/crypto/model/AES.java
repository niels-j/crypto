package nielsj.crypto.model;

import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import nielsj.crypto.control.*;

public class AES implements SymmetricEncryption {

  public AES() {
    try {
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
    } catch (Exception e) {
      System.out.println("AES: cipher generation error");
      System.out.println(e);
    }
    key = generateKey();
  }

  private Cipher cipher;
  private SecretKeySpec key;

  /*******************
   * public methods  *
   *******************/

  public SecretKeySpec generateKey() {
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

  // encryption

  // encrypt() is a wrapper
  // that accepts user-written input
  // and returns readable output

  public String encrypt(String plaintext) {
    byte[] plainBytes = plaintext.getBytes();
    byte[] cipherBytes = aesEncrypt(plainBytes);
    String ciphertext = Hex.byteArrayToHexString(cipherBytes);
    return ciphertext;
  }

  // aesEncrypt() is the actual encryption

  public byte[] aesEncrypt(byte[] plainBytes) {
    byte[] cipherBytes = {};
    try {
      cipher.init(Cipher.ENCRYPT_MODE, key);
      cipherBytes = cipher.doFinal(plainBytes);
    } catch (Exception e) {
      System.out.println("AES: encryption error");
      System.out.println(e);
    }
    return cipherBytes;
  }

  // decryption

  // decrypt() is a wrapper
  public String decrypt(String ciphertext) {
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
