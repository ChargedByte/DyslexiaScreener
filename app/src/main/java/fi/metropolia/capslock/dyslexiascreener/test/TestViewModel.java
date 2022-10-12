package fi.metropolia.capslock.dyslexiascreener.test;

import android.app.Application;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.*;
import fi.metropolia.capslock.dyslexiascreener.data.ScreeningDatabase;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;
import fi.metropolia.capslock.dyslexiascreener.test.recognition.RecognizableWord;
import fi.metropolia.capslock.dyslexiascreener.test.recognition.TextRecognitionFragment;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersFragment;
import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionFragment;

/**
 * {@link AndroidViewModel} for the {@link TestActivity} and {@link ExerciseFragment}s.
 *
 * @author Peetu Saarinen
 */
public class TestViewModel extends AndroidViewModel {
    private final MutableLiveData<Object> exerciseCompleted = new MutableLiveData<>();

    private final ScreeningDatabase database;

    private final Deque<ExerciseFragment> fragments = new ArrayDeque<>(
        List.of(new SelectionFragment(), new ReverseLettersFragment(), new TextRecognitionFragment(),
            new TextRecognitionFragment(), new SelectionFragment(), new TextRecognitionFragment(),
            new SelectionFragment())
    );

    @ArrayRes
    private final Deque<Integer> resourcesSelection = new ArrayDeque<>(List.of(R.array.letterSet1,
        R.array.wordSet1, R.array.letterSet2));

    private final Deque<RecognizableWord> recognizableWords = new ArrayDeque<>(List.of(
        new RecognizableWord(R.drawable.evil, R.string.word_evil), new RecognizableWord(R.drawable.herring, R.string.word_herring),
        new RecognizableWord(R.drawable.monkey, R.string.word_monkey)));

    private Test test;
    private ExerciseFragment currentFragment;
    private final int totalExerciseCount;
    private int currentExerciseNumber = 0;

    public TestViewModel(@NonNull Application application) {
        super(application);
        database = ScreeningDatabase.getInstance(application);
        totalExerciseCount = fragments.size();
    }

    public Test getTest() {
        return test;
    }

    /**
     * Create a new {@link Test} for this viewmodel.
     *
     * @param studentName A {@link String} of student's name
     * @param studentAge  An <code>int</code> of the student's age
     */
    public void createTest(String studentName, int studentAge) {
        test = new Test(studentName, studentAge);
    }

    public MutableLiveData<Object> getExerciseCompleted() {
        return exerciseCompleted;
    }

    public Deque<Integer> getResourcesSelection() {
        return resourcesSelection;
    }

    public Deque<RecognizableWord> getRecognizableWords() {
        return recognizableWords;
    }

    public ExerciseFragment getCurrentFragment() {
        return currentFragment;
    }

    public int getTotalExerciseCount() {
        return totalExerciseCount;
    }

    public int getCurrentExerciseNumber() {
        return currentExerciseNumber;
    }

    /**
     * Proceed to the next {@link ExerciseFragment} in the test.
     * <p>
     * This method updates {@link TestViewModel#currentFragment} with the next fragment or null if no fragments remain.
     */
    public void nextFragment() {
        currentFragment = fragments.pollFirst();
        if (currentFragment != null) {
            currentExerciseNumber++;
        }
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
