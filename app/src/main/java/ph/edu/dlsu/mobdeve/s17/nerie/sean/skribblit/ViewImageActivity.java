package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityViewImageBinding;

public class ViewImageActivity extends AppCompatActivity {

    private ActivityViewImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        byte[] byteArray = getIntent().getByteArrayExtra("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        binding.ivDisplayDrawing.setImageBitmap(bmp);

        binding.btnSaveImage.setOnClickListener(view ->{
            Uri imageFileUri = getContentResolver().
                    insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new ContentValues());

            try{
                OutputStream imageFileOS = getContentResolver()
                        .openOutputStream(imageFileUri);
                if(bmp != null){
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, imageFileOS);
                }else{
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, imageFileOS);
                }
                Toast.makeText(this, "Drawing Saved", Toast.LENGTH_LONG).show();

            }catch(FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        binding.btnBack.setOnClickListener(view -> {
            Intent backToSaveGame = new Intent(ViewImageActivity.this, SaveGameActivity.class);
            startActivity(backToSaveGame);
        });


    }

}
