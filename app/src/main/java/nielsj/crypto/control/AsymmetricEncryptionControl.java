package nielsj.crypto.control;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import nielsj.crypto.R;
import nielsj.crypto.model.*;

// class AsymmetricEncryption is the user interface to symmetric encryption

public class AsymmetricEncryptionControl extends AppCompatActivity {

  // Most attributes of this activity are the views

  TextView welcomeTextView, guideTextView;
  Button generateKeyPairButton, clearButton, signButton, verifyButton;
  EditText modulusEditText, privateKeyExponentEditText,
          publicKeyExponentEditText, messageEditText,
          signatureEditText, verificationEditText;

  // The other main attribute is the crypto object
  // that does the cryptographic work

  nielsj.crypto.model.RSA crypto;

  // Methods

  // onCreate() instantiates views based on XML file symmetricencryption.xml
  // and let buttons trigger the crypto object
  // to do encryption and decryption

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.asymmetricencryption);
    crypto = new RSA();
    welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
    welcomeTextView.setText("Asymmetric encryption (RSA)");
    String provider = crypto.getProvider();
    welcomeTextView.append("\nSecurity provider: " + provider);
    generateKeyPairButton= (Button) findViewById(R.id.generateKeyPairButton);
    generateKeyPairButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        crypto.generateKeyPair();
        modulusEditText.setText(crypto.modulus.toString());
        privateKeyExponentEditText.setText(crypto.privateKeyExponent.toString());
        publicKeyExponentEditText.setText(crypto.publicKeyExponent.toString());
      }
    });
    clearButton = (Button) findViewById(R.id.clearButton);
    clearButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        clear();}
    });
    modulusEditText = (EditText) findViewById(R.id.modulusEditText);
    privateKeyExponentEditText = (EditText) findViewById(R.id.privateKeyExponentEditText);
    publicKeyExponentEditText = (EditText) findViewById(R.id.publicKeyExponentEditText);
    guideTextView = (TextView) findViewById(R.id.guideTextView);
    messageEditText = (EditText) findViewById(R.id.messageEditText);
    signButton = (Button) findViewById(R.id.signButton);
    signButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String message = messageEditText.getText().toString();
        String signature = crypto.sign(message);
        signatureEditText.setText(signature);
      }
    });
    signatureEditText = (EditText) findViewById(R.id.signatureEditText);
    verifyButton = (Button) findViewById(R.id.verifyButton);
    verifyButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String result;
        String signature = signatureEditText.getText().toString();
        String message = messageEditText.getText().toString();
        if (crypto.verify(signature, message)) {
          result = "Signature is ok";
        } else {
          result = "Signnature is not ok";
        }
        verificationEditText.setText(result);}
    });
    verificationEditText = (EditText) findViewById(R.id.verificationEditText);

    clear();
  }

  private void clear() {
    modulusEditText.setText("<modulus>");
    privateKeyExponentEditText.setText("<private key exponent>");
    publicKeyExponentEditText.setText("<public key exponent>");
  }


}
