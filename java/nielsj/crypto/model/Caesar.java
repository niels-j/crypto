package nielsj.crypto.model;

import nielsj.crypto.control.Hex;

public class Caesar implements SymmetricEncryption {

  public Caesar() {
    key = -1;
  }

  private int key;
  public String encrypt(String plaintext) {
    int length = plaintext.length();
    char[] cs = new char[length];
    for (int i = 0; i < length; i++) {
      char c = plaintext.charAt(i);
      cs[i] = caesarEncrypt(c, key);
    }
    String ciphertext = new String(cs);
    return ciphertext;
  }

  public char caesarEncrypt(char c, int key) {
    int i = c - 65;
    if (i < 0 || i > 25) {
      System.out.println("Caesar: error: letter " + c + " is out of range 'A-Z'");
      System.out.println("Caesar: resetting letter to 'A'");
      i = 0;
    }
    int j = Math.floorMod(i + key, 26);
    return (char) (j + 65);
  }

  public String decrypt(String ciphertext) {
    int length = ciphertext.length();
    char[] cs = new char[length];
    for (int i = 0; i < length; i++) {
      char c = ciphertext.charAt(i);
      cs[i] = caesarDecrypt(c, key);
    }
    return new String(cs);
  }

  public char caesarDecrypt(char inc, int key) {
    return caesarEncrypt(inc, - key);
  }
}
