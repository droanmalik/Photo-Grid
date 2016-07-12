package me.droan.photo_grid.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.droan.photo_grid.R;
import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.Holder> {
    private Picasso picasso;
    private onClickListener listener;
    private List<Photo> photos = new ArrayList<>();

    public PhotosAdapter(Picasso picasso, onClickListener listener) {
        this.picasso = picasso;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(photos.get(position), picasso, listener);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void refresh(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    public interface onClickListener {
        void onClick(ViewGroup viewGroup, int position);
    }

    class Holder extends RecyclerView.ViewHolder {
        PhotoItem photoItem;
        private onClickListener listener;

        public Holder(final View photoItem) {
            super(photoItem);
            this.photoItem = (PhotoItem) photoItem;
            photoItem.setOnClickListener(v -> listener.onClick((FrameLayout) photoItem, getAdapterPosition()));
        }


        public void bind(Photo photo, Picasso picasso, onClickListener listener) {
            this.listener = listener;
            photoItem.bind(photo, picasso);

        }
    }
}
