package me.droan.photo_grid.photos;

import java.util.List;

import me.droan.photo_grid.data.PhotosApi;
import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosPresenter implements PhotosContract.Presenter {

    private PhotosFragment photosView;
    private PhotosApi service;
    private List<Photo> photos;

    public PhotosPresenter(PhotosFragment photosView, PhotosApi service) {

        this.photosView = photosView;
        this.service = service;
        photosView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadhotos(List<Photo> photos) {
        this.photos = photos;
        photosView.showPhotos(photos);
    }

    @Override
    public boolean isShowingBack(int position) {
        boolean _isShowingBack = photos.get(position).isShowingBack;
        updateIsShowingBack(position, _isShowingBack);
        return _isShowingBack;
    }

    private boolean updateIsShowingBack(int position, boolean _isShowingBack) {
        photos.get(position).isShowingBack = !_isShowingBack;
        return photos.get(position).isShowingBack;
    }

    @Override
    public void start() {
        service.listRepositories("Landscapes", "popular", new PhotosApi.DataChangeListener(

        ) {
            @Override
            public void onDataAdded(List<Photo> photos) {
                loadhotos(photos);
            }
        });
    }
}
