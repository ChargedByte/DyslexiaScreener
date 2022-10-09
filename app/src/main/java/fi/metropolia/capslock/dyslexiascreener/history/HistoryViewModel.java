package fi.metropolia.capslock.dyslexiascreener.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * ViewModel-class for the {@link HistoryActivity}.
 *
 * @author Peetu Saarinen
 */
public class HistoryViewModel extends AndroidViewModel {
    private final ScreeningDatabase database;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        database = ScreeningDatabase.getInstance(application);
    }

    public List<Test> getAllTests() {
        return database.testDao().findAll();
    }
}
