package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

public class User {

    private int userImageId;
    private String name;

    public User(int userImageId, String name){
        this.userImageId = userImageId;
        this.name = name;
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
}
