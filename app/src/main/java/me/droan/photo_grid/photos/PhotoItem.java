package me.droan.photo_grid.photos;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
    @Bind(R.id.title)
    TextView title;

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
        picasso.load(photo.getImage_url()).into(photoImageView);
        title.setText(photo.getName());
        checkVisibility(photo);
    }

    private void checkVisibility(Photo photo) {
        if ((photo.isShowingBack())) {
            title.setAlpha(1.0f);
            photoImageView.setAlpha(0.0f);
        } else {
            title.setAlpha(0);
            photoImageView.setAlpha(1.0f);
        }
    }
}
