package nielsj.crypto;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import nielsj.crypto.control.AsymmetricEncryptionControl;
import nielsj.crypto.control.HashingControl;
import nielsj.crypto.control.SymmetricEncryptionControl;

public class Crypto extends AppCompatActivity {

  // The attributes of this activity are the views
  // and the welcome message to be displayet in the topmost TextView

  Button symmetricEncryptionButton, hashingButton, asymmetricEncryptionButton;

  // method onCreate() instantiates views based on XML file crypto.xml

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.crypto);
    symmetricEncryptionButton = (Button) findViewById(R.id.symmetricEncryptionButton);
    symmetricEncryptionButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),
                          SymmetricEncryptionControl.class);
        startActivity(i);
      }
    });
    hashingButton = (Button) findViewById(R.id.hashingButton);
    hashingButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),
                            HashingControl.class);
        startActivity(i);
      }
    });
    asymmetricEncryptionButton = (Button) findViewById(R.id.asymmetricEncryptionButton);
    asymmetricEncryptionButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
      Intent i = new Intent(getApplicationContext(),
              AsymmetricEncryptionControl.class);
      startActivity(i);
      }
    });
  }
}