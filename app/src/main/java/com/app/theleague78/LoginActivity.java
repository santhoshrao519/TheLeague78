package com.app.theleague78;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.theleague78.entity.LoginResponse;
import com.app.theleague78.util.ApiClient;
import com.app.theleague78.util.ApiInterface;
import com.app.theleague78.util.LoadingBox;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    String semail, spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        TextView register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semail=email.getText().toString();
                spassword=password.getText().toString();
                if(semail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email required",Toast.LENGTH_LONG).show();

                }
                else if(spassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password required",Toast.LENGTH_LONG).show();

                }
                else {
                    validateCustomer();
                }

            }
        });


    }

    private void validateCustomer() {

        LoadingBox.showLoadingDialog(LoginActivity.this);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email",semail);
        jsonObject.addProperty("password", spassword);

        Call<LoginResponse> call = apiService.customerLogin(jsonObject);

        call.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoadingBox.dismissLoadingDialog();
                if (response.isSuccessful()){
                    LoginResponse signInResponse = response.body();

                    if (signInResponse.getStatusCode()==1&&signInResponse.getStatus().equals("SUCCESS")) {
                        Toast.makeText(getApplicationContext(),"Login Successful!", Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),signInResponse.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    LoadingBox.dismissLoadingDialog();
                    ResponseBody errorBody = response.errorBody();
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplicationContext(), jObjError.getString("errors").toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                LoadingBox.showLoadingDialog(LoginActivity.this);

            }

        });

    }
}
