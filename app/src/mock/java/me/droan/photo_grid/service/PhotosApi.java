package me.droan.photo_grid.service;

import java.util.ArrayList;
import java.util.List;

import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 13-07-2016.
 */
public class PhotosApi {
    public static final String CATEGORY_LANDSCAPE = "Landscapes";
    public static final String CATEGORY_ANIMALS = "Animals";
    public static final String CATEGORY_FOOD = "Food";

    public PhotosApi() {
    }

    public void listRepositories(String category, String feature, final DataChangeListener listener) {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo("Droan", "http://loremflickr.com/320/240/dog", false));
        photos.add(new Photo("Droan1", "http://loremflickr.com/320/240/cat", true));
        photos.add(new Photo("Droan2", "http://loremflickr.com/320/240/paris", false));
        photos.add(new Photo("Droan3", "http://loremflickr.com/320/240/dog", true));
        photos.add(new Photo("Droan4", "http://loremflickr.com/320/240/dog", false));
        photos.add(new Photo("Droan5", "http://loremflickr.com/320/240/paris", false));
        listener.onDataAdded(photos);
    }

    public interface DataChangeListener {
        void onDataAdded(List<Photo> photos);
    }

}
