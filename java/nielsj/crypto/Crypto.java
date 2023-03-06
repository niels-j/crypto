package nielsj.crypto;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Crypto extends AppCompatActivity {

  // The attributes of this activity are the views
  // and the welcome message to be displayet in the topmost TextView

  TextView welcomeTextView;
  Button symmetricEncryptionButton, hashingButton;
  String welcomeMessage = " Welcome to crypto\n"
                          + " - please select an algorithm type";

  // method onCreate() instantiates views based on XML file crypto.xml

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.crypto);

    welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
    welcomeTextView.setText(welcomeMessage);
    symmetricEncryptionButton = (Button) findViewById(R.id.symmetricencryptionButton);
    symmetricEncryptionButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),
                          nielsj.crypto.view.SymmetricEncryption.class);
        startActivity(i);
      }
    });
    hashingButton = (Button) findViewById(R.id.hashingButton);
    hashingButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),
                            nielsj.crypto.view.Hashing.class);
        startActivity(i);
      }
    });
  }


}
