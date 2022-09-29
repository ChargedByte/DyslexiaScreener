package fi.metropolia.capslock.dyslexiascreener.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Utility-class for dealing with {@link Locale}s.
 *
 * @author Peetu Saarinen
 */
public class LocaleUtil {
    /**
     * Updates the given {@link Context} to use the given language.
     *
     * @param context  A {@link Context} where the language is applied
     * @param language A language {@link String} to be applied
     * @return A {@link Context} with the language changed
     */
    public static Context setLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }
}
