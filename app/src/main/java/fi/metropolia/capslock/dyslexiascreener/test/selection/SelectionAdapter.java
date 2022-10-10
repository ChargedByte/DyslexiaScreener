package fi.metropolia.capslock.dyslexiascreener.test.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.test.selection.selection.SelectionItemDetails;

/**
 * Extension to {@link RecyclerView.Adapter} for displaying items in the {@link SelectionFragment}.
 *
 * @author Joel Tikkanen
 */
public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.ViewHolder> {
    private final List<String> items;

    private SelectionTracker<Long> selectionTracker;

    public SelectionAdapter(List<String> items) {
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
            .inflate(R.layout.item_selection, parent, false));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);

        holder.getTextView().setText(item);

        if (selectionTracker != null) {
            if (selectionTracker.isSelected((long) position)) {
                selectionTracker.select((long) position);
                holder.getTextView().setBackgroundResource(R.drawable.background_selection_item);
            } else {
                selectionTracker.deselect((long) position);
                holder.getTextView().setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Extension to {@link RecyclerView.ViewHolder} that provides {@link SelectionAdapter} with the elements in the item {@link View}.
     *
     * @author Joel Tikkanen
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView = (TextView) itemView.getRootView();
        }

        public Context getContext() {
            return context;
        }

        public TextView getTextView() {
            return textView;
        }

        public SelectionItemDetails getItemDetails() {
            return new SelectionItemDetails(getAdapterPosition());
        }
    }
}
