package com.example.dreamsocialclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
   private TextView tv_login_need_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intview();
        tv_login_need_account.setOnClickListener(this);
    }

    private void intview() {
        tv_login_need_account = findViewById(R.id.tv_login_need_account);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login_need_account:
                Intent gotosignup = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(gotosignup);
                break;
        }

    }
}
