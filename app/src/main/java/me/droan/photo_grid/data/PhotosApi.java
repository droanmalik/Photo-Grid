package me.droan.photo_grid.data;

import android.util.Log;

import java.util.List;

import me.droan.photo_grid.BuildConfig;
import me.droan.photo_grid.model.photos.Photo;
import me.droan.photo_grid.model.photos.PhotosModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosApi {
    private static final String TAG = "PhotosApi";
    private static final String PHOTOS_ENDPOINT = "https://api.500px.com/v1/";
    private final PhotosService service;

    public interface PhotosService {
        @GET("photos?consumer_key=" + BuildConfig.API_KEY)
        Call<PhotosModel> getPopularMovies(
                @Query("only") String category,
                @Query("feature") String feature);
    }

    public PhotosApi() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PHOTOS_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(PhotosService.class);
    }

    public interface PhotosService {
        @GET("photos?consumer_key=" + BuildConfig.API_KEY)
        Call<PhotosModel> getPopularMovies(
                @Query("only") String category,
                @Query("feature") String feature);
    }

    public void listRepositories(String category, String feature, final DataChangeListener listener) {
        service.getPopularMovies(category, feature).enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(Call<PhotosModel> call, Response<PhotosModel> response) {
                PhotosModel model = response.body();
                listener.onDataAdded(model.photos);
            }

            @Override
            public void onFailure(Call<PhotosModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage() + call.toString());
            }
        });

    }

    public interface DataChangeListener {
        void onDataAdded(List<Photo> photos);
    }
}
