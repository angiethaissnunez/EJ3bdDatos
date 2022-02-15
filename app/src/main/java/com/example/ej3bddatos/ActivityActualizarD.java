package com.example.ej3bddatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ConexionBD.Conexion;
import ConexionBD.TransaccionesBD;

public class ActivityActualizarD extends AppCompatActivity {
    Conexion conexion;
    EditText id;
    EditText nombres;
    EditText edad;
    EditText apellidos;
    EditText correo;
    EditText direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_d);

        conexion = new Conexion(this, TransaccionesBD.NameDatabase, null, 1);

        
        Button consulta = (Button) findViewById(R.id.btnBuscarDatos);
        Button eliminar = (Button) findViewById(R.id.btnEliminarDat);
        Button actualizar = (Button) findViewById(R.id.btnActualizarDat);

        id = (EditText) findViewById(R.id.txtID);
        nombres = (EditText) findViewById(R.id.txtNombre);
        apellidos = (EditText) findViewById(R.id.txtApellido2);
        edad = (EditText) findViewById(R.id.txtEdad2);
        correo = (EditText) findViewById(R.id.txtEmail2);
        direccion = (EditText) findViewById(R.id.txtDireccion2);

        Button btnmenu = findViewById(R.id.btnMenu);


        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityActualizarD.this, MainActivity.class);
                startActivity(intent);
            }
        });


        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombres.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edad.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty()){
                    Actualizar();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Favor llena los espacios vacios" ,Toast.LENGTH_LONG).show();

                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
    }


    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String[] params = {id.getText().toString()};
        String[] fields = {TransaccionesBD.nombres,
                TransaccionesBD.apellidos,
                TransaccionesBD.correo,
                TransaccionesBD.edad, TransaccionesBD.direccion};
        String wherecond = TransaccionesBD.id + "=?";

        try {
            Cursor cdata = db.query(TransaccionesBD.tablaAgendaPersonas, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();
            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            correo.setText(cdata.getString(2));
            edad.setText(cdata.getString(3));
            direccion.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(), " Registro encontrado con exito", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Clean();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String[] params = {id.getText().toString()};
        String wherecond = TransaccionesBD.id + "=?";
        db.delete(TransaccionesBD.tablaAgendaPersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "Regirtro eliminado", Toast.LENGTH_LONG).show();
        Clean();
    }

    private void Actualizar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};
        ContentValues valores = new ContentValues();
            valores.put(TransaccionesBD.nombres, nombres.getText().toString());
            valores.put(TransaccionesBD.apellidos, apellidos.getText().toString());
            valores.put(TransaccionesBD.edad, edad.getText().toString());
            valores.put(TransaccionesBD.correo, correo.getText().toString());
            valores.put(TransaccionesBD.direccion,direccion.getText().toString());
        db.update(TransaccionesBD.tablaAgendaPersonas, valores, TransaccionesBD.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Registro actualizado", Toast.LENGTH_LONG).show();
        Clean();
    }

    void Clean(){
        id.setText("");
        nombres.setText("");
        edad.setText("");
        apellidos.setText("");
        correo.setText("");
        direccion.setText("");


    }
}