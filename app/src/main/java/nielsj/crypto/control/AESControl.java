package nielsj.crypto.control;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import nielsj.crypto.R;
import nielsj.crypto.model.*;


// class AESUI is the user interface to AES encryption 

public class AESControl extends AppCompatActivity {
  TextView welcomeTextView, decryptedTextView;
  Button encryptButton, decryptButton;
  EditText keyEditText, ivEditText, plaintextEditText, encryptedEditText;

  // The crypto object does the cryptographic work

  nielsj.crypto.model.AES crypto;

  // Methods

  // The encrypt/decrypt buttons trigger the crypto object
  // to do encryption and decryption

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.aes);
    Intent intent = getIntent();
    String operation = intent.getStringExtra("operation");
    String padding = intent.getStringExtra("padding");
    crypto = new AES(operation, padding);
    String provider = crypto.getProvider();
    welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
    welcomeTextView.setText("AES with " + operation + " and " + padding
                            + "\nSecurity provider: " + provider);
    ivEditText = (EditText) findViewById(R.id.ivEditText);
    switch (operation) {
      case "ECB":
        ivEditText.setText("(no iv)");
        break;
      case "CBC":
        ivEditText.setText(crypto.getIV());
        break;
      default:
        ivEditText.setText("??");
    }
    plaintextEditText = (EditText) findViewById(R.id.plaintextEditText);
    keyEditText = (EditText) findViewById(R.id.keyEditText);
    keyEditText.setText(crypto.getKey());
    encryptButton = (Button) findViewById(R.id.encryptButton);
    encryptButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String pt = plaintextEditText.getText().toString();
        String key = keyEditText.getText().toString();
        if (operation.equals("CBC")) {
          String iv = ivEditText.getText().toString();
          crypto.setIV(iv);
        }
        String ct = crypto.encrypt(pt, key);
        encryptedEditText.setText(ct);
      }
    });
    encryptedEditText = (EditText) findViewById(R.id.encryptedEditText);
    decryptButton = (Button) findViewById(R.id.decryptButton);
    decryptButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String ct = encryptedEditText.getText().toString();
        String key = keyEditText.getText().toString();
        if (operation.equals("CBC")) {
          String iv = ivEditText.getText().toString();
          crypto.setIV(iv);
        }
        String pt = crypto.decrypt(ct, key);
        decryptedTextView.setText(pt);
      }
    });
    decryptedTextView = (TextView) findViewById(R.id.decryptedTextView);
  }
}