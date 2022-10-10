package fi.metropolia.capslock.dyslexiascreener.history;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.*;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Extension to {@link RecyclerView.Adapter} for displaying {@link Test} entities in the {@link HistoryActivity}.
 *
 * @author Peetu Saarinen
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final List<Test> items;

    private final MutableLiveData<Pair<Integer, Test>> itemDeleted = new MutableLiveData<>();

    public HistoryAdapter(List<Test> items) {
        this.items = items;
    }

    public List<Test> getItems() {
        return items;
    }

    public MutableLiveData<Pair<Integer, Test>> getItemDeleted() {
        return itemDeleted;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Test item = items.get(position);

        Context context = holder.itemView.getContext();
        Resources resources = context.getResources();

        String[] array = resources.getStringArray(R.array.dyslexiaPossibleOptions);

        String possible = item.isDyslexiaPossible() ? array[0] : array[1];

        holder.getTextViewDyslexia()
            .setText(String.format(resources.getString(R.string.dyslexia_possible), possible));

        holder.getTextViewNameAndAge()
            .setText(String.format("%s, %s", item.getStudentName(), String.format(resources.getString(R.string.age_message), item.getStudentAge())));

        holder.getTextViewDateTime()
            .setText(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(holder.getContext().getResources().getConfiguration().getLocales().get(0))
                .format(item.getTimestamp()));

        holder.getTextViewScore()
            .setText(String.format(resources.getString(R.string.score_message),
                item.getStudentPoints() + "/" + item.getAvailablePoints()));

        holder.getItemView().setOnLongClickListener(v -> {
            items.remove(item);
            itemDeleted.postValue(Pair.create(position, item));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Extension to {@link RecyclerView.ViewHolder} that provides {@link HistoryAdapter} with the elements in the item {@link View}.
     *
     * @author Peetu Saarinen
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final View itemView;
        private final TextView textViewNameAndAge;
        private final TextView textViewDateTime;
        private final TextView textViewScore;
        private final TextView textViewDyslexia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            this.itemView = itemView;
            textViewNameAndAge = itemView.findViewById(R.id.textViewNameAndAge);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewScore = itemView.findViewById(R.id.textViewScore);
            textViewDyslexia = itemView.findViewById(R.id.textViewDyslexia);
        }

        public Context getContext() {
            return context;
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getTextViewNameAndAge() {
            return textViewNameAndAge;
        }

        public TextView getTextViewDateTime() {
            return textViewDateTime;
        }

        public TextView getTextViewScore() {
            return textViewScore;
        }

        public TextView getTextViewDyslexia() {
            return textViewDyslexia;
        }
    }
}
