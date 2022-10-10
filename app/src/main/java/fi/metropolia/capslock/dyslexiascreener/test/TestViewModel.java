package fi.metropolia.capslock.dyslexiascreener.test;

import android.app.Application;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayDeque;
import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;
import fi.metropolia.capslock.dyslexiascreener.test.recognition.RecognizableWord;
import fi.metropolia.capslock.dyslexiascreener.test.recognition.TextRecognitionFragment;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersFragment;
import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionFragment;

/**
 * ViewModel for the {@link TestActivity}.
 *
 * @author Peetu Saarinen
 */
public class TestViewModel extends AndroidViewModel {
    private final MutableLiveData<Object> exerciseCompleted = new MutableLiveData<>();

    private final ScreeningDatabase database;

    private final ArrayDeque<ExerciseFragment> fragments = new ArrayDeque<>(
        List.of(new SelectionFragment(), new ReverseLettersFragment(), new TextRecognitionFragment(),
            new TextRecognitionFragment(), new SelectionFragment(), new TextRecognitionFragment(),
            new SelectionFragment())
    );

    @ArrayRes
    private final ArrayDeque<Integer> resourcesSelection = new ArrayDeque<>(List.of(R.array.letterSet1,
        R.array.wordSet1, R.array.letterSet2));

    private final ArrayDeque<RecognizableWord> recognizableWords = new ArrayDeque<>(List.of(
        new RecognizableWord(R.drawable.evil, R.string.word_evil), new RecognizableWord(R.drawable.herring, R.string.word_herring),
        new RecognizableWord(R.drawable.monkey, R.string.word_monkey)));

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

    public ArrayDeque<Integer> getResourcesSelection() {
        return resourcesSelection;
    }

    public ArrayDeque<RecognizableWord> getRecognizableWords() {
        return recognizableWords;
    }

    public ArrayDeque<ExerciseFragment> getFragments() {
        return fragments;
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
