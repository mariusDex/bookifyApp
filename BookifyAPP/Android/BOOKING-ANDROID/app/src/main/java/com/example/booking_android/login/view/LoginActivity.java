package com.example.booking_android.login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking_android.ParametersActivity;
import com.example.booking_android.R;
import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.Parameters;
import com.example.booking_android.entities.User;
import com.example.booking_android.login.LoginContract;
import com.example.booking_android.login.model.LoginModel;
import com.example.booking_android.login.presenter.LoginPresenter;
import com.example.booking_android.lst_hotels.presenter.LstHotelPresenter;
import com.example.booking_android.lst_hotels.view.LstHotelActivity;

import org.w3c.dom.Text;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginPresenter loginPresenter;
    private LoginModel loginModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void operacionInicial(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.sign_in:
                initPresenter();
                initData();


                //intent = new Intent(getBaseContext(), LstHotelActivity.class);
                //intent.putExtra("")
                break;
            case R.id.sign_up:
                intent = new Intent(getBaseContext(), LstHotelActivity.class);
                break;
        }

        //startActivity(intent);
    }




    public void initPresenter(){
        loginPresenter = new LoginPresenter(this);
    }
    public void initData(){
        User user;
        TextView emailView = findViewById(R.id.email);
        TextView passwordView = findViewById(R.id.password);
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        if(email.length() == 0 || password.length() == 0){
            user = new User("null", "null");
        }else{
            user = new User(email,password);
        }


        loginPresenter.user(user);
    }


    @Override
    public void successLogin(User user) {

        if(user  != null ){
            Toast.makeText(this, "Bienvenido " + user.getName() + " !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), ParametersActivity.class);
            Intent oldIntent = getIntent();
            // ALMACENAMIENTO DE VARIABLES GLOBALES

            SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("client_name", user.getName());
            editor.putString("client_id",user.getClient_id());
            editor.putString("client_name",user.getName());
            editor.putString("client_surname",user.getSurname());
            editor.putString("client_nif",user.getClient_nif());
            editor.putString("client_email",user.getEmail());
            editor.putString("client_birth_date",user.getBirth_date());
            editor.putString("client_phone",user.getPhone());
            editor.apply();

            startActivity(intent);

        }else{
            Toast.makeText(this, "Login incorrecto !" + user.getEmail(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void failureLogin(String err) {

        Context context = getApplicationContext();

        LayoutInflater inflater = getLayoutInflater();

        View customToastView = inflater.inflate(R.layout.custom_toast, null);

        TextView toastMessage = customToastView.findViewById(R.id.toast_message);
        toastMessage.setText(err);

        Toast customToast = new Toast(context);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.setView(customToastView);
        customToast.setGravity(Gravity.TOP, 0, 200);

        customToast.show();
    }












    public void goBack(View view){
        onBackPressed();
    }


}