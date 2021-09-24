package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        holder.tv_lobbyname.setText(lobbyArrayList.get(position).getName() + "");

        holder.ll_lobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DATA", "PRESSED");
                holder.ll_lobby.setBackgroundResource(R.drawable.lobby_selected);
//                holder.tv_lobbyname.setText("selected");

                lobbyArrayList.get(position).setSelected(true);
                for(Lobby lobby: lobbyArrayList){
                    if(lobby.getId() != lobbyArrayList.get(position).getId()){
//                        holder.ll_lobby.setBackgroundResource(R.drawable.lobby_background);
                        lobby.setSelected(false);
                    }
                }
            }
        });

    }

    protected class LobbyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lobbyname;
        LinearLayout ll_lobby;

        public LobbyViewHolder(View view){
            super(view);
            ll_lobby = view.findViewById(R.id.ll_lobby);
            tv_lobbyname = view.findViewById(R.id.tv_lobbyname);
        }
    }
}
