package nielsj.crypto.model;

public interface SymmetricEncryption {

  public String encrypt(String p, String k);
  public String decrypt(String c, String k);
  public String getKey ();
}