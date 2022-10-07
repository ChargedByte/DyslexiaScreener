package fi.metropolia.capslock.dyslexiascreener.history;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fi.metropolia.capslock.dyslexiascreener.R;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Activity-class for displaying saved {@link Test} entities.
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

        recyclerViewHistory.setLayoutManager(new GridLayoutManager(this, 1));

        HistoryAdapter adapter = new HistoryAdapter();
        viewModel.getTestListLiveData().observe(this, adapter::setItems);

        recyclerViewHistory.setAdapter(adapter);
    }
}
