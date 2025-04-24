package com.example.newsapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.model.NewsItem;

import java.util.List;

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.RelatedViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    private final List<NewsItem> items;
    private final OnItemClickListener listener;

    public RelatedAdapter(List<NewsItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RelatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_related, parent, false);
        return new RelatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedViewHolder holder, int position) {
        NewsItem item = items.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RelatedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView title, snippet;

        RelatedViewHolder(@NonNull View itemView) {
            super(itemView);
            img     = itemView.findViewById(R.id.imgRelated);
            title   = itemView.findViewById(R.id.tvRelatedTitle);
            snippet = itemView.findViewById(R.id.tvRelatedSnippet);
        }

        void bind(NewsItem item) {
            img.setImageResource(item.getImageRes());
            title.setText(item.getTitle());
            snippet.setText(item.getSnippet());
        }
    }
}
