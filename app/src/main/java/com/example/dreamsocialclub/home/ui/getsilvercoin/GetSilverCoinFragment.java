package com.example.dreamsocialclub.home.ui.getsilvercoin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dreamsocialclub.R;

public class GetSilverCoinFragment extends Fragment implements View.OnClickListener {
    public GetSilverCoinFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_get_silver_coin,container,false);



        return view;
    }

    @Override
    public void onClick(View view) {

    }
}