package fi.metropolia.capslock.dyslexiascreener.test.reversedletter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import fi.metropolia.capslock.dyslexiascreener.R;


public class ReversedLetterAdapter extends BaseAdapter {


    private final Context mContext;
    public Integer[] imageIDs = {R.drawable.pletter, R.drawable.tletter, R.drawable.reversejletter, R.drawable.qletter, R.drawable.sletter,
        R.drawable.reversehletter, R.drawable.jletter, R.drawable.reversefletter, R.drawable.fletter, R.drawable.zletter,
        R.drawable.eletter, R.drawable.cletter, R.drawable.bletter, R.drawable.hletter, R.drawable.reversecletter,
        R.drawable.reverseeletter, R.drawable.reversejletter, R.drawable.reversefletter, R.drawable.reversehletter, R.drawable.sletter,};


    public ReversedLetterAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imageIDs.length;

    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView mImageView;

        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(16, 16, 16, 16);

        } else {
            mImageView = (ImageView) convertView;
        }
        mImageView.setImageResource(imageIDs[position]);

        return mImageView;
    }
}

