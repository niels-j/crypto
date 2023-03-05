package nielsj.crypto.model;

public interface SymmetricEncryption {

  public String encrypt(String p);
  public String decrypt(String p);

}