package me.droan.photo_grid.photos;

import java.util.List;

import me.droan.photo_grid.model.photos.Photo;
import me.droan.photo_grid.service.PhotosApi;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosPresenter implements PhotosContract.Presenter {


    private PhotosContract.View photosView;
    private PhotosApi service;
    private List<Photo> photos;

    public PhotosPresenter(PhotosContract.View photosView, PhotosApi service) {

        this.photosView = photosView;
        this.service = service;
        photosView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadPhotos(List<Photo> photos) {
        this.photos = photos;
        photosView.showPhotos(photos);
    }

    @Override
    public void checkFlip(int position) {
        if (isShowingBack(position)) {
            photosView.flipFront();
        } else {
            photosView.flipBack();
        }
    }

    private boolean isShowingBack(int position) {
        boolean _isShowingBack = photos.get(position).isShowingBack();
        updateIsShowingBack(position, _isShowingBack);
        return _isShowingBack;
    }

    private boolean updateIsShowingBack(int position, boolean _isShowingBack) {
        photos.get(position).setShowingBack(!_isShowingBack);
        return photos.get(position).isShowingBack();
    }

    @Override
    public void start() {
        service.listRepositories(PhotosApi.CATEGORY_ANIMALS, "popular", this::loadPhotos);
    }
}
