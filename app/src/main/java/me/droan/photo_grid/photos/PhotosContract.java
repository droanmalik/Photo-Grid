package me.droan.photo_grid.photos;

import java.util.List;

import me.droan.photo_grid.common.BasePresenter;
import me.droan.photo_grid.common.BaseView;
import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosContract {

    interface View extends BaseView<Presenter> {
        void showPhotos(List<Photo> photos);

        void flip(int currentState);
    }

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);

        void loadhotos(List<Photo> photos);

        boolean isShowingBack(int position);
    }
}
