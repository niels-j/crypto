package nielsj.crypto.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nielsj.crypto.R;

public class Hashing extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.hashing);

    // Widgets created as defined in res/layout/hashing.xml
    guideTextView = (TextView) findViewById(R.id.guideTextView);
    inputEditText = (EditText) findViewById(R.id.inputEditText);
    hashButton = (Button) findViewById(R.id.hashButton);
    hashButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        System.out.println("view.Hashing: button clicked ..");
      }
    });
    hashvalueTextView = (TextView) findViewById(R.id.hashvalueTextView);
  }

  TextView guideTextView, hashvalueTextView;
  EditText inputEditText;
  Button hashButton;
}
