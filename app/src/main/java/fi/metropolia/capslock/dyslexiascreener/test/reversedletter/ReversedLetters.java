package fi.metropolia.capslock.dyslexiascreener.test.reversedletter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fi.metropolia.capslock.dyslexiascreener.R;

public class ReversedLetters extends AppCompatActivity {


    public GridView aGridView;
    FloatingActionButton nextButton;
    private int Points = 0;
    private int Clicksleft = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reversed_letters);
        aGridView = (GridView) findViewById(R.id.grid_view);
        aGridView.setAdapter(new ReversedLetterAdapter(this));
        aGridView.setDrawSelectorOnTop(true);
        nextButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        aGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
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
                Toast.makeText(getBaseContext(), "Pisteet" + (Points) + " Yritykset" + (Clicksleft), Toast.LENGTH_SHORT).show();
                //delete toast


                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //start next activity

                    }
                });
            }
        });
    }
}








