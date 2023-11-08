package com.example.lab5contactdatabase;
public class PersonModal {
    private String name;
    private String DoB;
    private String email;
    private int id;
    private int avatarResource;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthDate() {
        return DoB;
    }
    public void setBirthDate(String doB) {
        DoB = doB;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAvatarResource() {
        return avatarResource;
    }
    public void setAvatarResource(int avatarResource) {
        this.avatarResource = avatarResource;
    }
    public PersonModal(int id, String name, String doB, String email, int avatar) {
        this.name = name;
        DoB = doB;
        this.email = email;
        this.id = id;
        this.avatarResource = avatar;
    }
}
