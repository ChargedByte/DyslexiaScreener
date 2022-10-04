package fi.metropolia.capslock.dyslexiascreener;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private Resources resources;
    private ArrayList<String> letters;
    private int correct = 0;
    public static final int all_correct = 14;


    public CustomAdapter(Context context, ArrayList<String> letters) {
        this.letters = letters;
        this.context = context;
        this.resources = context.getResources();
    }

    @Override
    public int getCount() {
        return letters.size();
    }

    @Override
    public Object getItem(int position) {
        return letters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getCorrect() {
        return correct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(this.context);

            gridView = inflater.inflate(R.layout.singleitem_layout, parent, false);

            Button letterButton = (Button) gridView.findViewById(R.id.grid_item_button);
            letterButton.setText(letters.get(position));

            letterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setEnabled(false);
                    v.setBackgroundColor(resources.getColor(R.color.black, context.getTheme()));

                    Log.d("letterClick", String.valueOf(letterButton.getText()));

                    if (letterButton.getText().equals("b")){
                        correct++;
                        Log.d("letterClick", Integer.toString(correct));
                    }
                    if (correct == all_correct) {
                        Log.d("letterClick", "kaikki oikein");

                    }
                }
            });
        }
        else {
            gridView = (View) convertView;;
        }
        return gridView;
    }

}

