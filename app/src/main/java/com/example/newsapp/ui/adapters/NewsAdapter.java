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

public class NewsAdapter
        extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    private final List<NewsItem> items;
    private final OnItemClickListener listener;

    public NewsAdapter(List<NewsItem> items, OnItemClickListener listener) {
        this.items   = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull NewsViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgNews;
        private final TextView tvNewsTitle;
        private final TextView tvNewsSnippet;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNews       = itemView.findViewById(R.id.imgNews);
            tvNewsTitle   = itemView.findViewById(R.id.tvNewsTitle);
            tvNewsSnippet = itemView.findViewById(R.id.tvNewsSnippet);
        }

        void bind(NewsItem item, OnItemClickListener listener) {
            imgNews.setImageResource(item.getImageRes());
            tvNewsTitle.setText(item.getTitle());
            tvNewsSnippet.setText(item.getSnippet());
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
