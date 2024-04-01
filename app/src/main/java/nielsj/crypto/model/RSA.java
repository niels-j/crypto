package nielsj.crypto.model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.Signature;

import nielsj.crypto.view.Hex;

public class RSA {

  public RSA() {
    String provider = "BC";
    try {
      signerVerifier = Signature.getInstance("SHA256withRSA", provider);
    } catch (Exception e) {
      System.out.println("RSA: signature getInstance() error");
      System.out.println(e);
    }
    try {
      // Trusting the provider is important with key generation
      generator = KeyPairGenerator.getInstance("RSA", provider);
      generator.initialize(24);
      // generator.initialize(32);
    } catch (Exception e) {
      System.out.println("RSA: keypair generator getInstance() error");
      System.out.println(e);
    }
  }
  public String getProvider() {
    return generator.getProvider().getName();
  }

  Signature signerVerifier;
  KeyPairGenerator generator;

  public BigInteger modulus, privateKeyExponent, publicKeyExponent;
  RSAPrivateKey privateKey;
  RSAPublicKey publicKey;

  public void generateKeyPair() {
    KeyPair keyPair = null;
    try {
      keyPair = generator.generateKeyPair();
    } catch (Exception e) {
      System.out.println("RSA: key pair generation error");
      e.printStackTrace();
    }
    privateKey = (RSAPrivateKey) keyPair.getPrivate();
    publicKey = (RSAPublicKey) keyPair.getPublic();
    modulus = publicKey.getModulus();
    privateKeyExponent = privateKey.getPrivateExponent();
    publicKeyExponent = publicKey.getPublicExponent();
  }

  public String sign(String message) {
    byte[] input = message.getBytes();
    byte[] output = {};
    try {
      signerVerifier.initSign(privateKey);
      signerVerifier.update(input);
      output = signerVerifier.sign();
    } catch (Exception e) {
      System.out.println("RSA: signing error");
      System.out.println(e);
      return "(error)";
    }
    String signature = Hex.byteArrayToHexString(output);
    return signature;
  }

  public boolean verify(String signature, String message) {
    boolean b = false;
    try {
      byte[] messageBytes = message.getBytes();
      if (messageBytes.length == 0) System.out.println("RSA: message is empty");
      byte[] signatureBytes = Hex.hexStringToByteArray(signature);
      signerVerifier.initVerify(publicKey);
      signerVerifier.update(messageBytes);
      b = signerVerifier.verify(signatureBytes);
    } catch (Exception e) {
      System.out.println("RSA: verfication error");
      System.out.println(e);
    }
    return b;

  }

}