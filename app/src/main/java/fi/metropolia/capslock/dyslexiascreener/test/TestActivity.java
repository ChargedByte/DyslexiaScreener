package fi.metropolia.capslock.dyslexiascreener.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayDeque;

import fi.metropolia.capslock.dyslexiascreener.MainActivity;
import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;
import fi.metropolia.capslock.dyslexiascreener.test.recognition.TextRecognitionFragment;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersFragment;
import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionFragment;

/**
 * Activity-class for handling the {@link ExerciseFragment}s and tracking the test process.
 *
 * @author Peetu Saarinen
 */
public class TestActivity extends AppCompatActivity {

    private TestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewModel = new ViewModelProvider(this).get(TestViewModel.class);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {

        }

        Intent intent = getIntent();
        String studentName = intent.getStringExtra(MainActivity.EXTRA_STUDENT_NAME);
        int studentAge = intent.getIntExtra(MainActivity.EXTRA_STUDENT_AGE, -1);

        Test test = new Test(studentName, studentAge);

        ArrayDeque<ExerciseFragment> fragments = new ArrayDeque<>();
        fragments.add(SelectionFragment.newInstance("b", "b d b q d b p d b b q q b p d q b d d b b d p q b d q b d b p p d b p b d p b"));
        fragments.add(SelectionFragment.newInstance("was", "was raw saw way was saw saw was way saw win war was why saw was saw was"));
        fragments.add(ReverseLettersFragment.newInstance());
        fragments.add(TextRecognitionFragment.newInstance());

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerViewExercise, fragments.removeFirst())
                .commit();
        }

        viewModel.getCompletedExercise().observe(this, pair -> {
            test.addStudentPoints(pair.first);
            test.addAvailablePoints(pair.second);

            ExerciseFragment nextFragment = fragments.pollFirst();
            if (nextFragment != null) {
                fragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerViewExercise, nextFragment)
                    .commit();
            }

            // TODO: End
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
