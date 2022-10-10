package fi.metropolia.capslock.dyslexiascreener.test.selection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.stream.StreamSupport;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;
import fi.metropolia.capslock.dyslexiascreener.test.selection.selection.SelectionItemDetailsLookup;
import fi.metropolia.capslock.dyslexiascreener.test.selection.selection.SelectionItemKeyProvider;
import fi.metropolia.capslock.dyslexiascreener.utils.RandomUtil;

/**
 * Fragment where the user has to select the correct items from a grid.
 *
 * @author Joel Tikkanen
 */
public class SelectionFragment extends ExerciseFragment {
    private static final String ARG_CORRECT_ANSWER = "correctAnswer";
    private static final String ARG_ITEMS = "listItems";

    private List<String> items;
    private String correctAnswer;

    private SelectionTracker<Long> tracker;

    public SelectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            items = List.of(savedInstanceState.getString(ARG_ITEMS).split(","));
            correctAnswer = savedInstanceState.getString(ARG_CORRECT_ANSWER);
            return;
        }

        int resId = viewModel.getResourcesSelection().removeFirst();
        String[] array = getResources().getStringArray(resId);

        correctAnswer = RandomUtil.item(array);

        items = new ArrayList<>();

        int itemCount = correctAnswer.length() == 1 ? 40 : 20;
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

        if (savedInstanceState != null) {
            tracker.onRestoreInstanceState(savedInstanceState);
        }

        adapter.setSelectionTracker(tracker);

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> viewModel.getExerciseCompleted().postValue(null));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        tracker.onSaveInstanceState(outState);
        outState.putString(ARG_ITEMS, String.join(",", items));
        outState.putString(ARG_CORRECT_ANSWER, correctAnswer);
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getAvailablePoints() {
        return (int) items.stream().filter(x -> x.equals(correctAnswer)).count();
    }

    @Override
    public int getScoredPoints() {
        int incorrectSelection = (int) StreamSupport.stream(tracker.getSelection().spliterator(), false)
            .mapToInt(Long::intValue)
            .filter(x -> !items.get(x).equals(correctAnswer))
            .count();

        int correctSelection = (int) StreamSupport.stream(tracker.getSelection().spliterator(), false)
            .mapToInt(Long::intValue)
            .filter(x -> items.get(x).equals(correctAnswer))
            .count();

        return correctSelection - incorrectSelection;
    }
}
