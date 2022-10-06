package fi.metropolia.capslock.dyslexiascreener;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import fi.metropolia.capslock.dyslexiascreener.utils.LocaleUtil;

/**
 * Activity-class that provides shared defaults for other activities in the application.
 *
 * @author Peetu Saarinen
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleUtil.updateBaseContextLanguage(newBase));
    }
}
