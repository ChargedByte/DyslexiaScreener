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

/**
 * Application entrypoint, first activity loaded when the app starts.
 *
 * @author Joel Tikkanen
 * @author Joonas Jouttijärvi
 * @author Peetu Saarinen
 */
public class MainActivity extends BaseActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        floatingActionButton = findViewById(R.id.floatingActionButton);
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

}
