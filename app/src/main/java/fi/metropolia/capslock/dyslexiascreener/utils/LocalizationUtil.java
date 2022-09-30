package fi.metropolia.capslock.dyslexiascreener.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Utility-class to help with localization tasks.
 *
 * @author Peetu Saarinen
 */
public class LocalizationUtil {
    /**
     * Updates the given {@link Context} to use the given language.
     *
     * @param context      A {@link Context} where the language is applied
     * @param languageCode A language {@link String} to be applied
     * @return A {@link Context} with the language changed
     */
    public static Context applyLanguage(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }
}
