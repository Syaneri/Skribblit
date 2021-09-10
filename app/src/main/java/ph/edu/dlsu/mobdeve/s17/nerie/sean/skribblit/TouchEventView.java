package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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
        paint.setStrokeWidth(6f);
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

    public void changeColor(View v){
        switch(v.getId()){
            case R.id.btn_blackcolor:
                paint.setColor(Color.BLACK);
                break;
            case R.id.btn_graycolor:
                paint.setColor(Color.DKGRAY);
                break;
            case R.id.btn_redcolor:
                paint.setColor(Color.RED);
                break;
            case R.id.btn_yellowcolor:
                paint.setColor(Color.YELLOW);
                break;
            case R.id.btn_bluecolor:
                paint.setColor(Color.BLUE);
                break;
            case R.id.btn_greencolor:
                paint.setColor(Color.GREEN);
                break;
            case R.id.btn_pinkcolor:

                break;
            case R.id.btn_orangecolor:

                break;
            case R.id.btn_browncolor:

                break;
            case R.id.btn_whitecolor:
                paint.setColor(Color.WHITE);
                break;
        }
    }
}

