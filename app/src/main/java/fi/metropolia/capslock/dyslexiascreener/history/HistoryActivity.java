package fi.metropolia.capslock.dyslexiascreener.history;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Activity for displaying saved {@link Test} entities.
 *
 * @author Peetu Saarinen
 */
public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewHistory;

    private HistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(R.string.history_header);

        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);

        HistoryAdapter adapter = new HistoryAdapter(viewModel.getAllTests());
        adapter.getItemDeleted().observe(this, pair -> {
            int position = pair.first;
            Test item = pair.second;

            viewModel.deleteTest(item);
            adapter.notifyItemRemoved(position);
            Snackbar.make(recyclerViewHistory, R.string.deleted_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo_button, v -> {
                    viewModel.saveTest(item);
                    adapter.getItems().add(position, item);
                    adapter.notifyItemInserted(position);
                }).show();
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerViewHistory.setLayoutManager(layoutManager);
        recyclerViewHistory.setAdapter(adapter);
        recyclerViewHistory.addItemDecoration(new DividerItemDecoration(recyclerViewHistory.getContext(),
            layoutManager.getOrientation()));
    }
}
