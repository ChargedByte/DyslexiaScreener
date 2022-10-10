package fi.metropolia.capslock.dyslexiascreener.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.preference.PreferenceManager;

import java.util.Locale;

import fi.metropolia.capslock.dyslexiascreener.SharedConstants;

/**
 * Utility to help with localization tasks.
 *
 * @author Peetu Saarinen
 */
public final class LocaleUtil {

    /**
     * Updates the provided {@link Context} object with the language saved in application's preferences.
     *
     * @param context A {@link Context} where the language is applied
     * @return A {@link Context} with the language changed
     */
    public static Context updateBaseContextLanguage(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String language = sharedPreferences.getString(SharedConstants.PREF_APP_LANGUAGE, SharedConstants.LANGUAGE_DEFAULT);

        Locale locale = new Locale(language);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }
}
