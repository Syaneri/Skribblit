package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnEnter.setOnClickListener(this);
        binding.btnCreate.setOnClickListener(this);
        binding.btnSwitchPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String message = "";
        switch (v.getId()){
            case R.id.btn_enter:
                if(binding.etCode.getText().toString().equals("1234")) {
                    message = "Lobby entered";
                    Intent gotoLobby = new Intent(MainActivity.this, Lobby.class);
                    startActivity(gotoLobby);
                } else {
                    message = "Lobby not found";
                }
                break;
            case R.id.btn_create:
                message = "Lobby Created";
                break;
            case R.id.btn_switch_pic:
                message = "Not yet implemented :(";
                break;
        }

        Toast.makeText(getApplicationContext(),
            message,
                Toast.LENGTH_SHORT).show();
    }
}