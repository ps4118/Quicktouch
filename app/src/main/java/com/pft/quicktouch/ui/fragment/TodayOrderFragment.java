package com.pft.quicktouch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pft.quicktouch.R;


public class TodayOrderFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_today_order, null);
        }
        initview();
        return view;
    }

    public static TodayOrderFragment newInstance() {
        TodayOrderFragment fragment = new TodayOrderFragment();
        return fragment;
    }

    private void initview() {

    }
}
