package com.example.booking_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking_android.R;
import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.User;
import com.example.booking_android.login.LoginContract;
import com.example.booking_android.login.model.LoginModel;
import com.example.booking_android.login.presenter.LoginPresenter;
import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_hotels.presenter.LstHotelPresenter;
import com.example.booking_android.lst_hotels.view.LstHotelActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParametersActivity extends AppCompatActivity {
    private EditText date_selector_check_in;
    private EditText date_selector_check_out;
    private boolean isCorrect;
    private TextView nombre_user_hoteles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        nombre_user_hoteles = findViewById(R.id.nombre_user_hoteles);
        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String name = prefs.getString("client_name","");
        nombre_user_hoteles.setText(name);

        dateCheckIn();
        dateCheckOut();


    }



    public void dateCheckIn(){
        // on below line we are initializing our variables.
        date_selector_check_in = findViewById(R.id.date_selector_check_in);

        // on below line we are adding click listener
        // for our pick date button
        date_selector_check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        ParametersActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                date_selector_check_in.setText(year + "-" + "0"+(monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

    }
    public void dateCheckOut(){
        date_selector_check_out = findViewById(R.id.date_selector_check_out);
        date_selector_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        ParametersActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                date_selector_check_out.setText(year + "-" + "0"+(monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        },

                        year, month, day);

                datePickerDialog.show();
            }
        });

    }

    public void operacion(View view) {
        EditText date_check_in = findViewById(R.id.date_selector_check_in);
        EditText date_check_out = findViewById(R.id.date_selector_check_out);
        EditText selected_country = findViewById(R.id.selected_country);
        EditText selected_city = findViewById(R.id.selected_city);
        EditText selected_guests = findViewById(R.id.selected_guests);
        Switch top_hotels = findViewById(R.id.top_hotels);
        String error_msg = "";
        Boolean showErrors = false;
        Intent intent = null;
        Intent oldIntent = getIntent();
        switch (view.getId()) {
            case R.id.search_hotels:
                String date_check_in_string = date_check_in.getText().toString();
                String date_check_out_string  = date_check_out.getText().toString();
                String selected_country_string = selected_country.getText().toString();
                String selected_city_string = selected_city.getText().toString();
                String selected_guests_string = selected_guests.getText().toString();
                Boolean top_hotels_boolean = top_hotels.isChecked();
                String top_hotels_string;


                if(calculateDates(date_check_in_string,date_check_out_string) == false || date_check_in_string.length() == 0 || date_check_out_string.length() == 0) {
                    error_msg += "• Check-in or check-out dates incorrect";
                    showErrors = true;
                }

                if(selected_country_string.length() == 0 || selected_country_string.length() < 3 ){
                    if(error_msg != ""){
                        error_msg += "\n• Select a destination country!";
                    }else{
                        error_msg += "• Select a destination country!";
                    }

                    showErrors = true;
                }

                if(selected_guests_string.length() == 0){
                    if(error_msg != ""){
                        error_msg += "\n• Number of guests not valid";
                    }else{
                        error_msg += "• Number of guests not valid";
                    }

                    showErrors = true;
                }

                if(selected_city_string.length() == 0){
                    selected_city_string = "null";
                }
                if(top_hotels_boolean == true){
                    top_hotels_string ="YES";
                }else{
                    top_hotels_string ="NO";
                }

                if(showErrors){
                    showErrorToast(error_msg);
                }else{
                    // Guardamos el check-in y check-out para las habitaciones de luego

                    SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("check_in", date_check_in_string);
                    editor.putString("check_out",date_check_out_string);
                    editor.putString("guests", selected_guests_string);
                    editor.apply();


                    intent = new Intent(getBaseContext(),LstHotelActivity.class);
                    intent.putExtra("selected_check_in", date_check_in_string);
                    intent.putExtra("selected_check_out", date_check_out_string);
                    intent.putExtra("selected_country",selected_country_string);
                    intent.putExtra("selected_city",selected_city_string);
                    intent.putExtra("selected_guests",selected_guests_string);
                    intent.putExtra("top_hotels",top_hotels_string);
                    startActivity(intent);
                }


                break;
            case R.id.login_profile_button:

                SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
                String name = prefs.getString("client_name","");
                if(name == ""){
                    intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.putExtra("isFromMain",false);
                }else{
                    intent = new Intent(getBaseContext(), ProfileActivity.class);
                }

                startActivity(intent);
                break;
        }


    }


    public boolean calculateDates(String date1, String date2){


        String date = date1;
        String dateafter = date2;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date convertedDate = new Date();
        Date convertedDate2 = new Date();
        try {

            convertedDate = dateFormat.parse(date);
            convertedDate2 = dateFormat.parse(dateafter);
            if (convertedDate2.after(convertedDate)) {
                isCorrect = true;
            } else {
                isCorrect = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return isCorrect;
    }


    public void showErrorToast(String error){
        Context context = getApplicationContext();

        LayoutInflater inflater = getLayoutInflater();

        View customToastView = inflater.inflate(R.layout.custom_toast, null);

        TextView toastMessage = customToastView.findViewById(R.id.toast_message);
        toastMessage.setText(error);

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