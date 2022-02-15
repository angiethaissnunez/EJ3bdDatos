package com.example.ej3bddatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ConexionBD.Conexion;
import ConexionBD.TransaccionesBD;


public class ActivityIngresar extends AppCompatActivity {

    Button agregar,limpieza,mostrarlist;
    EditText nombres;
    EditText apellidos;
    EditText edades;
    EditText correos,direcciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar3);

        agregar=(Button) findViewById(R.id.btnActualizarDat);
        limpieza=(Button)findViewById(R.id.btnLimpiarDatos);
        mostrarlist=(Button) findViewById(R.id.btnListadoDatos);
        nombres=(EditText) findViewById(R.id.txtNombre);
        apellidos=(EditText) findViewById(R.id.txtApellido2);
        edades=(EditText) findViewById(R.id.txtEdad2);
        correos=(EditText) findViewById(R.id.txtEmail2);
        direcciones=(EditText) findViewById(R.id.txtDireccion2);

        Button btnmenu = findViewById(R.id.btnMenu2);


        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIngresar.this, MainActivity.class);
                startActivity(intent);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombres.getText().toString().isEmpty() && !direcciones.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edades.getText().toString().isEmpty() && !correos.getText().toString().isEmpty()){
                    agregarPersonas();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Favor llenar espacios vacios" ,Toast.LENGTH_LONG).show();
                }
            }
        });
        limpieza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clean();
            }
        });
        mostrarlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityListarD.class);
                startActivity(intent);
            }
        });

    }
    void Clean()
    {
        nombres.setText("");
        apellidos.setText("");
        edades.setText("");
        correos.setText("");
        direcciones.setText("");
    }
    private void agregarPersonas(){
        Conexion conexion=new Conexion(this, TransaccionesBD.NameDatabase, null, 1);
        SQLiteDatabase db=conexion.getWritableDatabase();

             ContentValues valores = new ContentValues();
             valores.put(TransaccionesBD.nombres, nombres.getText().toString());
             valores.put(TransaccionesBD.apellidos, apellidos.getText().toString());
             valores.put(TransaccionesBD.edad, edades.getText().toString());
             valores.put(TransaccionesBD.correo, correos.getText().toString());
             valores.put(TransaccionesBD.direccion, direcciones.getText().toString());

        Long resultado = db.insert(TransaccionesBD.tablaAgendaPersonas, TransaccionesBD.id, valores);
        Toast.makeText(getApplicationContext(),"Ingresado : Codigo : " + resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();
        Clean();
    }
}