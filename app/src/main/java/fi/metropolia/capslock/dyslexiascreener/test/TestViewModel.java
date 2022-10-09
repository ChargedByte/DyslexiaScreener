package fi.metropolia.capslock.dyslexiascreener.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * ViewModel for the {@link TestActivity}.
 *
 * @author Peetu Saarinen
 */
public class TestViewModel extends AndroidViewModel {
    private final MutableLiveData<Object> exerciseCompleted = new MutableLiveData<>();
    private final MutableLiveData<Object> timerExpired = new MutableLiveData<>();

    private final ScreeningDatabase database;

    private Test test;

    public TestViewModel(@NonNull Application application) {
        super(application);
        database = ScreeningDatabase.getInstance(application);
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public MutableLiveData<Object> getExerciseCompleted() {
        return exerciseCompleted;
    }

    public MutableLiveData<Object> getTimerExpired() {
        return timerExpired;
    }

    /**
     * Saves the provided {@link Test} entity to the database.
     *
     * @param test A {@link Test} entity to be saved
     */
    public void saveTest(Test test) {
        database.testDao().insert(test);
    }
}
