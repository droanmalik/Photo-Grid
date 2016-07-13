package me.droan.photo_grid.photos;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import me.droan.photo_grid.model.photos.Photo;
import me.droan.photo_grid.model.photos.PhotoRepository;
import me.droan.photo_grid.service.PhotosApi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosPresenterTest {

    @Mock
    PhotosApi service;
    @Mock
    private PhotoRepository repository;
    @Mock
    private PhotosContract.View view;
    @Captor
    private ArgumentCaptor<PhotosApi.DataChangeListener> loadPhotosCaptor;

    private PhotosPresenter presenter;

    private List<Photo> list = new ArrayList<>();

    @Before
    public void setupPhotosPPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new PhotosPresenter(view, service);
        view.setPresenter(presenter);
    }

    @Test
    public void shouldCallServiceOnStartWhichLoadsDataOSucess() {
        presenter.start();
        List<Photo> photos = getPhotos();
        verify(service).listRepositories(anyString(), anyString(), loadPhotosCaptor.capture());
        loadPhotosCaptor.getValue().onDataAdded(photos);
        verify(view).showPhotos(photos);
    }


    @Test
    public void testIsShowingBack() throws Exception {
        List<Photo> photos = getPhotos();
        assertEquals("Back side is showing", photos.get(1).isShowingBack(), true);
        assertEquals("front side is showing", photos.get(0).isShowingBack(), false);
    }

    @Test
    public void loadPhotosFromServiceAndLoadIntoView() {

        List<Photo> photos = getPhotos();
        presenter.loadPhotos(photos);
        verify(view).showPhotos(photos);
    }

    @NonNull
    private List<Photo> getPhotos() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo("Droan", "http://loremflickr.com/320/240/dog", false));
        photos.add(new Photo("Droan1", "http://loremflickr.com/320/240/cat", true));
        photos.add(new Photo("Droan2", "http://loremflickr.com/320/240/paris", false));
        photos.add(new Photo("Droan3", "http://loremflickr.com/320/240/dog", true));
        photos.add(new Photo("Droan4", "http://loremflickr.com/320/240/dog", false));
        photos.add(new Photo("Droan5", "http://loremflickr.com/320/240/paris", false));
        return photos;
    }
}