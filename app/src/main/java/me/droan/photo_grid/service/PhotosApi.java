package me.droan.photo_grid.service;

import android.support.annotation.StringDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import me.droan.photo_grid.BuildConfig;
import me.droan.photo_grid.model.photos.Photo;
import me.droan.photo_grid.model.photos.PhotoRepository;
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
    public static final String CATEGORY_LANDSCAPE = "Landscapes";
    public static final String CATEGORY_ANIMALS = "Animals";
    public static final String CATEGORY_FOOD = "Food";
    private static final String TAG = "PhotosApi";
    private static final String PHOTOS_ENDPOINT = "https://api.500px.com/v1/";
    private final PhotosService service;

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

    public void listRepositories(@CategoryName String category, String feature, final DataChangeListener listener) {
        service.fetchPhotos(category, feature).enqueue(new Callback<PhotoRepository>() {
            @Override
            public void onResponse(Call<PhotoRepository> call, Response<PhotoRepository> response) {
                if (response.isSuccessful()) {
                    PhotoRepository model = response.body();
                    listener.onDataAdded(model.photos);
                }
            }

            @Override
            public void onFailure(Call<PhotoRepository> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage() + call.toString());
            }
        });

    }
    public interface PhotosService {
        @GET("photos?consumer_key=" + BuildConfig.API_KEY)
        Call<PhotoRepository> fetchPhotos(
                @Query("only") String category,
                @Query("feature") String feature);
    }
    public interface DataChangeListener {
        void onDataAdded(List<Photo> photos);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            CATEGORY_LANDSCAPE,
            CATEGORY_ANIMALS,
            CATEGORY_FOOD
    })
    public @interface CategoryName {
    }
}
