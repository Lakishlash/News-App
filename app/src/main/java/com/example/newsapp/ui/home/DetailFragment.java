package com.example.newsapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.model.NewsItem;
import com.example.newsapp.ui.adapters.RelatedAdapter;

public class DetailFragment extends Fragment {

    private NewsItem item;

    public static DetailFragment newInstance(NewsItem newsItem) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", newsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            item = (NewsItem) getArguments().getSerializable("item");
        }

        ImageView imgHeader = view.findViewById(R.id.imgDetailHeader);
        TextView tvTitle    = view.findViewById(R.id.tvDetailTitle);
        TextView tvBody     = view.findViewById(R.id.tvDetailBody);

        if (item != null) {
            imgHeader.setImageResource(item.getImageRes());
            tvTitle.setText(item.getTitle());

            //  Long content for realism
            String fullText = item.getSnippet() + "\n\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n\n" +
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                    "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
                    "Donec in sodales dui, a blandit nunc. Pellentesque id eros quis nunc suscipit blandit.";
            tvBody.setText(fullText);
        }

        RecyclerView rvRelated = view.findViewById(R.id.rvRelated);
        rvRelated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvRelated.setAdapter(new RelatedAdapter(
                item != null ? item.getRelated() : java.util.Collections.emptyList(),
                clickedItem -> {
                    DetailFragment newFragment = DetailFragment.newInstance(clickedItem);
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, newFragment)
                            .addToBackStack(null)
                            .commit();
                }
        ));
    }
}
