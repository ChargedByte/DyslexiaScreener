package fi.metropolia.capslock.dyslexiascreener.test.reverse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.*;
import fi.metropolia.capslock.dyslexiascreener.test.reverse.selection.ReverseLetterItemDetails;

/**
 * Extension to {@link RecyclerView.Adapter} for displaying items in the {@link ReverseLettersFragment}.
 *
 * @author Joonas Jouttijärvi
 */
public class ReverseLettersAdapter extends RecyclerView.Adapter<ReverseLettersAdapter.ViewHolder> {
    private final List<ReverseLetter> items;

    private SelectionTracker<Long> selectionTracker;

    public ReverseLettersAdapter(List<ReverseLetter> items) {
        this.items = items;
        setHasStableIds(true);
    }

    public void setSelectionTracker(SelectionTracker<Long> selectionTracker) {
        this.selectionTracker = selectionTracker;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_reverse_letter, parent, false));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageId = items.get(position).getResId();

        holder.getImageView().setImageResource(imageId);

        if (selectionTracker != null) {
            if (selectionTracker.isSelected((long) position)) {
                selectionTracker.select((long) position);
                holder.getImageView().setBackgroundResource(R.drawable.background_selection_item);
            } else {
                selectionTracker.deselect((long) position);
                holder.getImageView().setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Extension to {@link RecyclerView.ViewHolder} that provides {@link ReverseLettersAdapter} with the elements in the item {@link View}.
     *
     * @author Joonas Jouttijärvi
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = (ImageView) itemView.getRootView();
        }

        public Context getContext() {
            return context;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public ReverseLetterItemDetails getItemDetails() {
            return new ReverseLetterItemDetails(getAdapterPosition());
        }
    }
}
