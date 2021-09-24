package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class TouchEventView extends androidx.appcompat.widget.AppCompatImageView {
    private Paint paint;
    private Path path;
    private float mX, mY;
    private ArrayList<Stroke> paths = new ArrayList<>();
    private String currentColor;
    private String currentWidth;
    private Canvas nCanvas;
    private Bitmap nBitmap;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    Context context;

    GestureDetector gestureDetector;

    public class Stroke{
        public String color;
        public String width;
        public Path path;
        public Stroke(String color, String width, Path path){
            this.color = color;
            this.width = width;
            this.path = path;
        }
    }

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.context = context;
        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLACK);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void init(Bitmap bitmap){
        nBitmap = bitmap;
        nCanvas = new Canvas(nBitmap);
        currentColor = "black";
        currentWidth = "normal";
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();

            path.reset();
            Toast.makeText(context, "Double Tap >> Tapped at: (" + x + "," + y + ")", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
//        nCanvas.drawColor(Color.WHITE);

        for (Stroke fp : paths) {
            changeColor(fp.color);
            changeStroke(fp.width);
            nCanvas.drawPath(fp.path, paint);
        }
        canvas.drawBitmap(nBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y){
        path = new Path();
        Stroke fp = new Stroke(currentColor, currentWidth, path);
        paths.add(fp);

        path.reset();
        path.moveTo(x, y);

        mX = x;
        mY = y;
    }

//    private void touchUp() {
//        path.lineTo(mX, mY);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(eventX, eventY);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
//                touchUp();
                invalidate();
                break;
            default:
                return false;
        }

        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    public void changeColor(String str) {
        switch (str) {
            case "black":
                currentColor = "black";
                paint.setColor(context.getColor(R.color.black));
                break;
            case "gray":
                currentColor = "gray";
                paint.setColor(context.getColor(R.color.gray));
                break;
            case "red":
                currentColor = "red";
                paint.setColor(context.getColor(R.color.red));
                break;
            case "yellow":
                currentColor = "yellow";
                paint.setColor(context.getColor(R.color.yellow));
                break;
            case "blue":
                currentColor = "blue";
                paint.setColor(context.getColor(R.color.blue));
                break;
            case "green":
                currentColor = "green";
                paint.setColor(context.getColor(R.color.green));
                break;
            case "pink":
                currentColor = "pink";
                paint.setColor(context.getColor(R.color.pink));
                break;
            case "orange":
                currentColor = "orange";
                paint.setColor(context.getColor(R.color.orange));
                break;
            case "brown":
                currentColor = "brown";
                paint.setColor(context.getColor(R.color.brown));
                break;
            case "white":
                currentColor = "white";
                paint.setColor(context.getColor(R.color.white));
                break;
        }
    }

    public void changeStroke(String str){
        switch(str) {
            case "normal":
                currentWidth = "normal";
                paint.setStrokeWidth(20);
                break;
            case "thin":
                currentWidth = "thin";
                paint.setStrokeWidth(10);
                break;
            case "thick":
                currentWidth = "thick";
                paint.setStrokeWidth(30);
        }
    }

    public void clearCanvas(){ paths.clear(); }

    public Bitmap save() { return nBitmap; }

    public String getCurrentColor() { return currentColor; }

    public String getCurrentWidth() { return currentWidth; }
}

