package com.example.workshopsorting;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragment extends Fragment {

    private static final String BUNDLE_MESSAGE = "message";
    private TextView mMessageTextView;

    public static ScreenSlidePageFragment newInstance(String message) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mMessageTextView = view.findViewById(R.id.tvMessage);
        setUpTextView();
    }

    private void setUpTextView() {
        String message = "hello";
        if (getArguments() != null) {
            String argMessage = getArguments().getString(BUNDLE_MESSAGE);
            if (argMessage != null && !argMessage.isEmpty()) {
                message = argMessage;
            }
        }
        mMessageTextView.setText(message);
    }
}