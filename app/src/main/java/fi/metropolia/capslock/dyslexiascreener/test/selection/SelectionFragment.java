package fi.metropolia.capslock.dyslexiascreener.test.selection;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;

/**
 * @author Joel Tikkanen
 */
public class SelectionFragment extends ExerciseFragment {
    private static final String ARG_CORRECT_ANSWER = "correct_answer";
    private static final String ARG_ITEMS = "items";
    private String correctAnswer;
    private List<String> items;

    public SelectionFragment() {
    }

    @NonNull
    public static SelectionFragment newInstance(String correctAnswer, String items) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CORRECT_ANSWER, correctAnswer);
        args.putString(ARG_ITEMS, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correctAnswer = requireArguments().getString(ARG_CORRECT_ANSWER);
        items = List.of(requireArguments().getString(ARG_ITEMS).split(" "));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewInstructions = view.findViewById(R.id.textViewInstructions);
        if (correctAnswer.length() == 1) {
            textViewInstructions.setText(String.format(getResources().getString(R.string.selection_instructions_letter), correctAnswer));
        } else {
            textViewInstructions.setText(String.format(getResources().getString(R.string.selection_instructions_word), correctAnswer));
        }

        GridView gridViewItems = view.findViewById(R.id.gridViewItems);
        gridViewItems.setAdapter(new SelectionAdapter(getContext(), items));

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> {
            viewModel.getCompletedExercise().postValue(Pair.create(0, 0));

            // TODO: Count score
        });
    }
}
