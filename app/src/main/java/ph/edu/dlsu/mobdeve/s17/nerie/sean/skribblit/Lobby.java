package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityMainBinding;

public class Lobby extends AppCompatActivity implements View.OnClickListener {

    private ActivityLobbyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnLobbyStart.setOnClickListener(this);
        binding.btnLobbyExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.btn_lobby_start):
                Intent gameStart = new Intent(Lobby.this, Game.class);
                startActivity(gameStart);
                finish();
                break;
            case(R.id.btn_lobby_exit):
                finish();
                break;
        }
    }
}