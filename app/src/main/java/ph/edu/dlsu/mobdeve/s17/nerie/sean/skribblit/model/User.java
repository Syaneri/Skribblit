package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

public class User {

    private int userImageId;
    private String name;
    private int id;
    private int highscore;
    private String lobby;

    public User(int userImageId, String name, int id, int highscore, String lobby){
        this.userImageId = userImageId;
        this.name = name;
        this.id = id;
        this.highscore = highscore;
        this.lobby = lobby;
    }

    public User(){

    }

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }
}
