package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.R;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Drawing;

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
    }

    protected class SaveGameViewHolder extends RecyclerView.ViewHolder {
        TextView tv_object_drawn;

        public SaveGameViewHolder(View view){
            super(view);
            tv_object_drawn = view.findViewById(R.id.tv_object_drawn);
        }
    }
}
