package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    int images[] = {R.drawable.mark_face_angry, R.drawable.mark_face_ase, R.drawable.mark_face_cry,
            R.drawable.mark_face_hehe, R.drawable.mark_face_jito, R.drawable.mark_face_laugh,
            R.drawable.mark_face_smile, R.drawable.mark_face_tere};
    ImageView userImage;
    EditText name;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userImage = (ImageView) findViewById(R.id.iv_display_pic);
        name = (EditText) findViewById(R.id.et_name);

        binding.btnEnter.setOnClickListener(view -> {
            String str = name.getText().toString();
            Bundle bundle = getIntent().getExtras();

            Intent gotoLobby = new Intent(MainActivity.this, LobbyActivity.class);

            gotoLobby.putExtra("message_key", str);
            gotoLobby.putExtra("image_key", R.drawable.mark_face_hehe);
            startActivity(gotoLobby);
        });

        binding.btnSwitchPic.setOnClickListener(view -> {
            userImage.setImageResource(images[i]);
            i++;
            if (i == 8)
                i = 0;
        });
    }

}