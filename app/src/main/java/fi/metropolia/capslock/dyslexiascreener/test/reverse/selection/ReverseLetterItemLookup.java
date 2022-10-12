package fi.metropolia.capslock.dyslexiascreener.test.reverse.selection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersAdapter;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.ReverseLettersFragment;
import fi.metropolia.capslock.dyslexiascreener.utils.EmptyItemDetails;

/**
 * {@link ItemDetailsLookup} for the {@link ReverseLettersFragment}'s selection functionality.
 *
 * @author Peetu Saarinen
 */
public class ReverseLetterItemLookup extends ItemDetailsLookup<Long> {
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
        return new EmptyItemDetails();
    }
}
