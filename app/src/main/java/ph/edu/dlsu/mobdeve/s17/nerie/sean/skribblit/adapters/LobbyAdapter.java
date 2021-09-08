package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.R;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;

public class LobbyAdapter extends RecyclerView.Adapter<LobbyAdapter.LobbyViewHolder> {

    private ArrayList<Lobby> lobbyArrayList;
    private Context context;

    public LobbyAdapter(Context context, ArrayList<Lobby> lobbyArrayList){
        this.lobbyArrayList = lobbyArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return lobbyArrayList.size();
    }

    public LobbyAdapter.LobbyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lobby, parent, false);
        LobbyViewHolder lobbyViewHolder = new LobbyViewHolder(view);
        return lobbyViewHolder;
    }

    @Override
    public void onBindViewHolder(LobbyAdapter.LobbyViewHolder holder, int position) {
        holder.tv_lobbyname.setText(lobbyArrayList.get(position).getId() + "");
    }

    protected class LobbyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lobbyname;

        public LobbyViewHolder(View view){
            super(view);

            tv_lobbyname = view.findViewById(R.id.tv_lobbyname);
        }
    }
}
