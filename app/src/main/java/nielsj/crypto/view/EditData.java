package nielsj.crypto.view;

import android.widget.EditText;
import android.content.Context;
import android.util.AttributeSet;

public class EditData  extends EditText {

  // defining four required constructors
  public EditData (Context context) { super(context); }

  public EditData (Context context, AttributeSet attrs) { super(context, attrs); }

  public EditData (Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
  public EditData (Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }

  public static String keyformat;
}
