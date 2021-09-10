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

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityGameBinding binding;
    private TouchEventView drawing_pad;

    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private float windowWidth;
    private float windowHeight;

    private Bitmap alteredImage;
    private Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

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

        //change to next word
        binding.btnNext.setOnClickListener(view ->{
            Intent gotoPostGame = new Intent(GameActivity.this, PostGameActivity.class);
            startActivity(gotoPostGame);
            finish();
        });

    }

    private void init(){
        drawing_pad = (TouchEventView) findViewById(R.id.iv_canvas);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() != R.id.btn_next){
            drawing_pad.changeColor(v);
        }
    }
}