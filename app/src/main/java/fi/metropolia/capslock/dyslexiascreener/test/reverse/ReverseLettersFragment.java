package fi.metropolia.capslock.dyslexiascreener.test.reverse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.ExerciseFragment;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.selection.ReverseLetterItemKeyProvider;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.selection.ReverseLetterItemLookup;
import fi.metropolia.capslock.dyslexiascreener.utils.RandomUtil;

/**
 * Fragment where the user has to select images in the grid where the letter is reversed.
 *
 * @author Joonas Jouttijärvi
 */
public class ReverseLettersFragment extends ExerciseFragment {
    private static final String STATE_ITEMS = "listItems";

    private static final ReverseLetter[] reverseLetters = {
        new ReverseLetter(R.drawable.letter_b, false),
        new ReverseLetter(R.drawable.letter_c, false),
        new ReverseLetter(R.drawable.letter_c_reverse, true),
        new ReverseLetter(R.drawable.letter_e, false),
        new ReverseLetter(R.drawable.letter_e_reverse, true),
        new ReverseLetter(R.drawable.letter_f, false),
        new ReverseLetter(R.drawable.letter_f_reverse, true),
        new ReverseLetter(R.drawable.letter_g, false),
        new ReverseLetter(R.drawable.letter_h, false),
        new ReverseLetter(R.drawable.letter_h_reverse, true),
        new ReverseLetter(R.drawable.letter_j, false),
        new ReverseLetter(R.drawable.letter_j_reverse, true),
        new ReverseLetter(R.drawable.letter_p, false),
        new ReverseLetter(R.drawable.letter_s, false),
        new ReverseLetter(R.drawable.letter_s_reverse, true),
        new ReverseLetter(R.drawable.letter_t, false),
    };

    private List<ReverseLetter> items;

    private SelectionTracker<Long> tracker;

    public ReverseLettersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Type listType = new TypeToken<List<ReverseLetter>>() {
            }.getType();
            items = new Gson().fromJson(savedInstanceState.getString(STATE_ITEMS), listType);
            return;
        }

        items = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            items.add(RandomUtil.item(reverseLetters));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reverse_letters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ReverseLettersAdapter adapter = new ReverseLettersAdapter(items);

        RecyclerView recyclerViewItems = view.findViewById(R.id.recyclerViewItems);
        recyclerViewItems.setAdapter(adapter);
        recyclerViewItems.setLayoutManager(new GridLayoutManager(getContext(), items.size() / 3));

        tracker = new SelectionTracker.Builder<>(
            "reverse_letter_selection",
            recyclerViewItems,
            new ReverseLetterItemKeyProvider(recyclerViewItems),
            new ReverseLetterItemLookup(recyclerViewItems),
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
        outState.putString(STATE_ITEMS, new Gson().toJson(items));
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getAvailablePoints() {
        return (int) items.stream().filter(ReverseLetter::isReversed).count();
    }

    @Override
    public int getScoredPoints() {
        int incorrectSelections = (int) StreamSupport.stream(tracker.getSelection().spliterator(), false)
            .mapToInt(Long::intValue)
            .mapToObj(x -> items.get(x))
            .filter(x -> !x.isReversed())
            .count();

        int correctSelections = (int) StreamSupport.stream(tracker.getSelection().spliterator(), false)
            .mapToInt(Long::intValue)
            .mapToObj(x -> items.get(x))
            .filter(ReverseLetter::isReversed)
            .count();
        
        return correctSelections - incorrectSelections;
    }
}
