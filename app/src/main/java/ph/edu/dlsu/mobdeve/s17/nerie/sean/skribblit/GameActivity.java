package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Drawing;

public class GameActivity extends AppCompatActivity{
    private ActivityGameBinding binding;

    private ImageView iv_canvas;
    private TextView tv_word;
    private TextView tv_timer;
    private int counter;
    private int score;
    private Button btn_palette;

    private TouchEventView drawing_pad;


    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private float windowWidth;
    private float windowHeight;

    private Bitmap alteredImage;
    private Matrix matrix;

    private String currentColor;
    private String currentWidth;

    private ArrayList<Pair<Path, Paint>> paths = new ArrayList<Pair<Path, Paint>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        tv_word = (TextView) findViewById(R.id.tv_word);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        counter = 90;
        score = 0;

        String str = tv_word.getText().toString();

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

        new CountDownTimer(90000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(String.valueOf(counter));
                counter--;
            }
            @Override
            public void onFinish() {
                score += counter;
            }
        }.start();

        init();

        canvas = new Canvas(bitmap);
        paint = new Paint();

        //change to next word
        binding.btnNext.setOnClickListener(view ->{
            Intent gotoSaveGame = new Intent(GameActivity.this, SaveGameActivity.class);
            //gotoSaveGame.putExtra("name_key", "Hello");
//            Drawing drawing = new Drawing();

            gotoSaveGame.putExtra("canvas", bitmap);
            startActivity(gotoSaveGame);

            finish();
        });

        //change colors
        binding.btnBlackcolor.setOnClickListener(view -> {
            this.drawing_pad.changeColor("black");
            colorSelected();
        });

        binding.btnBrowncolor.setOnClickListener(view -> {
            this.drawing_pad.changeColor("brown");
            colorSelected();
        });

        binding.btnBluecolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("blue");
            colorSelected();
        });

        binding.btnGraycolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("gray");
            colorSelected();
        });

        binding.btnGreencolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("green");
            colorSelected();
        });

        binding.btnBlackcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("black");
            colorSelected();
        });

        binding.btnOrangecolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("orange");
            colorSelected();
        });

        binding.btnPinkcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("pink");
            colorSelected();
        });

        binding.btnRedcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("red");
            colorSelected();
        });

        binding.btnWhitecolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("white");
            colorSelected();
        });

        binding.btnYellowcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("yellow");
            colorSelected();
        });

        //change stroke sizes

        binding.btnThickbrush.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("thick");
            currentWidth = this.drawing_pad.getCurrentWidth();
        });

        binding.btnThinbrush.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("thin");
            currentWidth = this.drawing_pad.getCurrentWidth();
        });

        binding.btnNormalbrush.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("normal");
            currentWidth = this.drawing_pad.getCurrentWidth();
        });

        binding.btnBrush.setOnClickListener(view -> {
            canvas.drawColor(Color.WHITE);
            this.drawing_pad.changeColor("white");
            this.drawing_pad.changeStroke("thick");
            this.drawing_pad.clearCanvas();
            binding.btnBrush.setBackgroundResource(R.drawable.brush_selected);
            binding.btnEraser.setBackgroundResource(R.drawable.eraser_notselected);
        });

        binding.btnEraser.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("thick");
            this.drawing_pad.changeColor("white");
            binding.btnEraser.setBackgroundResource(R.drawable.eraser_selected);
            binding.btnBrush.setBackgroundResource(R.drawable.brush_notselected);
        });

    }

    private void colorSelected(){
        this.drawing_pad.changeStroke(currentWidth);
        this.currentColor = this.drawing_pad.getCurrentColor();
        binding.btnEraser.setBackgroundResource(R.drawable.eraser_notselected);
        binding.btnBrush.setBackgroundResource(R.drawable.brush_notselected);
    }

    private void init(){
        drawing_pad = (TouchEventView) findViewById(R.id.iv_canvas);
        currentWidth = "normal";
        currentColor = "black";
        drawing_pad.init(bitmap);
    }

}