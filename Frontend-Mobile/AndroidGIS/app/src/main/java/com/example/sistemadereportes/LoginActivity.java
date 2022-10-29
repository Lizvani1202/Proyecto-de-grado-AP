package com.example.sistemadereportes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String email, contraseña;
    EditText mPassword, mEmail;
    Button btnLogin;
    TextView tvPregunta;
    private String URL = "http://192.168.1.16/SistemaGis/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = contraseña = "";
        mEmail = (EditText) findViewById(R.id.txtEmail);
        mPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvPregunta = (TextView) findViewById(R.id.tvPregunta);
    }

    public void login(View view){
        email = mEmail.getText().toString().trim();

        contraseña = mPassword.getText().toString().trim();

        if(!email.equals("") && !contraseña.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {

                        Intent intent = new Intent(LoginActivity.this, Inicio.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {

                        Toast.makeText(LoginActivity.this, "Correo o Password erroneo", Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_LONG).show();


                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("contraseña", contraseña);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            aviso();
        }


    }
    public void registro(View view){
        Intent intent = new Intent(LoginActivity.this, Registro.class);
        startActivity(intent);
        finish();
    }

    public void aviso (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notificación");
        builder.setMessage("No deben existir campos vacíos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEmail.requestFocus();
            }
        });
        builder.show();
    }
}