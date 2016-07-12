package me.droan.photo_grid.photos;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.droan.photo_grid.R;
import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosFragment extends Fragment implements PhotosContract.View {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    PhotosAdapter adapter;
    PhotosContract.Presenter photosPresenter;
    private ImageView background;
    private TextView title;
    private AnimatorSet animateImageBack; //= (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_image_back);
    private AnimatorSet animateImageFront;// = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_image_front);
    private AnimatorSet animateTextBack;// = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_text_back);
    private AnimatorSet animateTextFront;// = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_text_front);

    public static PhotosFragment newInstance() {

        Bundle args = new Bundle();
        PhotosFragment fragment = new PhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animateImageBack = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_image_back);
        animateImageFront = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_image_front);
        animateTextBack = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_text_back);
        animateTextFront = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_text_front);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_fragment, container, false);
        ButterKnife.bind(this, view);
        initToolbar();
        initRecyclerView();
        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new PhotosAdapter(Picasso.with(getContext()), (viewGroup, position) -> {
            background = (ImageView) viewGroup.getChildAt(0);
            title = (TextView) viewGroup.getChildAt(1);
            photosPresenter.checkFlip(position);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        photosPresenter.start();
    }

    @Override
    public void showPhotos(List<Photo> photos) {
        adapter.refresh(photos);
    }

    @Override
    public void flipFront() {
        animateImageBack.setTarget(background);
        animateTextBack.setTarget(title);
        animateImageBack.start();
        animateTextBack.start();
    }

    @Override
    public void flipBack() {
        animateImageFront.setTarget(background);
        animateTextFront.setTarget(title);
        animateImageFront.start();
        animateTextFront.start();
    }

    @Override
    public void setPresenter(PhotosContract.Presenter presenter) {
        this.photosPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
