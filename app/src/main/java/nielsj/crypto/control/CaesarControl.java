package nielsj.crypto.control;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import nielsj.crypto.R;
import nielsj.crypto.model.*;

// class CaesarUI is the user interface to Caesar encryption

public class CaesarControl extends AppCompatActivity {

  // Most attributes of this activity are the views
  TextView welcomeTextView, keyTextView, guideTextView, decryptedTextView;
  Button encryptButton, decryptButton;
  EditText keyEditText, plaintextEditText, encryptedEditText;

  // The other main attribute is the crypto object
  // that does the cryptographic work

  nielsj.crypto.model.Caesar caesar = new Caesar();

  // Methods

  // onCreate() instantiates views based on XML file symmetricencryptionui.xml
  // and let buttons trigger the crypto object
  // to do encryption and decryption

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.caesar);
    String welcomeText = "Caesar encryption";
    String keyText = "Please type key (0-25):";
    String guideText = "Please type plaintext:";

    welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
    welcomeTextView.setText(welcomeText);
    keyTextView = (TextView) findViewById(R.id.keyTextView);
    keyTextView.setText(keyText);
    keyEditText = (EditText) findViewById(R.id.keyEditText);
    keyEditText.setText(caesar.getKey());
    guideTextView = (TextView) findViewById(R.id.guideTextView);
    guideTextView.setText(guideText);
    plaintextEditText = (EditText) findViewById(R.id.plaintextEditText);
    encryptButton = (Button) findViewById(R.id.encryptButton);
    encryptButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String pt = plaintextEditText.getText().toString();
        String key = keyEditText.getText().toString();
        String ct = caesar.encrypt(pt, key);
        encryptedEditText.setText(ct);
      }
    });
    encryptedEditText = (EditText) findViewById(R.id.encryptedEditText);
    decryptButton = (Button) findViewById(R.id.decryptButton);
    decryptButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String ct = encryptedEditText.getText().toString();
        String key = keyEditText.getText().toString();
        String pt = caesar.decrypt(ct, key);
        decryptedTextView.setText(pt);
      }
    });
    decryptedTextView = (TextView) findViewById(R.id.decryptedTextView);
  }


}
