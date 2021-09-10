package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.LobbyAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;

public class LobbyActivity extends AppCompatActivity {

    private LobbyAdapter lobbyAdapter;
    private ActivityLobbyBinding binding;

    ArrayList<Lobby> lobbyList;

    TextView receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        receiver_msg = (TextView) findViewById(R.id.tv_lobbyname);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        int id = intent.getIntExtra("image_key",R.drawable.mark_face_hehe);

        lobbyList = populateLobby(str, id);
        lobbyAdapter = new LobbyAdapter(getApplicationContext(), lobbyList);

        binding.rvLobbyList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvLobbyList.setAdapter(lobbyAdapter);

        binding.btnLobbyStart.setOnClickListener(view -> {
            Intent gameStart = new Intent(LobbyActivity.this, GameActivity.class);

            gameStart.putExtra("name_key", str);
            startActivity(gameStart);
            finish();
        });

        binding.btnLobbyExit.setOnClickListener(view -> {
            finish();
        });
    }

    //temporary data
    private ArrayList<Lobby> populateLobby(String str, int id){
        ArrayList<Lobby> lobbyList = new ArrayList<>();
        String[] words1 = {"dog", "cat", "lion", "tiger", "crocodile", "shark", "whale", "snake", "bear", "wolf"};
        //String[] words2 = {"chair", "sofa", "table", "wardrobe", "bed", "coffee table", "recliner", "ottoman", "cupboard", "armchair", "cabinet"};

        lobbyList.add(new Lobby(
           id,
                str,
                words1
        ));
//        lobbyList.add(new Lobby(
//                2434,
//                "Furniture",
//                words2
//        ));
//
//        lobbyList.add(new Lobby(
//                1234,
//                "Animals",
//                words1
//        ));
        return lobbyList;
    }
}