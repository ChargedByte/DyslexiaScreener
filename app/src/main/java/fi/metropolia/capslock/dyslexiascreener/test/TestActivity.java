package fi.metropolia.capslock.dyslexiascreener.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayDeque;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.SharedConstants;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;
import fi.metropolia.capslock.dyslexiascreener.test.recognition.TextRecognitionFragment;

/**
 * Activity for handling the {@link ExerciseFragment}s and the test process.
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

        Intent intent = getIntent();
        String studentName = intent.getStringExtra(SharedConstants.EXTRA_STUDENT_NAME);
        int studentAge = intent.getIntExtra(SharedConstants.EXTRA_STUDENT_AGE, -1);

        Test test = new Test(studentName, studentAge);

        ArrayDeque<ExerciseFragment> fragments = new ArrayDeque<>();
        fragments.add(TextRecognitionFragment.newInstance());

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerViewExercise, fragments.removeFirst())
                .commit();
        }

        viewModel.getExerciseCompleted().observe(this, obj -> {
            ExerciseFragment nextFragment = fragments.pollFirst();
            if (nextFragment != null) {
                fragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerViewExercise, nextFragment)
                    .commit();
                return;
            }

            viewModel.saveTest(test);

            Intent newIntent = new Intent(this, TestEndActivity.class);
            newIntent.putExtra(SharedConstants.EXTRA_STUDENT_NAME, test.getStudentName());
            startActivity(newIntent);
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
