package fi.metropolia.capslock.dyslexiascreener.test.reverse;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;

/**
 * @author Joonas Jouttijärvi
 */
public class ReverseLettersFragment extends ExerciseFragment {
    private int points = 0;
    private int clicksLeft = 8;

    public ReverseLettersFragment() {
    }

    @NonNull
    public static ReverseLettersFragment newInstance() {
        ReverseLettersFragment fragment = new ReverseLettersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reverse_letters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView gridViewItems = view.findViewById(R.id.gridViewItems);
        gridViewItems.setAdapter(new ReverseLettersAdapter(getContext()));
        gridViewItems.setDrawSelectorOnTop(true);

        gridViewItems.setOnItemClickListener((parent, v, position, id) -> {
            clicksLeft--;
            if (clicksLeft == 0) {
                gridViewItems.setVisibility(View.GONE);
            }

            if (position == 2) {
                points++;
            }
            if (position == 5) {
                points++;
            }
            if (position == 7) {
                points++;
            }
            if (position == 15) {
                points++;
            }
            if (position == 17) {
                points++;
            }
            if (position == 14) {
                points++;
            }
            if (position == 16) {
                points++;
            }
            if (position == 18) {
                points++;
            }
        });

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> {
            viewModel.getCompletedExercise().postValue(Pair.create(0, 0));

            // TODO: Count score
        });
    }
}
