package fi.metropolia.capslock.dyslexiascreener.history;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Test> items;

    public HistoryAdapter() {
        //this.items = Collections.emptyList();
        this.items = Arrays.asList(new Test("Test Name 1", 8), new Test("Test Name 2", 13), new Test("Test Name 3", 10));
    }

    public HistoryAdapter(List<Test> items) {
        this.items = items;
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

        holder.getTextViewName().setText(item.getStudentName());
        holder.getTextViewAge().setText(String.format(resources.getString(R.string.age_message), item.getStudentAge()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewAge() {
            return textViewAge;
        }
    }
}
