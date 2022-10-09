package fi.metropolia.capslock.dyslexiascreener.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.SharedConstants;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

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

        viewModel.setTest(new Test(studentName, studentAge));

        if (savedInstanceState == null) {

        }

        viewModel.getExerciseCompleted().observe(this, obj -> {


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
