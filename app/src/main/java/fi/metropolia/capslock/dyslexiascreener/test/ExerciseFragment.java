package fi.metropolia.capslock.dyslexiascreener.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * Fragment that provides shared functionality between individual tests.
 *
 * @author Peetu Saarinen
 */
public abstract class ExerciseFragment extends Fragment {
    protected TestViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(TestViewModel.class);
    }

    public abstract int getAvailablePoints();

    public abstract int getScoredPoints();
}
