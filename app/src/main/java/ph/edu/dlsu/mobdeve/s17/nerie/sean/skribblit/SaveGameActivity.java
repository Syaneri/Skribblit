package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.SaveGameAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAO;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivitySaveGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Drawing;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public class SaveGameActivity extends AppCompatActivity {
    private ActivitySaveGameBinding binding;
    private ArrayList<Drawing> drawingList;
    private SaveGameAdapter saveGameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent();
        String str = intent.getStringExtra("name_key");

        drawingList = populateDrawings("Robot");

        saveGameAdapter = new SaveGameAdapter(getApplicationContext(), drawingList);

        binding.rvDrawingList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvDrawingList.setAdapter(saveGameAdapter);

        binding.btnViewHighScores.setOnClickListener(view -> {
            Intent endGame = new Intent(SaveGameActivity.this, PostGameActivity.class);
            startActivity(endGame);
            finish();
        });

    }

    private void saveUser(String name, int imageId, int highscore){
        User user = new User();

        UserDAO userDAO = new UserDAOSQLImpl(getApplicationContext());

        user.setId(userDAO.getSize() + 1);
        user.setName(name);
        user.setUserImageId(imageId);
        user.setHighscore(highscore);
        userDAO.addUser(user);
    }

    //temporary data
    private ArrayList<Drawing> populateDrawings(String str){
        ArrayList<Drawing> drawingList = new ArrayList<>();

        drawingList.add(new Drawing(
                1234,
                str
        ));

        return drawingList;
    }
}