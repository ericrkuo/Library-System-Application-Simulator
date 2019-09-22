package model;

import javafx.scene.image.Image;
import model.observer.Subject;

import java.util.Objects;


public abstract class Media extends Subject {
    protected String title;
    protected MediaCategory category;
    protected int id;
    protected boolean isAvailable;
    protected Image image;

    public Media(MediaCategory category, String title, boolean isAvailable, int id, Image image) {
        this.category = category;
        this.title = title;
        this.isAvailable = isAvailable;
        this.id = id;
        this.image = image;
    }

    //getters
    public String getTitle() {
        return title;
    }

    public MediaCategory getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Image getImage() {
        return image;
    }

    //setters
    public void setCategory(MediaCategory category) {
        this.category = category;
    }

    public void setAvailable(Boolean available) {
        this.isAvailable = available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void flipAvailability() {
        this.isAvailable = !isAvailable;
        addObserver(new MediaChain());
        notifyObserver(this);
    }

    public abstract void printInfo();

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Media)) {
            return false;
        }

        Media media = (Media) o;
        return id == media.id
                && isAvailable == media.isAvailable
                && Objects.equals(title, media.title)
                && category == media.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category, id, isAvailable);
    }
}
