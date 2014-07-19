package me.kashyap.masking.masking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lenovo on 5/18/2014.
 */
public class MaskView extends View {

    Paint srcPaint;
    Paint destPaint;
    Paint overlayPaint;

    Bitmap mask;
    Bitmap overlay;
    Bitmap source;
    Bitmap temp;

    Canvas c1;

    float matrixVal[] = {
            1,0,0,0,0,
            0,1,0,0,0,
            0,0,1,0,0,
            0,0,0,1,0,
    };

    RectF rectF = new RectF();
    public MaskView(Context context) {
        this(context, null);
    }

    public MaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        srcPaint = new Paint();
        srcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        srcPaint.setColor(Color.BLACK);
        srcPaint.setAntiAlias(true);
        srcPaint.setStyle(Paint.Style.FILL);

        destPaint = new Paint();
        destPaint.setColor(Color.TRANSPARENT);
        destPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        overlayPaint = new Paint();
        overlayPaint.setAlpha(0x33);
        this.overlay = Bitmap.createBitmap(640,300, Bitmap.Config.ARGB_8888);
    }

    public void setBitmaps(Bitmap source, Bitmap mask, Bitmap overlay) {
        this.source = source;
        this.mask = mask;
temp = overlay;
//        this.overlay = overlay;
        c1 = new Canvas(this.overlay);
        Log.d("MaskView", "mask bitmap set");
    }

    public void setTransParency(int val) {
//        overlayPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        overlayPaint.setAlpha(val);
        rectF.set(20 , 20 , getWidth() - 20 , getHeight() - 20);
        matrixVal[19] = 255 - val;
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(new ColorMatrix(matrixVal));
//        srcPaint.setColorFilter(filter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MaskView", "on draw");
        if (null != mask) {
            Log.d("MaskView", "mask drawn");
//            canvas.drawBitmap(source, 0,0, null);
//            c1.drawColor(Color.RED);
            c1.drawBitmap(temp, 0,0,null);
//            c1.drawColor(Color.RED);
            c1.drawBitmap(mask, 0,0, srcPaint);
//            c1.drawRoundRect(rectF,20 ,20 , destPaint);
            canvas.drawBitmap(overlay, 0, 0, overlayPaint);
//            canvas.drawBitmap(mask, 0 , 0, srcPaint);
//            canvas.drawRoundRect(rectF, 20, 20, srcPaint);
//            canvas.drawBitmap(source, 0,0, destPaint);
        }
    }
}
