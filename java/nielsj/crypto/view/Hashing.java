package nielsj.crypto.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import nielsj.crypto.R;
import nielsj.crypto.model.*;

// class Hashing is the user interface to Hashing

public class Hashing extends AppCompatActivity {

  // Most attributes of this activity are the views
  TextView guideTextView, hashvalueTextView;
  EditText inputEditText;
  Button hashButton;
  SHA256 crypto = new SHA256();

  // Methods

  // onCreate() instantiates views based on XML file hashing.xml
  // and let buttons trigger the crypto object
  // to do the hashing

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.hashing);

    // Widgets created as defined in res/layout/hashing.xml
    guideTextView = (TextView) findViewById(R.id.guideTextView);
    inputEditText = (EditText) findViewById(R.id.inputEditText);
    hashButton = (Button) findViewById(R.id.hashButton);
    hashvalueTextView = (TextView) findViewById(R.id.hashvalueTextView);
    hashButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String input = inputEditText.getText().toString();
        String hashValue = crypto.hash(input);
        hashvalueTextView.setText(hashValue);
      }
    });
  }

}
