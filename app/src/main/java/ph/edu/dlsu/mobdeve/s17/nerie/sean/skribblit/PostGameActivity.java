package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.LobbyAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.UserAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityPostGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public class PostGameActivity extends AppCompatActivity {

    private ActivityPostGameBinding binding;
    private ArrayList<User> userArrayList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userArrayList = populateUser();
        userAdapter = new UserAdapter(getApplicationContext(), userArrayList);

        binding.rvPlayerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvPlayerList.setAdapter(userAdapter);

        binding.btnLobbyStart.setOnClickListener(view -> {
            Intent gameStart = new Intent(PostGameActivity.this, MainActivity.class);
            startActivity(gameStart);
            finish();
        });

        binding.btnLobbyExit.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });


    }

    private ArrayList<User> populateUser(){
        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User(
                R.drawable.mark_face_laugh,
                "Sean",
                1,
                1234

        ));

        userList.add(new User(
                R.drawable.mark_face_cry,
                "Robi",
                2,
                1231

        ));

        return userList;
    }
}