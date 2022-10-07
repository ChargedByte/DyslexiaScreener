package fi.metropolia.capslock.dyslexiascreener.test.reversedletter;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import fi.metropolia.capslock.dyslexiascreener.R;

public class ReversedLetters extends AppCompatActivity {


    public GridView aGridView;
    private int Points = 0;
    private int Clicksleft = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reversed_letters);
        aGridView = findViewById(R.id.grid_view);
        aGridView.setAdapter(new ReversedLetterAdapter(this));
        aGridView.setDrawSelectorOnTop(true);


        aGridView.setOnItemClickListener((parent, v, position, id) -> {
            //set visibility gone onclick
            Clicksleft--;
            if (Clicksleft == 0) {
                aGridView.setVisibility(View.GONE);
            }
            if (position == 2) {
                Points++;
            }
            if (position == 5) {
                Points++;
            }
            if (position == 7) {
                Points++;
            }
            if (position == 15) {
                Points++;
            }
            if (position == 17) {
                Points++;
            }
            if (position == 14) {
                Points++;
            }
            if (position == 16) {
                Points++;
            }
            if (position == 18) {
                Points++;
            }
        });
    }
}








