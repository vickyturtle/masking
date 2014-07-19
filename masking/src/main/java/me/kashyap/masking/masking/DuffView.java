package me.kashyap.masking.masking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 5/18/2014.
 */
public class DuffView extends View {

    Paint paint = new Paint();
    Paint oPaint = new Paint();
    Paint anotherPaint = new Paint();
    Bitmap src;
    Bitmap dest;
    Bitmap overlay;
    PorterDuff.Mode mode;

    float matrixVqal [] = {
            1,0,0,0,0,
            0,1,0,0,0,
            0,0,1,0,0,
            0,0,0,1,255,
    };

    public DuffView(Context context) {
        this(context, null);
    }

    public DuffView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DuffView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBitmaps(Bitmap overlay, Bitmap source, Bitmap destination) {
        src = source;
        this.dest = destination;
        this.overlay = overlay;
    }

    public void setMode(PorterDuff.Mode mode1, PorterDuff.Mode mode2) {
        mode = mode1;
        paint.setXfermode(new PorterDuffXfermode(mode1));
//        oPaint.setAlpha(0x99);
        oPaint.setXfermode(new PorterDuffXfermode(mode2));
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(new ColorMatrix(matrixVqal));
//        paint.setColorFilter(filter);
//        paint.setAlpha(0x99);
//        anotherPaint.setAlpha(0x33);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(overlay, 0, 0, anotherPaint);
        canvas.drawBitmap(src,0,0, anotherPaint);
//        canvas.drawARGB(128, 255, 0, 0);
        canvas.drawColor(Color.RED, mode);
        canvas.drawBitmap(dest, 0, 0, oPaint);
//        canvas.drawBitmap(src,0,0, oPaint);
//        canvas.drawColor(0xffff0000, PorterDuff.Mode.SRC_ATOP);
    }
}
