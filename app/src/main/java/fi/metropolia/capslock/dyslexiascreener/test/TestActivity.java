package fi.metropolia.capslock.dyslexiascreener.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import fi.metropolia.capslock.dyslexiascreener.*;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Activity for handling the {@link ExerciseFragment}s and the test process.
 *
 * @author Peetu Saarinen
 */
public class TestActivity extends BaseActivity {
    private TestViewModel viewModel;

    private ExerciseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewModel = new ViewModelProvider(this).get(TestViewModel.class);

        Intent intent = getIntent();
        String studentName = intent.getStringExtra(SharedConstants.EXTRA_STUDENT_NAME);
        int studentAge = intent.getIntExtra(SharedConstants.EXTRA_STUDENT_AGE, -1);

        viewModel.setTest(new Test(studentName, studentAge));

        if (savedInstanceState == null) {
            fragment = viewModel.getFragments().removeFirst();

            getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerViewExercise, fragment)
                .commit();
        }

        viewModel.getExerciseCompleted().observe(this, obj -> {
            viewModel.getTest().addAvailablePoints(fragment.getAvailablePoints());
            viewModel.getTest().addStudentPoints(fragment.getScoredPoints());

            fragment = viewModel.getFragments().pollFirst();
            if (fragment != null) {
                getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerViewExercise, fragment)
                    .commit();
                return;
            }

            viewModel.saveTest(viewModel.getTest());

            Intent newIntent = new Intent(this, TestEndActivity.class);
            newIntent.putExtra(SharedConstants.EXTRA_STUDENT_NAME, viewModel.getTest().getStudentName());
            startActivity(newIntent);
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
