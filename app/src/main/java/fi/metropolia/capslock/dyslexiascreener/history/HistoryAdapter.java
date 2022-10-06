package fi.metropolia.capslock.dyslexiascreener.history;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Extension-class to {@link RecyclerView.Adapter} for displaying {@link Test} entities in the {@link HistoryActivity}.
 *
 * @author Peetu Saarinen
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Test> items;

    /**
     * Constructs a {@link HistoryAdapter} class with an empty <code>items</code> list.
     */
    public HistoryAdapter() {
        this.items = Collections.emptyList();
    }

    public void setItems(List<Test> items) {
        this.items = items;
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

        String probability = resources.getStringArray(R.array.probabilities)[0]; // TODO: Calculate probability

        holder.getTextViewNameAndAge()
            .setText(String.format("%s, %s", item.getStudentName(), String.format(resources.getString(R.string.age_message), item.getStudentAge())));

        holder.getTextViewDateTime()
            .setText(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(item.getTimestamp()));

        holder.getTextViewProbability()
            .setText(String.format(resources.getString(R.string.probability_message), probability));

        holder.getTextViewScore()
            .setText(String.format(resources.getString(R.string.score_message),
                item.getStudentPoints() + "/" + item.getAvailablePoints()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Extension-class to {@link RecyclerView.ViewHolder} that provides {@link HistoryAdapter} with the elements in the item {@link View}.
     *
     * @author Peetu Saarinen
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNameAndAge;
        private final TextView textViewDateTime;
        private final TextView textViewProbability;
        private final TextView textViewScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNameAndAge = itemView.findViewById(R.id.textViewNameAndAge);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewProbability = itemView.findViewById(R.id.textViewProbability);
            textViewScore = itemView.findViewById(R.id.textViewScore);
        }

        public TextView getTextViewNameAndAge() {
            return textViewNameAndAge;
        }

        public TextView getTextViewDateTime() {
            return textViewDateTime;
        }

        public TextView getTextViewProbability() {
            return textViewProbability;
        }

        public TextView getTextViewScore() {
            return textViewScore;
        }
    }
}
