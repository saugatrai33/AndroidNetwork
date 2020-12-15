package com.saugatrai.androidnetwork.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saugatrai.androidnetwork.R;
import com.saugatrai.androidnetwork.model.PhotoResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<PhotoResponse> responseList;

    public PhotoAdapter(List<PhotoResponse> photoResponses) {
        this.responseList = photoResponses;
    }

    public void setResponseList(List<PhotoResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoResponse response = responseList.get(position);
        Picasso.get().load(response.getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.photoImg);
        holder.title.setText(response.getTitle());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoImg;
        private TextView title;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImg = itemView.findViewById(R.id.img_poto);
            title = itemView.findViewById(R.id.text_title);
        }
    }

}
