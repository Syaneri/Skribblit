package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.LobbyAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;

public class LobbyActivity extends AppCompatActivity {

    private LobbyAdapter lobbyAdapter;
    private ActivityLobbyBinding binding;

    ArrayList<Lobby> lobbyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lobbyList = populateLobby();
        lobbyAdapter = new LobbyAdapter(getApplicationContext(), lobbyList);

        binding.rvLobbyList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvLobbyList.setAdapter(lobbyAdapter);

        binding.btnLobbyStart.setOnClickListener(view -> {
            Intent gameStart = new Intent(LobbyActivity.this, GameActivity.class);
            startActivity(gameStart);
            finish();
        });

        binding.btnLobbyExit.setOnClickListener(view -> {
            finish();
        });
    }

    //temporary data
    private ArrayList<Lobby> populateLobby(){
        ArrayList<Lobby> lobbyList = new ArrayList<>();
        String[] words1 = {"dog", "cat", "lion", "tiger", "crocodile", "shark", "whale", "snake", "bear", "wolf"};
        String[] words2 = {"chair", "sofa", "table", "wardrobe", "bed", "coffee table", "recliner", "ottoman", "cupboard", "armchair", "cabinet"};

        lobbyList.add(new Lobby(
           1234,
                "Animals",
                words1
        ));
        lobbyList.add(new Lobby(
                2434,
                "Furniture",
                words2
        ));

        return lobbyList;
    }
}