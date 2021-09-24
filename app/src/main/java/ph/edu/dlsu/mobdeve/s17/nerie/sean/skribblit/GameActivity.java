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
import android.graphics.PorterDuff;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Drawing;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;

public class GameActivity extends AppCompatActivity{
    private ActivityGameBinding binding;

    private String name;
    private Lobby lobby;
    private int dp;

    private ImageView iv_canvas;
    private TextView tv_word;
    private TextView tv_timer;
    private int counter;
    private int score;
    private int wordCount;
    private CountDownTimer timer;
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
    private ArrayList<Drawing> drawings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = getIntent().getStringExtra("name");
        dp = getIntent().getIntExtra("dp", 0);
        lobby = (Lobby)getIntent().getSerializableExtra("lobby");

        counter = 90;
        score = 0;
        wordCount = 0;

        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        tv_word = (TextView) findViewById(R.id.tv_word);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        tv_word.setText(lobby.getWords()[wordCount]);

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

        timer = new CountDownTimer(90000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(String.valueOf(counter));
                counter--;
            }
            @Override
            public void onFinish() {
                 saveDrawing();
            }
        }.start();

        init();

        canvas = new Canvas(bitmap);
        paint = new Paint();

        //change to next word
        binding.btnNext.setOnClickListener(view ->{
            saveDrawing();
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

        binding.btnClear.setOnClickListener(view -> {
            canvas.drawColor(Color.WHITE);
            this.drawing_pad.clearCanvas();
            binding.btnEraser.setBackgroundResource(R.drawable.eraser_notselected);
        });

        binding.btnEraser.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("thick");
            this.drawing_pad.changeColor("white");
            binding.btnEraser.setBackgroundResource(R.drawable.eraser_selected);
        });
    }

    private void saveDrawing(){
        if(wordCount < 4) {
            score += counter;

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Drawing newDrawing = new Drawing(lobby.getWords()[0], byteArray);
            drawings.add(newDrawing);
            canvas.drawColor(Color.WHITE);
            this.drawing_pad.clearCanvas();
            wordCount++;
            tv_word.setText(lobby.getWords()[wordCount]);
            timer.cancel();
            counter = 90;
            timer.start();
        } else{
            Intent gotoSaveGame = new Intent(GameActivity.this, SaveGameActivity.class);
            //gotoSaveGame.putExtra("name_key", "Hello");
//            Drawing drawing = new Drawing();

            //gotoSaveGame.putExtra("canvas", bitmap);

            gotoSaveGame.putExtra("drawings", drawings);
//            Bitmap temp = bitmap;
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            temp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//
//            gotoSaveGame.putExtra("canvas", byteArray);
            startActivity(gotoSaveGame);

            finish();
        }
    }

    private void colorSelected(){
        this.drawing_pad.changeStroke(currentWidth);
        this.currentColor = this.drawing_pad.getCurrentColor();
        binding.btnEraser.setBackgroundResource(R.drawable.eraser_notselected);
    }

    private void init(){
        drawing_pad = (TouchEventView) findViewById(R.id.iv_canvas);
        currentWidth = "normal";
        currentColor = "black";
        drawing_pad.init(bitmap);
    }

}