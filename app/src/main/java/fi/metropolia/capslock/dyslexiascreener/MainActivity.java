package fi.metropolia.capslock.dyslexiascreener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.dao.TestDao;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Application entrypoint, first activity loaded when the app starts.
 *
 * @author Joel Tikkanen
 * @author Joonas Jouttijärvi
 * @author Peetu Saarinen
 */
public class MainActivity extends AppCompatActivity {

    SeekBar ageSeekbar;
    EditText nameText;
    TextView currentAgeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.nameEditText);
        ageSeekbar = (SeekBar) findViewById(R.id.ageseekBar);
        currentAgeText = (TextView) findViewById(R.id.currentAge);

        ageSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentAgeText.setText(Integer.toString(progress));
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
     * @param view View of item clicked
     */
    public void startTest(View view) {
        String name = nameText.getText().toString();
        int age = ageSeekbar.getProgress();
    }

}
