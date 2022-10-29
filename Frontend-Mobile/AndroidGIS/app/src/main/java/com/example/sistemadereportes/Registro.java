package com.example.sistemadereportes;

import androidx.annotation.Nullable;
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

public class Registro extends AppCompatActivity {

    EditText etnombre, etapellido,etcelular, etemail, etpassword, etrecontraseña;
    Button btnRegistro;
    TextView tvStatus;
    private String nombre, apellidos,celular, email, contraseña, recontraseña;
    private String URL = "http://192.168.1.16/SistemaGis/register.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = (EditText) findViewById(R.id.etNombre);
        etapellido = (EditText) findViewById(R.id.etApellido);
        etcelular = (EditText) findViewById(R.id.etCelular);
        etemail = (EditText) findViewById(R.id.etEmail);
        etpassword = (EditText) findViewById(R.id.etContraseña);
        etrecontraseña = (EditText)findViewById(R.id.etReContraseña);
        tvStatus = (TextView)findViewById(R.id.tvStatus);
        btnRegistro = (Button) findViewById(R.id.btnSignIn);
        nombre = apellidos = celular = email = contraseña = recontraseña = "";
    }

    public void save(View view){
        nombre = etnombre.getText().toString().trim();
        apellidos = etapellido.getText().toString().trim();
        celular = etcelular.getText().toString().trim();
        email = etemail.getText().toString().trim();
        contraseña = etpassword.getText().toString().trim();
        recontraseña = etrecontraseña.getText().toString().trim();

        if(!contraseña.equals(recontraseña)){
            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        }else if (!nombre.equals("")&& !apellidos.equals("") && !celular.equals("") && !email.equals("") && !contraseña.equals("") && !recontraseña.equals("")){
            aviso();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    if (response.equals("success")) {
                        tvStatus.setText("Successfully registered");
                        btnRegistro.setClickable(false);

                    } else if (response.equals("failure")) {

                        tvStatus.setText("Algo esta mal");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("nombre",nombre);
                    data.put("apellidos",apellidos);
                    data.put("celular", celular);
                    data.put("email", email);
                    data.put("contraseña", contraseña);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }


    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
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
                etnombre.requestFocus();
            }
        });
        builder.show();
    }
}