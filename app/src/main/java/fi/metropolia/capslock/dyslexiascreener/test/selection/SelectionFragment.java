package fi.metropolia.capslock.dyslexiascreener.test.selection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.selection.SelectionItemDetailsLookup;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.selection.SelectionItemKeyProvider;
import fi.metropolia.capslock.dyslexiascreener.utils.RandomUtil;

/**
 * Fragment where the user has to select the correct items from a grid.
 *
 * @author Joel Tikkanen
 */
public class SelectionFragment extends ExerciseFragment {
    private static final String ARG_CORRECT_ANSWER = "correct_answer";
    private static final String ARG_ITEMS = "items";

    private String correctAnswer;
    private List<String> items;

    private SelectionTracker<Long> tracker;

    public SelectionFragment() {
    }

    @NonNull
    public static SelectionFragment newInstance(@ArrayRes int resId) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ITEMS, resId);
        args.putInt(ARG_CORRECT_ANSWER, -1);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    public static SelectionFragment newInstance(@ArrayRes int resId, int correctIndex) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ITEMS, resId);
        args.putInt(ARG_CORRECT_ANSWER, correctIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            tracker.onRestoreInstanceState(savedInstanceState);
            return;
        }

        int resId = requireArguments().getInt(ARG_ITEMS);
        String[] array = getResources().getStringArray(resId);

        int correctIndex = requireArguments().getInt(ARG_CORRECT_ANSWER);
        correctAnswer = correctIndex == -1 ? RandomUtil.item(array) : array[correctIndex];

        items = new ArrayList<>();

        int itemCount = correctAnswer.length() == 1 ? 40 : 32;
        for (int i = 0; i < itemCount; i++) {
            items.add(RandomUtil.item(array));
        }
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

        SelectionAdapter adapter = new SelectionAdapter(items);

        RecyclerView recyclerViewItems = view.findViewById(R.id.recyclerViewItems);
        recyclerViewItems.setAdapter(adapter);
        recyclerViewItems.setLayoutManager(new GridLayoutManager(getContext(), items.size() / 4));

        tracker = new SelectionTracker.Builder<>(
            "item_selection",
            recyclerViewItems,
            new SelectionItemKeyProvider(recyclerViewItems),
            new SelectionItemDetailsLookup(recyclerViewItems),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();

        adapter.setSelectionTracker(tracker);

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> {
            viewModel.getExerciseCompleted().postValue(null);

            // TODO: Count score
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        tracker.onSaveInstanceState(outState);
    }

    @Override
    public int getAvailablePoints() {
        return (int) items.stream().filter(x -> x.equals(correctAnswer)).count();
    }

    @Override
    public int getScoredPoints() {
        return 0;
    }
}
