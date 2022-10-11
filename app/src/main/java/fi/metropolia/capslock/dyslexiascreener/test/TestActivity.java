package fi.metropolia.capslock.dyslexiascreener.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import fi.metropolia.capslock.dyslexiascreener.*;

/**
 * Activity for handling the {@link ExerciseFragment}s and the test process.
 *
 * @author Peetu Saarinen
 */
public class TestActivity extends BaseActivity {
    private TestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewModel = new ViewModelProvider(this).get(TestViewModel.class);

        Intent intent = getIntent();
        String studentName = intent.getStringExtra(SharedConstants.EXTRA_STUDENT_NAME);
        int studentAge = intent.getIntExtra(SharedConstants.EXTRA_STUDENT_AGE, -1);

        if (savedInstanceState == null) {
            viewModel.createTest(studentName, studentAge);

            viewModel.nextFragment();
            getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerViewExercise, viewModel.getCurrentFragment())
                .commit();
        }

        viewModel.getExerciseCompleted().observe(this, obj -> {
            viewModel.getTest().addAvailablePoints(viewModel.getCurrentFragment().getAvailablePoints());
            viewModel.getTest().addStudentPoints(viewModel.getCurrentFragment().getScoredPoints());


            viewModel.nextFragment();
            if (viewModel.getCurrentFragment() != null) {
                getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerViewExercise, viewModel.getCurrentFragment())
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
