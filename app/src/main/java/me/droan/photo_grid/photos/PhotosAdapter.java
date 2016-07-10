package me.droan.photo_grid.photos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.droan.photo_grid.R;
import me.droan.photo_grid.model.photos.Photo;

/**
 * Created by drone on 11-07-2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.Holder> {
    private Context context;
    private Picasso picasso;
    private List<Photo> photos = new ArrayList<>();

    public PhotosAdapter(Context context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.photo_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(photos.get(position), picasso);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void refresh(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        PhotoItem photoItem;

        public Holder(View photoItem) {
            super(photoItem);
            this.photoItem = (PhotoItem) photoItem;
        }


        public void bind(Photo photo, Picasso picasso) {
            photoItem.bind(photo, picasso);


        }
    }
}
