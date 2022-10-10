package fi.metropolia.capslock.dyslexiascreener.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * ViewModel for the {@link HistoryActivity}.
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

    /**
     * Save the provided entity to the database
     *
     * @param entity A {@link Test} entity to be saved
     */
    public void saveTest(Test entity) {
        database.testDao().insert(entity);
    }

    /**
     * Delete the provided entity from the database
     *
     * @param entity A {@link Test} entity to be deleted
     */
    public void deleteTest(Test entity) {
        database.testDao().delete(entity);
    }
}
