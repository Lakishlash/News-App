package com.example.newsapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.model.NewsItem;
import com.example.newsapp.ui.adapters.NewsAdapter;
import com.example.newsapp.ui.adapters.TopStoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rvTopStories, rvNews;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // 1) Greeting
        TextView tvGreeting = view.findViewById(R.id.tvGreeting);
        String userName = "John";
        tvGreeting.setText(
                String.format(getString(R.string.greeting_format), userName)
        );

        // 2) Find views
        rvTopStories = view.findViewById(R.id.rvTopStories);
        rvNews = view.findViewById(R.id.rvNews);
        ImageButton btnLeft = view.findViewById(R.id.btnLeft);
        ImageButton btnRight = view.findViewById(R.id.btnRight);

        // 3) Layout managers
        rvTopStories.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        rvNews.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // 4) Load dummy data
        List<NewsItem> newsList = loadDummyNews();
        List<NewsItem> topStories = newsList.subList(0, Math.min(3, newsList.size()));

        // 5) Set adapters
        rvTopStories.setAdapter(new TopStoriesAdapter(topStories));
        rvNews.setAdapter(new NewsAdapter(newsList, item -> {
            DetailFragment frag = DetailFragment.newInstance(item);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, frag)
                    .addToBackStack(null)
                    .commit();
        }));

        // 6) Arrow buttons scroll offset
        int imgSize = getResources().getDimensionPixelSize(R.dimen.item_image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.margin_half) * 2;
        final int offset = imgSize + margin;

        btnLeft.setOnClickListener(v -> rvTopStories.smoothScrollBy(-offset, 0));
        btnRight.setOnClickListener(v -> rvTopStories.smoothScrollBy(offset, 0));
    }

    private List<NewsItem> loadDummyNews() {
        String[] titles = {
                "Australia’s Budget 2025 Announced",
                "Global Markets Surge Overnight",
                "New Study Reveals Diet Secrets",
                "Melbourne's Transport Expands",
                "Tech Conference Highlights 2025",
                "Tips to Boost Work Productivity"
        };

        String[] snippets = {
                "What you need to know from the budget release.",
                "Dow and Nasdaq hit record highs last night.",
                "Surprising findings from new Aussie research.",
                "New train lines and stops across Melbourne.",
                "Top trends revealed at this year’s tech summit.",
                "Simple habits to improve focus every day."
        };

        List<NewsItem> allItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            int resId = getResources().getIdentifier(
                    "news_" + (i + 1),
                    "drawable",
                    requireContext().getPackageName()
            );

            allItems.add(new NewsItem(
                    "news_" + (i + 1),
                    titles[i],
                    snippets[i],
                    resId,
                    new ArrayList<>() // placeholder for related
            ));
        }

        // Add 2 related items to each news item (excluding itself)
        for (int i = 0; i < allItems.size(); i++) {
            List<NewsItem> related = new ArrayList<>();
            for (int j = 0; j < allItems.size(); j++) {
                if (j != i && related.size() < 2) {
                    related.add(allItems.get(j));
                }
            }
            allItems.get(i).getRelated().addAll(related);
        }

        return allItems;
    }
}
