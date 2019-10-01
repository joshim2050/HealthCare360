package com.example.dreamsocialclub.home.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dreamsocialclub.CasinoActivity;
import com.example.dreamsocialclub.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button earn_coin;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        earn_coin = root.findViewById(R.id.earn_coin);
        earn_coin.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.earn_coin:
                Intent intent = new Intent(getContext(),CasinoActivity.class);
                startActivity(intent);
                break;
        }

    }
}