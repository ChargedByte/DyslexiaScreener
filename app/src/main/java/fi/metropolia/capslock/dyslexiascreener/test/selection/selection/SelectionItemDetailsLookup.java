package fi.metropolia.capslock.dyslexiascreener.test.selection.selection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionAdapter;
import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionFragment;
import fi.metropolia.capslock.dyslexiascreener.utils.EmptyItemDetails;

/**
 * {@link ItemDetailsLookup} for the {@link SelectionFragment}'s selection functionality.
 *
 * @author Peetu Saarinen
 */
public class SelectionItemDetailsLookup extends ItemDetailsLookup<Long> {
    private final RecyclerView recyclerView;

    public SelectionItemDetailsLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent event) {
        View view = recyclerView.findChildViewUnder(event.getX(), event.getY());
        if (view != null) {
            return ((SelectionAdapter.ViewHolder) recyclerView.getChildViewHolder(view)).getItemDetails();
        }
        return new EmptyItemDetails();
    }
}
