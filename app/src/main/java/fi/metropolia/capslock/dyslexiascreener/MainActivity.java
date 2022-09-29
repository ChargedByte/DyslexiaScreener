package fi.metropolia.capslock.dyslexiascreener;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Application entrypoint, first activity loaded when the app starts.
 *
 * @author Joel Tikkanen
 * @author Joonas Jouttijärvi
 * @author Peetu Saarinen
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private SeekBar seekBarAge;
    private TextView textViewAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.nameEditText);
        seekBarAge = (SeekBar) findViewById(R.id.ageseekBar);
        textViewAge = (TextView) findViewById(R.id.currentAge);

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAge.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    /**
     * Start the test with given name and age.
     *
     * @param view View of item clicked
     */
    public void startTest(View view) {
        String name = editTextName.getText().toString();
        int age = seekBarAge.getProgress();

        // TODO: Start test
    }

}
