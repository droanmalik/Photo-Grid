package me.droan.photo_grid.photos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.droan.photo_grid.R;
import me.droan.photo_grid.service.PhotosApi;
import me.droan.photo_grid.util.ActivityUtil;

public class PhotosActivity extends AppCompatActivity {

    private static final String TAG = "ShowcaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_activity);

        //create the fragment
        PhotosFragment photosFragment = (PhotosFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (photosFragment == null) {
            photosFragment = PhotosFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), photosFragment, R.id.contentFrame);
        }

        //set services
        PhotosApi service = new PhotosApi();
        //create the presenter
        PhotosPresenter photosPresenter = new PhotosPresenter(photosFragment, service);
    }
}
