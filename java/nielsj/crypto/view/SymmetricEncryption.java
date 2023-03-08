package nielsj.crypto.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import nielsj.crypto.R;
import nielsj.crypto.model.*;

// class SymmetricEncryption is the user interface to symmetric encryption

public class SymmetricEncryption extends AppCompatActivity {

  // Most attributes of this activity are the views
  TextView guideTextView, decryptedTextView;
  Button encryptButton, decryptButton;
  EditText plaintextEditText, encryptedEditText;

  // The other main attribute is the crypto object
  // that does the cryptographic work

  nielsj.crypto.model.SymmetricEncryption crypto = new AES();
  // nielsj.crypto.model.SymmetricEncryption crypto = new Caesar();

  // Methods

  // onCreate() instantiates views based on XML file symmetricencryption.xml
  // and let buttons trigger the crypto object
  // to do encryption and decryption

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.symmetricencryption);

    guideTextView = (TextView) findViewById(R.id.guideTextView);
    plaintextEditText = (EditText) findViewById(R.id.plaintextEditText);
    encryptButton = (Button) findViewById(R.id.encryptButton);
    encryptButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String pt = plaintextEditText.getText().toString();
        String ct = crypto.encrypt(pt);
        encryptedEditText.setText(ct);
      }
    });
    encryptedEditText = (EditText) findViewById(R.id.encryptedEditText);
    decryptButton = (Button) findViewById(R.id.decryptButton);
    decryptButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String ct = encryptedEditText.getText().toString();
        String pt = crypto.decrypt(ct);
        decryptedTextView.setText(pt);
      }
    });
    decryptedTextView = (TextView) findViewById(R.id.decryptedTextView);
  }


}
