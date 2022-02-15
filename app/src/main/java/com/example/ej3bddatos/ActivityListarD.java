package com.example.ej3bddatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ConexionBD.Conexion;
import ConexionBD.TransaccionesBD;

public class ActivityListarD extends AppCompatActivity {
    Conexion conexion;
    ListView listapersonas;
    ArrayList<Persona> lista;
    ArrayList<String> ArregloPersonas;
    Button agrego,actualizo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_d);

        Button btnmenu = findViewById(R.id.btnMenu3);


        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListarD.this, MainActivity.class);
                startActivity(intent);
            }
        });


        conexion=new Conexion(this, TransaccionesBD.NameDatabase, null, 1);
        listapersonas=(ListView) findViewById(R.id.listadopersonas);


        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);
        agrego=(Button) findViewById(R.id.btnAgregarDat2);
        actualizo=(Button) findViewById(R.id.btnActualizarDat2);

        agrego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityIngresar.class);
                startActivity(intent);
            }
        });
        actualizo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityActualizarD.class);
                startActivity(intent);
            }

        });
    }
    private void ObtenerListaPersonas()
    {
        SQLiteDatabase db= conexion.getReadableDatabase();
        Persona list_personas = null;
        lista = new ArrayList<Persona>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TransaccionesBD.tablaAgendaPersonas, null);

        while(cursor.moveToNext())
        {
            list_personas=new Persona();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getInt(3));
            list_personas.setCorreo(cursor.getString(4));
            list_personas.setDirrecion(cursor.getString(5));
            lista.add(list_personas);
        }
        cursor.close();

        flist();

    }

    private void flist()
    {
        ArregloPersonas=new ArrayList<String>();

        for (int i=0; i< lista.size(); i++)
        {
            ArregloPersonas.add(lista.get(i).getId() + " | "
                    +lista.get(i).getNombres()+" | "
                    +lista.get(i).getApellidos()+" |" + lista.get(i).getEdad() + " | "
                    +lista.get(i).getCorreo()+" | "
                    +lista.get(i).getDirrecion());
        }


    }
}