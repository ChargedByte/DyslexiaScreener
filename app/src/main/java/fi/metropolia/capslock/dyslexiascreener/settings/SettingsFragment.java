package fi.metropolia.capslock.dyslexiascreener.settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import fi.metropolia.capslock.dyslexiascreener.MainActivity;
import fi.metropolia.capslock.dyslexiascreener.R;

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


        ListPreference selectTime = findPreference("select_time");

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

        ListPreference selectQuestionCount = findPreference("select_question_count");

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

        ListPreference selectLanguage = findPreference("select_language");

        if (selectLanguage != null) {
            String systemLanguage = Locale.getDefault().getLanguage();
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
                Snackbar.make(requireView(), R.string.restart_message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.restart_button, v -> restartApplication()).show();
                return true;
            });
        }
    }

    private void restartApplication() {
        Intent intent = new Intent(getContext(), MainActivity.class);

        int pendingIntentId = 23252156;

        int pendingIntentFlags = PendingIntent.FLAG_CANCEL_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            pendingIntentFlags |= PendingIntent.FLAG_MUTABLE;

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), pendingIntentId, intent, pendingIntentFlags);

        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

        System.exit(0);
    }
}
