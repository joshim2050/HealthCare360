package com.example.dreamsocialclub.home.ui.convertCoin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dreamsocialclub.R;

public class ConvertCoinFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_convert_coin, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        return root;
    }
}