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
import com.app.theleague78.entity.RegisterResponse;
import com.app.theleague78.util.ApiClient;
import com.app.theleague78.util.ApiInterface;
import com.app.theleague78.util.LoadingBox;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password, phno;
    String semail, spassword, sname, sphno;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        phno=(EditText)findViewById(R.id.phoneno);

        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        TextView login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Button register=(Button)findViewById(R.id.signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname=name.getText().toString();
                semail=email.getText().toString();
                spassword=password.getText().toString();
                sphno=phno.getText().toString();

                if(sname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Name required",Toast.LENGTH_LONG).show();

                }
                else if(semail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email required",Toast.LENGTH_LONG).show();

                }
                else if(spassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password required",Toast.LENGTH_LONG).show();

                }
                else if(sphno.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Pone No required",Toast.LENGTH_LONG).show();

                }
                else {
                    insertCustomer();
                }
            }
        });
        
    }

    private void insertCustomer() {

        LoadingBox.showLoadingDialog(RegisterActivity.this);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customer_name",sname);
        jsonObject.addProperty("email",semail);
        jsonObject.addProperty("password", spassword);
        jsonObject.addProperty("phone_number", sphno);
        jsonObject.addProperty("role", "C");

        Call<RegisterResponse> call = apiService.customerSignUp(jsonObject);

        call.enqueue(new retrofit2.Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                LoadingBox.dismissLoadingDialog();
                if (response.isSuccessful()){
                    RegisterResponse registerResponse = response.body();

                    if (registerResponse.getStatusCode()==1&&registerResponse.getStatus().equals("SUCCESS")) {

                        Toast.makeText(getApplicationContext(),"Registration Successful!", Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(RegisterActivity.this,
                                MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),registerResponse.getMessage(),Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                LoadingBox.showLoadingDialog(RegisterActivity.this);

            }

        });

    }
    
}
