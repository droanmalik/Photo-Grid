
package me.droan.photo_grid.model.photos;

import java.util.ArrayList;
import java.util.List;

public class PhotoList {

    public int current_page;
    public int total_pages;
    public int total_items;
    public List<Photo> photos = new ArrayList<Photo>();
    public String feature;

}
