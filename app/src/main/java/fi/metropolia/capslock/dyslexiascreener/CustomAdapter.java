package fi.metropolia.capslock.dyslexiascreener;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 *
 * Adapter for letter and word selection test.
 * @author Joel Tikkanen
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private Resources resources;
    private List<String> items;
    private String correct_answer;
    private int correct = 0;
    private int maxCorrect;


    public CustomAdapter(Context context, List<String> letters, String correct_answer, int maxCorrect) {
        this.items = letters;
        this.context = context;
        this.resources = context.getResources();
        this.correct_answer = correct_answer;
        this.maxCorrect = maxCorrect;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getCorrect() {
        return correct;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(this.context);

            gridView = inflater.inflate(R.layout.singleitem_layout, parent, false);

            Button letterButton = (Button) gridView.findViewById(R.id.grid_item_button);
            letterButton.setText(items.get(position));

            letterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setEnabled(false);
                    v.setBackgroundColor(resources.getColor(R.color.black, context.getTheme()));

                    Log.d("letterClick", String.valueOf(letterButton.getText()));

                    if (letterButton.getText().equals(correct_answer)){
                        correct++;
                        Log.d("letterClick", "oikein");
                    }
                    if (correct == maxCorrect) {
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

