package fi.metropolia.capslock.dyslexiascreener.test;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;

/**
 * ViewModel-class for the {@link TestActivity}.
 *
 * @author Peetu Saarinen
 */
public class TestViewModel extends AndroidViewModel {
    private final MutableLiveData<Pair<Integer, Integer>> completedExercise = new MutableLiveData<>();

    private final ScreeningDatabase database;

    public TestViewModel(@NonNull Application application) {
        super(application);
        database = ScreeningDatabase.getInstance(application);
    }

    public MutableLiveData<Pair<Integer, Integer>> getCompletedExercise() {
        return completedExercise;
    }
}
