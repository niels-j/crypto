package nielsj.crypto.control;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import nielsj.crypto.R;

// class SymmetricEncryptionUI is the user interface to symmetric encryption
// It selects between Caesar and AES encryption
// (for AES, the class also selects ECB or CBC)

public class SymmetricEncryptionControl extends AppCompatActivity {

  // The attributes of this activity are the views and buttons
  Button caesarButton, aesButton;
  RadioButton ecbRadioButton, cbcRadioButton,
          noPaddingRadioButton, paddingRadioButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.symmetricencryption);

    // The Caesar button
    caesarButton = (Button) findViewById(R.id.caesarButton);
    caesarButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),
                          CaesarControl.class);
        startActivity(i);
      }
    });

    // The AES button with parameter passing to class AESControl
    aesButton = (Button) findViewById(R.id.aesButton);
    ecbRadioButton = (RadioButton) findViewById(R.id.ecbRadioButton);
    cbcRadioButton = (RadioButton) findViewById(R.id.cbcRadioButton);
    noPaddingRadioButton = (RadioButton) findViewById(R.id.noPaddingRadioButton);
    paddingRadioButton = (RadioButton) findViewById(R.id.paddingRadioButton);
    aesButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),
                          AESControl.class);
        String operation = "";
        if (ecbRadioButton.isChecked())
          operation = "ECB";
        if (cbcRadioButton.isChecked())
          operation = "CBC";
        i.putExtra("operation", operation);
        String padding = "";
        if (noPaddingRadioButton.isChecked())
          padding = "NoPadding";
        if (paddingRadioButton.isChecked())
          padding = "PKCS5Padding";
        i.putExtra("padding", padding);
        String keyformat = "";
        startActivity(i);
      }
    });
  }
}