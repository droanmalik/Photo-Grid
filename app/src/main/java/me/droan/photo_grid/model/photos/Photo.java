
package me.droan.photo_grid.model.photos;

public class Photo {

    private int id;
    private String name;
    private String image_url;
    private int width;
    private int height;
    private boolean isShowingBack;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isShowingBack() {
        return isShowingBack;
    }

    public void setShowingBack(boolean showingBack) {
        isShowingBack = showingBack;
    }
}
