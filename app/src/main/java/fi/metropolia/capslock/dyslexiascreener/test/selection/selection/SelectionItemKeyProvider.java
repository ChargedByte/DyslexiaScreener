package fi.metropolia.capslock.dyslexiascreener.test.selection.selection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

import fi.metropolia.capslock.dyslexiascreener.test.selection.SelectionFragment;

/**
 * {@link ItemKeyProvider} for the {@link SelectionFragment}'s selection functionality.
 *
 * @author Peetu Saarinen
 */
public class SelectionItemKeyProvider extends ItemKeyProvider<Long> {
    private final RecyclerView recyclerView;

    public SelectionItemKeyProvider(RecyclerView recyclerView) {
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
