package fi.metropolia.capslock.dyslexiascreener;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fi.metropolia.capslock.dyslexiascreener.history.HistoryActivity;
import fi.metropolia.capslock.dyslexiascreener.settings.SettingsActivity;
import fi.metropolia.capslock.dyslexiascreener.test.TestActivity;

/**
 * Activity loaded first when the application starts.
 *
 * @author Joel Tikkanen
 * @author Joonas Jouttijärvi
 * @author Peetu Saarinen
 */
public class MainActivity extends BaseActivity {
    private static final String STATE_NAME = "studentName";
    private static final String STATE_AGE = "studentAge";

    private EditText editTextName;
    private EditText editTextAge;
    private FloatingActionButton floatingActionButtonStartTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(R.string.app_name);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        floatingActionButtonStartTest = findViewById(R.id.floatingActionButtonStartTest);

        floatingActionButtonStartTest.setOnClickListener(v -> {
            if (validateInputs()) {
                String studentName = editTextName.getText().toString();
                int studentAge = Integer.parseInt(editTextAge.getText().toString());

                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra(SharedConstants.EXTRA_STUDENT_NAME, studentName);
                intent.putExtra(SharedConstants.EXTRA_STUDENT_AGE, studentAge);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_NAME, editTextName.getText().toString());
        outState.putString(STATE_AGE, editTextAge.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editTextName.setText(savedInstanceState.getString(STATE_NAME));
        editTextAge.setText(savedInstanceState.getString(STATE_AGE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.root_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuItemSettings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (itemId == R.id.menuItemHistory) {
            startActivity(new Intent(this, HistoryActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Verifies if the user inputs provided match the requirements.
     *
     * @return <code>true</code> if all checks passed, otherwise <code>false</code>
     */
    private boolean validateInputs() {
        String nameText = editTextName.getText().toString();
        String ageText = editTextAge.getText().toString();

        if (nameText.isBlank()) {
            editTextName.setError(getResources().getString(R.string.error_empty_name));
            return false;
        }

        if (ageText.isBlank()) {
            editTextAge.setError(getResources().getString(R.string.error_empty_age));
            return false;
        }

        int studentAge;
        try {
            studentAge = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            editTextAge.setError(getResources().getString(R.string.error_invalid_age));
            return false;
        }

        if (studentAge < 0) {
            editTextAge.setError(getResources().getString(R.string.error_negative_age));
            return false;
        }

        if (studentAge < 7) {
            editTextAge.setError(getResources().getString(R.string.age_min_message));
            return false;
        }

        if (studentAge > 14) {
            editTextAge.setError(getResources().getString(R.string.age_max_message));
            return false;
        }

        return true;
    }
}
