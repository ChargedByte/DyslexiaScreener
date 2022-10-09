package fi.metropolia.capslock.dyslexiascreener.test.reverse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
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
import fi.metropolia.capslock.dyslexiascreener.utils.RandomUtil;

/**
 * Fragment where the user has to select images in the grid where the letter is reversed.
 *
 * @author Joonas Jouttijärvi
 */
public class ReverseLettersFragment extends ExerciseFragment {
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
        new ReverseLetter(R.drawable.letter_q, false),
        new ReverseLetter(R.drawable.letter_s, false),
        new ReverseLetter(R.drawable.letter_t, false),
        new ReverseLetter(R.drawable.letter_z, false),
    };

    private static List<ReverseLetter> items;

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

        items = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
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

        int spanCount = items.size() / 4;

        RecyclerView recyclerViewItems = view.findViewById(R.id.recyclerViewItems);
        recyclerViewItems.setAdapter(adapter);
        recyclerViewItems.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        SelectionTracker<Long> tracker = new SelectionTracker.Builder<>(
            "reverse_letter_selection",
            recyclerViewItems,
            new ReverseLetterItemKeyProvider(recyclerViewItems),
            new ReverseLetterItemLookup(recyclerViewItems),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();

        adapter.setSelectionTracker(tracker);

        tracker.addObserver(new SelectionTracker.SelectionObserver<>() {
            @Override
            public void onItemStateChanged(@NonNull Long key, boolean selected) {
                super.onItemStateChanged(key, selected);
            }
        });

        FloatingActionButton floatingActionButtonNext = view.findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext.setOnClickListener(v -> {
            viewModel.getExerciseCompleted().postValue(null);

            // TODO: Count score
        });
    }

    @Override
    public int getAvailablePoints() {
        return 0;
    }

    @Override
    public int getScoredPoints() {
        return 0;
    }

    private static class ReverseLetterItemKeyProvider extends ItemKeyProvider<Long> {
        private final RecyclerView recyclerView;

        public ReverseLetterItemKeyProvider(RecyclerView recyclerView) {
            super(SCOPE_MAPPED);
            this.recyclerView = recyclerView;
        }

        @Nullable
        @Override
        public Long getKey(int position) {
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter == null)
                throw new IllegalStateException("RecyclerView adapter is not set");
            return adapter.getItemId(position);
        }

        @Override
        public int getPosition(@NonNull Long key) {
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForItemId(key);
            if (viewHolder == null)
                return RecyclerView.NO_POSITION;
            return viewHolder.getLayoutPosition();
        }
    }

    private static class ReverseLetterItemLookup extends ItemDetailsLookup<Long> {
        private final RecyclerView recyclerView;

        public ReverseLetterItemLookup(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Nullable
        @Override
        public ItemDetails<Long> getItemDetails(@NonNull MotionEvent event) {
            View view = recyclerView.findChildViewUnder(event.getX(), event.getY());
            if (view != null) {
                return ((ReverseLettersAdapter.ViewHolder) recyclerView.getChildViewHolder(view)).getItemDetails();
            }
            return null;
        }
    }
}
