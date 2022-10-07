package fi.metropolia.capslock.dyslexiascreener.test.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.R;

/**
 * Adapter for letter and word selection test.
 *
 * @author Joel Tikkanen
 */

public class SelectionAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> items;

    public SelectionAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_selection, parent, false);
            ((TextView) view.getRootView()).setText(getItem(position));
        } else {
            view = convertView;
        }
        return view;
    }
}
