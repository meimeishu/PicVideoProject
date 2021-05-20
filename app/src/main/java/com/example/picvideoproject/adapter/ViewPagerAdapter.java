package com.example.picvideoproject.adapter;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.picvideoproject.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    List<String> mDatas = new ArrayList<String>();

    public ViewPagerAdapter() {
        mDatas.add("111111");
        mDatas.add("111111");
        mDatas.add("111111");
        mDatas.add("111111");
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @org.jetbrains.annotations.NotNull View view, @NonNull @org.jetbrains.annotations.NotNull Object object) {
        return false;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }
}
