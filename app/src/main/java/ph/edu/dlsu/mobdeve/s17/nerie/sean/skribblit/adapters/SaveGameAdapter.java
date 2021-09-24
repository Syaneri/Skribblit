package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.GameActivity;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.LobbyActivity;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.MainActivity;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.R;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.SaveGameActivity;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.ViewImageActivity;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityPostGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Drawing;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SaveGameAdapter extends RecyclerView.Adapter<SaveGameAdapter.SaveGameViewHolder> {

    private ArrayList<Drawing> drawingsArrayList;
    private Context context;

    public SaveGameAdapter(Context context, ArrayList<Drawing> drawingsArrayList){
        this.drawingsArrayList = drawingsArrayList;
        this.context = context;

    }

    @Override
    public int getItemCount() {
        return drawingsArrayList.size();
    }

    public SaveGameAdapter.SaveGameViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drawings, parent, false);
        SaveGameAdapter.SaveGameViewHolder saveGameViewHolder = new SaveGameAdapter.SaveGameViewHolder(view);
        return saveGameViewHolder;
    }

    @Override
    public void onBindViewHolder(SaveGameAdapter.SaveGameViewHolder holder, int position) {
        holder.tv_object_drawn.setText(drawingsArrayList.get(position).getName() + "");

        holder.iv_drawing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(holder.iv_drawing.getWidth(), holder.iv_drawing.getHeight(), Bitmap.Config.RGB_565);
                bitmap.setHasAlpha(true);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent viewImageIntent = new Intent(context, ViewImageActivity.class);

                viewImageIntent.putExtra("img", byteArray);
                viewImageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(viewImageIntent);
            }
        });

    }

    protected class SaveGameViewHolder extends RecyclerView.ViewHolder {
        TextView tv_object_drawn;
        ImageView iv_drawing;

        public SaveGameViewHolder(View v){
            super(v);

            tv_object_drawn = v.findViewById(R.id.tv_object_drawn);
            iv_drawing = v.findViewById(R.id.iv_drawing);

            iv_drawing.setOnClickListener(view -> {
                Toast.makeText(context, "ITEM PRESSED", Toast.LENGTH_SHORT).show();
            });

        }
    }
}
