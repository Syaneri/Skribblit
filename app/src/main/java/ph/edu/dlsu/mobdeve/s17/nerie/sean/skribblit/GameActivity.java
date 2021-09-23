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

public class GameActivity extends AppCompatActivity{
    private ActivityGameBinding binding;

    private ImageView iv_canvas;
    private TextView tv_word;
    private Button btn_palette;

    private TouchEventView drawing_pad;


    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private float windowWidth;
    private float windowHeight;

    private Bitmap alteredImage;
    private Matrix matrix;

    private ArrayList<Pair<Path, Paint>> paths = new ArrayList<Pair<Path, Paint>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        tv_word = (TextView) findViewById(R.id.tv_word);

        String str = tv_word.getText().toString();

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
            Intent gotoSaveGame = new Intent(GameActivity.this, SaveGameActivity.class);
            //gotoSaveGame.putExtra("name_key", "Hello");
            startActivity(gotoSaveGame);

            finish();
        });

        //change colors
        binding.btnBlackcolor.setOnClickListener(view -> {
            this.drawing_pad.changeColor("black");
        });

        binding.btnBrowncolor.setOnClickListener(view -> {
            this.drawing_pad.changeColor("brown");
        });

        binding.btnBluecolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("blue");
        });

        binding.btnGraycolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("gray");
        });

        binding.btnGreencolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("green");
        });

        binding.btnBlackcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("black");
        });

        binding.btnOrangecolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("orange");
        });

        binding.btnPinkcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("pink");
        });

        binding.btnRedcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("red");
        });

        binding.btnWhitecolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("white");
        });

        binding.btnYellowcolor.setOnClickListener(view ->{
            this.drawing_pad.changeColor("yellow");
        });

        //change stroke sizes

        binding.btnThickbrush.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("thick");
        });

        binding.btnThinbrush.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("thin");
        });

        binding.btnNormalbrush.setOnClickListener(view -> {
            this.drawing_pad.changeStroke("normal");
        });

    }

    private void init(){
        drawing_pad = (TouchEventView) findViewById(R.id.iv_canvas);
        drawing_pad.init();
    }

}