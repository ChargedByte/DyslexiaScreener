package fi.metropolia.capslock.dyslexiascreener.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

public class HistoryViewModel extends AndroidViewModel {
    private ScreeningDatabase database;

    private LiveData<List<Test>> testListLiveData;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        database = ScreeningDatabase.getInstance(application);
        testListLiveData = database.testDao().loadAll();
    }

    public LiveData<List<Test>> getTestListLiveData() {
        return testListLiveData;
    }
}
