package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.LobbyAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.UserAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAO;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityLobbyBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityPostGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.Lobby;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public class PostGameActivity extends AppCompatActivity {

    private ActivityPostGameBinding binding;
    private ArrayList<User> userArrayList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        populateUser();
//        userAdapter = new UserAdapter(getApplicationContext(), userArrayList);
//
//        binding.rvPlayerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        binding.rvPlayerList.setAdapter(userAdapter);

        initDAO();

        binding.btnNewGame.setOnClickListener(view -> {
            Intent gameStart = new Intent(PostGameActivity.this, MainActivity.class);
            startActivity(gameStart);
            finish();
        });

        binding.btnExitGame.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });

    }

    private void initDAO(){
        UserDAO userDAO = new UserDAOSQLImpl(getApplicationContext());
        userAdapter = new UserAdapter(getApplicationContext(), userDAO.getUsers());

        binding.rvPlayerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        binding.rvPlayerList.setAdapter(userAdapter);

        //save user using intent
    }

    private void populateUser() {
        ArrayList<User> userList = new ArrayList<>();
        User user = new User();

        UserDAO userDAO = new UserDAOSQLImpl(getApplicationContext());

        user.setId(1);
        user.setName("Sean");
        user.setUserImageId(R.drawable.mark_face_hehe);
        user.setHighscore(1234);

        userDAO.addUser(user);

        user.setId(2);
        user.setName("Robi");
        user.setUserImageId(R.drawable.mark_face_cry);
        user.setHighscore(1231);

        userDAO.addUser(user);


//        userList.add(new User(
//                R.drawable.mark_face_laugh,
//                "Sean",
//                1,
//                1234
//
//        ));
//
//        userList.add(new User(
//                R.drawable.mark_face_cry,
//                "Robi",
//                2,
//                1231
//
//        ));
//
//        this.userArrayList = userList;
//
//        return userList;
    }
}