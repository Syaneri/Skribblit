package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityPostGameBinding;

public class PostGameActivity extends AppCompatActivity {

    private ActivityPostGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
}