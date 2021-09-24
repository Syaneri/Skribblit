package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    int images[] = {R.drawable.mark_face_angry, R.drawable.mark_face_ase, R.drawable.mark_face_cry,
            R.drawable.mark_face_hehe, R.drawable.mark_face_jito, R.drawable.mark_face_laugh,
            R.drawable.mark_face_smile, R.drawable.mark_face_tere};
    ImageView userImage;
    int i = 0, dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userImage = (ImageView) findViewById(R.id.iv_display_pic);

        binding.btnEnter.setOnClickListener(view -> {
            if(!binding.etName.getText().toString().equals("")){
                Intent gotoLobby = new Intent(MainActivity.this, LobbyActivity.class);

                gotoLobby.putExtra("name", binding.etName.getText().toString());
                gotoLobby.putExtra("dp", this.dp);

                startActivity(gotoLobby);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No name entered.",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        binding.btnSwitchPic.setOnClickListener(view -> {
            userImage.setImageResource(images[i]);
            this.dp = images[i];

            i++;
            if (i == 8)
                i = 0;
        });
    }

}