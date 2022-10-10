package fi.metropolia.capslock.dyslexiascreener.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import fi.metropolia.capslock.dyslexiascreener.BaseActivity;
import fi.metropolia.capslock.dyslexiascreener.MainActivity;
import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.SharedConstants;

/**
 * Activity for thanking the user after a test and sending them back to the start.
 *
 * @author Peetu Saarinen
 */
public class TestEndActivity extends BaseActivity {
    private TextView textViewThankYou;
    private Button buttonReturnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_end);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        Intent intent = getIntent();
        String studentName = intent.getStringExtra(SharedConstants.EXTRA_STUDENT_NAME);

        textViewThankYou = findViewById(R.id.textViewThankYou);
        buttonReturnHome = findViewById(R.id.buttonReturnHome);

        textViewThankYou.setText(String.format(getResources().getString(R.string.thank_you_message), studentName));

        buttonReturnHome.setOnClickListener(v -> {
            Intent newIntent = new Intent(this, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);
            TestEndActivity.this.finish();
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
