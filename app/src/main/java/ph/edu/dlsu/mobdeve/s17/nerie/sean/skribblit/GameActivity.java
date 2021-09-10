package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {
    private ActivityGameBinding binding;
    private ImageView iv_canvas;
    private Button btn_palette;

    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private float windowWidth;
    private float windowHeight;


    private float downX = 0.0f;
    private float downY = 0.0f;
    private float upX = 0.0f;
    private float upY = 0.0f;

    private Bitmap alteredImage;
    private Matrix matrix;

    private int penColor = Color.BLUE;

    TextView tv_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String str = intent.getStringExtra("name_key");

        tv_player = (TextView) findViewById(R.id.tv_player_turn);

        tv_player.setText(str + "'s Turn");
        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);

        DisplayMetrics currentDisplay = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(currentDisplay);
        windowHeight = currentDisplay.heightPixels * 2;
        windowWidth = currentDisplay.widthPixels * 2;

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        final Display display2 = windowManager.getDefaultDisplay();
        Point outPoint = new Point();

        display2.getSize(outPoint);

        int width = 0;
        int height = 0;

        if (outPoint.y > outPoint.x) {
            height = outPoint.y;
            width = outPoint.x;
        } else {
            height = outPoint.x;
            width = outPoint.y;
        }

        bitmap = Bitmap.createBitmap(width,
                (height),
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        paint = new Paint();

        paint.setColor(penColor);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        iv_canvas.setImageBitmap(bitmap);
        iv_canvas.setOnTouchListener(this);

        //change to next word
        binding.btnNext.setOnClickListener(view ->{
            Intent gotoPostGame = new Intent(GameActivity.this, PostGameActivity.class);
            startActivity(gotoPostGame);
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                downX = motionEvent.getX();
                downY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                upX = motionEvent.getX();
                upY = motionEvent.getY();
                canvas.drawLine(downX, downY, upX, upY, paint);
                iv_canvas.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                upX = motionEvent.getX();
                upY = motionEvent.getY();
                canvas.drawLine(downX, downY, upX, upY, paint);
                iv_canvas.invalidate();
                downX = upX;
                downY = upY;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }

        return true;
    }
}