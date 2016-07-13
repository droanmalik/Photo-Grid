
package me.droan.photo_grid.model.photos;

public class Photo {

    private String name;
    private String image_url;
    private boolean isShowingBack;

    public Photo(String name, String image_url, boolean isShowingBack) {
        this.name = name;
        this.image_url = image_url;
        this.isShowingBack = isShowingBack;
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

    public boolean isShowingBack() {
        return isShowingBack;
    }

    public void setShowingBack(boolean showingBack) {
        isShowingBack = showingBack;
    }
}
