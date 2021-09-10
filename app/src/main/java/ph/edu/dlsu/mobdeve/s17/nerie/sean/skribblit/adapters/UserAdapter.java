package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.R;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private ArrayList<User> userArrayList;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> userArrayList){
        this.userArrayList = userArrayList;
        this.context = context;
    }

    public UserAdapter.UserViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_highscore, parent, false);
        UserAdapter.UserViewHolder userViewHolder = new UserAdapter.UserViewHolder(view);
        return userViewHolder;
    }
    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        holder.tv_user_highscore.setText(userArrayList.get(position).getName() + "");
        holder.tv_score.setText(userArrayList.get(position).getHighscore() + "");
    }

    protected class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_user_highscore;
        TextView tv_score;

        public UserViewHolder(View view){
            super(view);
            tv_score = view.findViewById(R.id.tv_score);
            tv_user_highscore = view.findViewById(R.id.tv_user_highscore);
        }
    }
}


