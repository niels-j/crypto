package nielsj.crypto.model;

public class Caesar implements SymmetricEncryption {

  private String key = "0";

  public String getKey() {
    return key;
  }

  // encrypt and decrypt a whole string

  public String encrypt(String plaintext, String key) {
    int length = plaintext.length();
    char[] cs = new char[length];
    int k = Integer.valueOf(key);
    for (int i = 0; i < length; i++) {
      char c = plaintext.charAt(i);
      cs[i] = caesarEncrypt(c, k);
    }
    String ciphertext = new String(cs);
    return ciphertext;
  }

  public String decrypt(String ciphertext, String key) {
    int length = ciphertext.length();
    char[] cs = new char[length];
    int k = Integer.valueOf(key);
    for (int i = 0; i < length; i++) {
      char c = ciphertext.charAt(i);
      cs[i] = caesarDecrypt(c, k);
    }
    return new String(cs);
  }

  // encrypt and decrypt a single character
  public char caesarEncrypt(char c, int key) {
    int i = c - 65;
    if (i < 0 || i > 25) {
      System.out.println("Caesar: error: letter " + c + " is out of range 'A-Z'");
      return c;
    }  else {
      int j = Math.floorMod(i - key, 26);
      return (char) (j + 65);
    }
  }

  public char caesarDecrypt(char inc, int key) {
    return caesarEncrypt(inc, - key);
  }
}