package com.example.dreamsocialclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private PreferenceData preferenceData;
    private FirebaseAuth signupAuth;
    private TextView tv_registration_already_have_account;
    private EditText et_registration_user_name,et_registration_user_phone_number,et_registration_user_password,et_registration_user_re_type_password;
    private Button btn_registration_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //intview
        initview();
        this.preferenceData = new PreferenceData(SignUpActivity.this);
        tv_registration_already_have_account.setOnClickListener(this);
        btn_registration_login.setOnClickListener(this);



    }

    private void initview() {
        signupAuth = FirebaseAuth.getInstance();
        tv_registration_already_have_account = findViewById(R.id.tv_registration_already_have_account);

        et_registration_user_name = findViewById(R.id.et_registration_user_name);
        et_registration_user_phone_number = findViewById(R.id.et_registration_user_phone_number);
        et_registration_user_password = findViewById(R.id.et_registration_user_password);
        et_registration_user_re_type_password = findViewById(R.id.et_registration_user_re_type_password);
        btn_registration_login = findViewById(R.id.btn_registration_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_registration_already_have_account:
                Intent gotologin = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(gotologin);
                break;
            case R.id.btn_registration_login:
                create_new_account();
                break;
        }
    }
    private void create_new_account() {

                final String name = et_registration_user_name.getText().toString().trim();
                final String phone_number = et_registration_user_phone_number.getText().toString().trim();
                String password = et_registration_user_password.getText().toString().trim();
                String re_password = et_registration_user_re_type_password.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    et_registration_user_name.setError("Enter Name .");
                    et_registration_user_name.requestFocus();
                    return;
                }else if (TextUtils.isEmpty(phone_number)){
                    et_registration_user_phone_number.setError("Enter Phone Number");
                    et_registration_user_phone_number.requestFocus();
                    return;

                }else if (TextUtils.isEmpty(password)){
                    et_registration_user_password.setError("Enter Password");
                    et_registration_user_password.requestFocus();
                    return;
                }else if (password.length()<6){
                    et_registration_user_password.setError("The password must contain at least 6 character");
                    et_registration_user_password.requestFocus();
                    return;

                }else if (TextUtils.isEmpty(re_password)){
                    et_registration_user_re_type_password.setError("Enter Password Again");
                    et_registration_user_re_type_password.requestFocus();
                    return;
                }else if (!password.matches(re_password)){
                    et_registration_user_re_type_password.setError("Password doesn't match");
                    et_registration_user_re_type_password.requestFocus();
                    return;

                }else {

                    preferenceData.setValue("name",name);
                    preferenceData.setValue("phone",phone_number);
                    Intent gotoveryfication = new Intent(SignUpActivity.this,VerifyPhoneActivity.class);
                   gotoveryfication.putExtra("mobile",phone_number);
                    startActivity(gotoveryfication);
                }

    }

}
