package com.example.c0752439_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserLogin extends AppCompatActivity {
    EditText username,password;
    Button submit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        init();
        if(sharedPreferences.contains("status")){
            if(sharedPreferences.getString("status",null).equals("1")){
                startActivity(new Intent(UserLogin.this,UserHome.class));
                finish();
            }
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().trim().length() == 0){
                    username.setError("Field is empty");
                    showAlert(UserLogin.this,"Error","Enter Username");
                }
                else if(password.getText().toString().trim().length() == 0){
                    password.setError("Field is empty");
                    showAlert(UserLogin.this,"Error","Enter Password");
                }
                else {
                    if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
                        if (sharedPreferences.getString("username", null).equals(username.getText().toString().trim()) && sharedPreferences.getString("password", null).equals(password.getText().toString().trim())) {
                            editor.putString("status", "1");
                            editor.commit();
                            startActivity(new Intent(UserLogin.this, UserHome.class));
                            finish();
                        } else {
                            showAlert(UserLogin.this, "Invalid Credentials", "The username or password you entered is incorrect. ");
                        }
                    }
                }
            }
        });


    }
    private void init(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.putString("username", "admin");
        editor.putString("password", "admin");
        editor.commit();
    }

    private void showAlert(Context context,String title,String message){
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(
                context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
}
