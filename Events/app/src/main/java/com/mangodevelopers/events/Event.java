package com.mangodevelopers.events;

/**
 * Created by AmanTugnawat on 24-09-16.
 */
public class Event {

    private int id;
    private String name;
    private String image;
    private String description;
    private String category;
    private String ctc;
    private String experience;
    private String link;
    private int fav;
    private long time;

    public Event() {
    }

    public Event(int id, String name, String image, String category, String description, String experience) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        //this.ctc = ctc;
        this.experience = experience;
        //this.link = link;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
