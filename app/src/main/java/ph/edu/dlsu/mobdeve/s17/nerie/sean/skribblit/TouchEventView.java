package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import android.content.Context;
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
    private Paint paint = new Paint();
    private Path path = new Path();
    Context context;

    GestureDetector gestureDetector;

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.context = context;

        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLACK);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
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
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return false;
        }

        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    public void changeColor(String str){
        switch(str){
            case "black":
                paint.setColor(context.getColor(R.color.black));
                break;
            case "gray":
                paint.setColor(context.getColor(R.color.gray));
                break;
            case "red":
                paint.setColor(context.getColor(R.color.red));
                break;
            case "yellow":
                paint.setColor(context.getColor(R.color.yellow));
                break;
            case "blue":
                invalidate();
                paint.setColor(context.getColor(R.color.blue));
                break;
            case "green":
                paint.setColor(context.getColor(R.color.green));
                break;
            case "pink":
                paint.setColor(context.getColor(R.color.pink));
                break;
            case "orange":
                paint.setColor(context.getColor(R.color.orange));
                break;
            case "brown":
                paint.setColor(context.getColor(R.color.brown));
                break;
            case "white":
                paint.setColor(context.getColor(R.color.white));
                break;
        }
    }

    public void changeStroke(String str){
        switch(str){
            case "normal":
                paint.setStrokeWidth(10f);
                break;
            case "thin":
                paint.setStrokeWidth(6f);
                break;
            case "thick":
                paint.setStrokeWidth(15f);
    }
}

