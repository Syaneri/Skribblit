package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.adapters.UserAdapter;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAO;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao.UserDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.databinding.ActivityPostGameBinding;
import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public class PostGameActivity extends AppCompatActivity {

    private ActivityPostGameBinding binding;
    private ArrayList<User> userArrayList;
    private UserAdapter userAdapter;

    private String name;
    private String lobby;
    private int dp;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.name = getIntent().getStringExtra("name");
        this.lobby = getIntent().getStringExtra("lobby");
        this.dp = getIntent().getIntExtra("dp", 0);
        this.score = getIntent().getIntExtra("score", 0);

        //create user
//        saveUser(this.name, this.dp, this.score, this.lobby);

//        populateUser();

//        populateUser();
//        userAdapter = new UserAdapter(getApplicationContext(), userArrayList);
//
//        binding.rvPlayerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        binding.rvPlayerList.setAdapter(userAdapter);

        initDAO();
    }

    private void initDAO(){
        UserDAO userDAO = new UserDAOSQLImpl(getApplicationContext());

        User user = new User();
        user.setId(userDAO.getSize() + 1);
        user.setName(this.name);
        user.setUserImageId(this.dp);
        user.setHighscore(this.score);
        user.setLobby(this.lobby);
        userDAO.addUser(user);

        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList = userDAO.getUsers();

        Collections.sort(userArrayList, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                if (u1.getHighscore() < u2.getHighscore())
                    return 1;
                if (u1.getHighscore() > u2.getHighscore())
                    return -1;
                return 0;
            }
        });

        userAdapter = new UserAdapter(getApplicationContext(), userArrayList);

        binding.rvPlayerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        binding.rvPlayerList.setAdapter(userAdapter);

        binding.btnNewGame.setOnClickListener(view -> {
            Intent gameStart = new Intent(PostGameActivity.this, MainActivity.class);
            startActivity(gameStart);
            finish();
        });

        binding.btnExitGame.setOnClickListener(view -> {
            finishAndRemoveTask();
        });



        //save user using intent
    }

//    private void populateUser() {
//        ArrayList<User> userList = new ArrayList<>();
//        User user = new User();
//
//        UserDAO userDAO = new UserDAOSQLImpl(getApplicationContext());
//
//        user.setId(3);
//        user.setName("Sean");
//        user.setUserImageId(R.drawable.mark_face_hehe);
//        user.setHighscore(1250);
//
//        userDAO.addUser(user);
//
//        user.setId(2);
//        user.setName("Robi");
//        user.setUserImageId(R.drawable.mark_face_cry);
//        user.setHighscore(1231);
//
//        userDAO.addUser(user);
//
//
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
//    }
}