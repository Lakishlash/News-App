package com.example.newsapp.ui.adapters;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.model.NewsItem;
import java.util.List;

public class TopStoriesAdapter
        extends RecyclerView.Adapter<TopStoriesAdapter.TopStoryViewHolder> {

    private final List<NewsItem> items;

    public TopStoriesAdapter(List<NewsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        // Inflate item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_story, parent, false);

        // 1) Calculate usable width = screen width minus 2 arrow buttons
        DisplayMetrics dm = parent.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int arrowSize   = parent.getResources()
                .getDimensionPixelSize(R.dimen.arrow_button_size);
        int usableWidth = screenWidth - (arrowSize * 2);

        // 2) Each card = half of that usable space
        int cardWidth = usableWidth / 2;

        // 3) Apply to the view's LayoutParams
        RecyclerView.LayoutParams lp =
                (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.width = cardWidth;
        view.setLayoutParams(lp);

        return new TopStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TopStoryViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class TopStoryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgTopStory;

        TopStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopStory = itemView.findViewById(R.id.imgTopStory);
        }

        void bind(NewsItem item) {
            imgTopStory.setImageResource(item.getImageRes());
        }
    }
}
