package fi.metropolia.capslock.dyslexiascreener.settings;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.SharedConstants;

/**
 * Fragment that displays application's settings and saves them automatically.
 *
 * @author Peetu Saarinen
 * @see PreferenceFragmentCompat
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Resources resources = getResources();


        ListPreference selectTime = findPreference(SharedConstants.PREF_TEST_TIME_LIMIT);

        if (selectTime != null) {
            int[] timeLimits = {4, 6, 8, 12};

            selectTime.setEntries(
                Arrays.stream(timeLimits)
                    .mapToObj(i -> String.format(resources.getString(R.string.select_time_description), i))
                    .toArray(String[]::new)
            );
            selectTime.setEntryValues(
                Arrays.stream(timeLimits)
                    .mapToObj(Integer::toString)
                    .toArray(String[]::new)
            );

            selectTime.setSummaryProvider((Preference.SummaryProvider<ListPreference>) preference -> {
                CharSequence entry = preference.getEntry();
                if (entry == null)
                    return null;
                return entry.toString();
            });
        }

        ListPreference selectQuestionCount = findPreference(SharedConstants.PREF_TEST_EXERCISES);

        if (selectQuestionCount != null) {
            int[] questionCounts = {4, 6, 8, 12};

            selectQuestionCount.setEntries(
                Arrays.stream(questionCounts)
                    .mapToObj(i -> String.format(resources.getString(R.string.select_question_count_description), i))
                    .toArray(String[]::new)
            );
            selectQuestionCount.setEntryValues(
                Arrays.stream(questionCounts)
                    .mapToObj(Integer::toString)
                    .toArray(String[]::new)
            );

            selectQuestionCount.setSummaryProvider((Preference.SummaryProvider<ListPreference>) preference -> {
                CharSequence entry = preference.getEntry();
                if (entry == null)
                    return null;
                return entry.toString();
            });
        }

        ListPreference selectLanguage = findPreference(SharedConstants.PREF_APP_LANGUAGE);

        if (selectLanguage != null) {
            String systemLanguage = getResources().getConfiguration().getLocales().get(0).getLanguage();
            Optional<String> systemLanguageEntry = Arrays.stream(selectLanguage.getEntryValues())
                .map(CharSequence::toString)
                .filter(s -> new Locale(s).getLanguage().equals(systemLanguage))
                .findFirst();

            if (selectLanguage.getValue() == null && systemLanguageEntry.isPresent())
                selectLanguage.setValue(systemLanguageEntry.get());

            selectLanguage.setSummaryProvider((Preference.SummaryProvider<ListPreference>) preference -> {
                CharSequence entry = preference.getEntry();
                if (entry == null)
                    return null;
                return entry.toString();
            });

            selectLanguage.setOnPreferenceChangeListener((preference, newValue) -> {
                requireActivity().recreate();
                return true;
            });
        }
    }
}
