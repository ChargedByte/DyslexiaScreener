package fi.metropolia.capslock.dyslexiascreener.test.selection;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import fi.metropolia.capslock.dyslexiascreener.R;

/**
 * Activity for letter and word selection test items.
 *
 * @author Joel Tikkanen
 * @author Peetu Saarinen
 */

public class SelectionActivity extends AppCompatActivity {
    public static final String EXTRA_ITEMS = "fi.metropolia.capslock.dyslexiascreener.SELECTION_ITEMS";
    public static final String EXTRA_CORRECT = "fi.metropolia.capslock.dyslexiascreener.SELECTION_CORRECT";
    GridView letterGrid;
    TextView itemCaption;
    private int maxCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_selection);

        letterGrid = (GridView) findViewById(R.id.letterGrid);
        itemCaption = (TextView) findViewById(R.id.itemCaption);

        //Adjusting the adapter for word selection
        if (getIntent().getStringExtra(EXTRA_CORRECT).equals("oli")) {
            itemCaption.setText(itemCaption.getText() + " oli");
            letterGrid.setNumColumns(6);
            maxCorrect = 6;
        }

        //Adjusting the adapter for letter selection
        if (getIntent().getStringExtra(EXTRA_CORRECT).equals("b")) {
            itemCaption.setText(itemCaption.getText() + " b");
            letterGrid.setNumColumns(13);
            maxCorrect = 14;
        }

        //Items for the specific test
        String itemsString = getIntent().getStringExtra(EXTRA_ITEMS);

        letterGrid.setAdapter(new CustomAdapter(this, Arrays.asList(itemsString.split(" ")), "oli", maxCorrect));

    }
}
