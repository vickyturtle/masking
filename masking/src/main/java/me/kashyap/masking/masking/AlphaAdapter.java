package me.kashyap.masking.masking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created on 7/3/2014.
 */
public class AlphaAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    Bitmap src;
    Bitmap dest;
    Bitmap overlay;

    public AlphaAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        src = BitmapFactory.decodeResource(context.getResources(), R.drawable.sample);
        dest = BitmapFactory.decodeResource(context.getResources(), R.drawable.frame_shapes_a);
        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall_pattern);
        overlay = temp.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public int getCount() {
        return 40;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate( R.layout.opactity_row, null, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.opactiy_imageView);
            holder.maskView = (MaskView) convertView.findViewById(R.id.maskview);
            holder.maskView.setBitmaps(src, dest, overlay);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        holder.maskView.setTransParency( position * 255  / 40);
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        MaskView maskView;
    }

}
