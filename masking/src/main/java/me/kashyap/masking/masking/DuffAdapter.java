package me.kashyap.masking.masking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created on 5/18/2014.
 */
public class DuffAdapter extends BaseAdapter {

    Context context;
    boolean isShapeMode;
    LayoutInflater inflater;
    Bitmap src;
    Bitmap dest;
    Bitmap overlay;

    public DuffAdapter(Context context, boolean isShapeMode) {
        this.context = context;
        this.isShapeMode = isShapeMode;
        inflater = LayoutInflater.from(context);
        src = BitmapFactory.decodeResource(context.getResources(), R.drawable.sample);
        dest = BitmapFactory.decodeResource(context.getResources(), R.drawable.frame_shapes_a);
        overlay = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall_pattern);
    }

    @Override
    public int getCount() {
        return 371;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.porter_duff_row, parent, false);
            holder = new ViewHolder();
            holder.duffView = (DuffView) convertView.findViewById(R.id.duff_view);
            holder.textView = (TextView) convertView.findViewById(R.id.duff_title);
            holder.duffView.setBitmaps(overlay, src, dest);
            holder.duffView.setShapeMode(isShapeMode);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.duffView.setMode(getMode(position / 19), getMode(position % 19));
        String text = getMode(position / 19).name() + ", " + getMode(position % 19).name();
        holder.textView.setText(text);
        return convertView;
    }

    public static PorterDuff.Mode getMode(int position) {
        PorterDuff.Mode mode;
        switch (position) {
            case 0:
                return PorterDuff.Mode.ADD;
            case 1:
                return PorterDuff.Mode.CLEAR;
            case 2:
                return PorterDuff.Mode.DARKEN;
            case 3:
                return PorterDuff.Mode.DST;
            case 4:
                return PorterDuff.Mode.DST_ATOP;
            case 5:
                return PorterDuff.Mode.DST_OUT;
            case 6:
                return PorterDuff.Mode.DST_IN;
            case 7:
                return PorterDuff.Mode.DST_OVER;
            case 8:
                return PorterDuff.Mode.LIGHTEN;
            case 9:
                return PorterDuff.Mode.MULTIPLY;
            case 10:
                return PorterDuff.Mode.OVERLAY;
            case 11:
                return PorterDuff.Mode.SRC;
            case 12:
                return PorterDuff.Mode.SRC_ATOP;
            case 13:
                return PorterDuff.Mode.SRC_IN;
            case 14:
                return PorterDuff.Mode.SRC_OUT;
            case 15:
                return PorterDuff.Mode.SRC_OVER;
            case 16:
                return PorterDuff.Mode.SCREEN;
            case 17:
                return PorterDuff.Mode.XOR;
            case 18:
                return PorterDuff.Mode.OVERLAY;

        }
        return PorterDuff.Mode.OVERLAY;
    }

    static class ViewHolder {
        DuffView duffView;
        TextView textView;
    }
}
