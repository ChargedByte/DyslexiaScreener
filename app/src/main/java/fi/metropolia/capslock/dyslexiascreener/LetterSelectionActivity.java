package fi.metropolia.capslock.dyslexiascreener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class LetterSelectionActivity extends AppCompatActivity {


    GridView letterGrid;
    ArrayList<String> letters = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_selection);
        letterGrid = (GridView) findViewById(R.id.letterGrid);

        //items

        String testLetters = "bdbdbpdbbqqbpdpbddbbdpqbdqbdppdbpbdpbpp";

        for (int i=0;i<testLetters.length();i++){
            if (testLetters.charAt(i) == 'b'){
                letters.add("b");
            } else {
                letters.add(Character.toString(testLetters.charAt(i)));
            }
        }

        letterGrid.setAdapter(new CustomAdapter(this, letters));

    }
}
