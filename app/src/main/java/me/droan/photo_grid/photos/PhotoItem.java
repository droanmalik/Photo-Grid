package me.droan.photo_grid.photos;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.droan.photo_grid.R;
import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotoItem extends FrameLayout {
    @Bind(R.id.photo)
    ImageView photoImageView;
    private Picasso picasso;

    public PhotoItem(Context context) {
        super(context);
    }

    public PhotoItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bind(Photo photo, Picasso picasso) {
        picasso.load(photo.image_url).into(photoImageView);
    }
}
