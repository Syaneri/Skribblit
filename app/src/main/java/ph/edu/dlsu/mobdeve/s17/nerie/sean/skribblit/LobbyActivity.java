package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.LobbyAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;

public class LobbyActivity extends AppCompatActivity {

    private LobbyAdapter lobbyAdapter;
    private ActivityLobbyBinding binding;
    private boolean lobbySelected;

    ArrayList<Lobby> lobbyList;

    private String name;
    private Lobby lobby;
    private int dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");
        int dp = getIntent().getIntExtra("dp", 0);

        lobbyList = populateLobby();
        lobbySelected = false;
        lobbyAdapter = new LobbyAdapter(getApplicationContext(), lobbyList);

        binding.rvLobbyList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvLobbyList.setAdapter(lobbyAdapter);

        binding.btnLobbyStart.setOnClickListener(view -> {
            for(Lobby lobby: lobbyList){
                if(lobby.isSelected()){
                    lobbySelected = true;
                    Intent gameStart = new Intent(LobbyActivity.this, GameActivity.class);
                    gameStart.putExtra("name", name);
                    gameStart.putExtra("dp", dp);
                    gameStart.putExtra("lobby", lobby);
                    startActivity(gameStart);
                    finish();
                }
            }
            if (!lobbySelected) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No lobby selected",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        binding.btnLobbyExit.setOnClickListener(view -> {
            finish();
        });
    }

    //temporary data
    private ArrayList<Lobby> populateLobby(){
        ArrayList<Lobby> lobbyList = new ArrayList<>();
        String[] words1 = {"dog", "cat", "tiger", "crocodile", "shark"};
        String[] words2 = {"chair", "sofa", "table", "wardrobe", "bed"};

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