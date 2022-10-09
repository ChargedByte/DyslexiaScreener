package fi.metropolia.capslock.dyslexiascreener.settings;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import fi.metropolia.capslock.dyslexiascreener.R;

/**
 * Container activity for the {@link SettingsFragment}.
 *
 * @author Peetu Saarinen
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setTitle(R.string.settings_header);

        getSupportFragmentManager()
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainerViewSettings, new SettingsFragment())
            .commit();
    }
}
