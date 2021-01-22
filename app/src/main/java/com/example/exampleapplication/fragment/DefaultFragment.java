package com.example.exampleapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import me.relex.circleindicator.CircleIndicator3;

import com.example.exampleapplication.R;
import com.example.exampleapplication.RecyclerAdapter;

public class DefaultFragment extends Fragment {

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample_default, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerAdapter mAdapter = new RecyclerAdapter(5);

        ViewPager2 viewpager = view.findViewById(R.id.viewpager);
        viewpager.setAdapter(mAdapter);

        CircleIndicator3 indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewpager);

        viewpager.setCurrentItem(2,false);

        mAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());
        view.findViewById(R.id.add).setOnClickListener(v -> mAdapter.add());
        view.findViewById(R.id.remove).setOnClickListener(v -> {
            mAdapter.remove();
        });
    }
}
