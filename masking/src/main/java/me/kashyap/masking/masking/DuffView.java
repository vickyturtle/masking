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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 5/18/2014.
 */
public class DuffView extends View {

    Paint paint = new Paint();
    Paint oPaint = new Paint();

    Bitmap src;
    Bitmap dest;
    Bitmap overlay;
    PorterDuff.Mode mode;
    RectF shapeRect = new RectF();

    boolean isShapeMode;

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

    public void setShapeMode(boolean isShapeMode) {
        this.isShapeMode = isShapeMode;
        if(isShapeMode) {
            oPaint.setColor(Color.TRANSPARENT);
            oPaint.setStyle(Paint.Style.FILL);
            oPaint.setAntiAlias(true);
        }
    }

    public void setMode(PorterDuff.Mode mode1, PorterDuff.Mode mode2) {
        mode = mode1;
        oPaint.setXfermode(new PorterDuffXfermode(mode1));
        paint.setXfermode(new PorterDuffXfermode(mode2));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        shapeRect.set(50, 50, w - 50, h - 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null != src) {
            canvas.drawBitmap(src, 0, 0, null);
            if (isShapeMode) {
                canvas.drawRoundRect(shapeRect, 40, 40, oPaint);
            } else {
                canvas.drawBitmap(dest, 0, 0, oPaint);
            }
            canvas.drawBitmap(overlay, 0, 0, paint);
        }
    }
}
