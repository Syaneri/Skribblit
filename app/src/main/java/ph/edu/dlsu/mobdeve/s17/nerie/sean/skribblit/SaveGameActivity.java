package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.SaveGameAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAO;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivitySaveGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Drawing;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

import static android.graphics.BitmapFactory.decodeByteArray;

public class SaveGameActivity extends AppCompatActivity {
    private ActivitySaveGameBinding binding;
    private ArrayList<Drawing> drawingList;
    private SaveGameAdapter saveGameAdapter;

    private String name;
    private String lobby;
    private int dp;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get data from gameactivity
        Intent intent = getIntent();

        this.name = getIntent().getStringExtra("name");
        this.dp = getIntent().getIntExtra("dp", 0);
        this.lobby = getIntent().getStringExtra("lobby");
        this.score = getIntent().getIntExtra("score", 0);

        ArrayList<Drawing> drawings = (ArrayList<Drawing>)intent.getSerializableExtra("drawings");

        //send data to savegameadapter
        saveGameAdapter = new SaveGameAdapter(getApplicationContext(), drawings);

        binding.rvDrawingList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvDrawingList.setAdapter(saveGameAdapter);
        
        binding.userScore.setText("Score: " + score);

        binding.btnViewHighScores.setOnClickListener(view -> {
            Intent endGame = new Intent(SaveGameActivity.this, PostGameActivity.class);

            endGame.putExtra("lobby", this.lobby);
            endGame.putExtra("score", this.score);
            endGame.putExtra("name", this.name);
            endGame.putExtra("dp", this.dp);

            startActivity(endGame);
            finish();
        });

    }


//    //temporary data
//    private ArrayList<Drawing> populateDrawings(String str, Bitmap bmp){
//        ArrayList<Drawing> drawingList = new ArrayList<>();
//
//        drawingList.add(new Drawing(
//                str,
//                bmp
//        ));
//
//        return drawingList;
//    }
}