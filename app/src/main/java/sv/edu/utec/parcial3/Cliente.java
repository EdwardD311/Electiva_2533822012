package sv.edu.utec.parcial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.utec.parcial3.Helper.AdminSQLite;

public class Cliente extends AppCompatActivity {
    EditText txtIdCliente, txtNombre, txtApellidos, txtDireccion, txtCiudad;
    Button btnAgr, btnAct, btnBus, btnEli;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        txtIdCliente=findViewById(R.id.edtIdCliente);
        txtNombre=findViewById(R.id.edtNombre);
        txtApellidos=findViewById(R.id.edtApellidos);
        txtDireccion=findViewById(R.id.edtDireccion);
        txtCiudad=findViewById(R.id.edtCiudad);
        btnAgr=findViewById(R.id.btnAgregar);
        btnAct=findViewById(R.id.btnActualizar);
        btnBus=findViewById(R.id.btnBuscar);
        btnEli=findViewById(R.id.btnEliminar);

        btnAgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AdminSQLite admin=new AdminSQLite(getApplicationContext(), "RentaCar",null, 2);
               SQLiteDatabase bd = admin.getWritableDatabase();

               String idC=txtIdCliente.getText().toString();
               String Nom=txtNombre.getText().toString();
               String Ape=txtApellidos.getText().toString();
               String Dir=txtDireccion.getText().toString();
               String Ciu=txtCiudad.getText().toString();

                ContentValues infor= new ContentValues();
                infor.put("ID_Cliente", idC);
                infor.put("sNombreCliente", Nom);
                infor.put("sApellidosCliente", Ape);
                infor.put("sCiudadCliente", Dir);
                infor.put("sDireccionCliente", Ciu);

                bd.insert("MD_Clientes", null,infor);
                bd.close();

                Toast.makeText(getApplicationContext(),"Se inserto el Cliente", Toast.LENGTH_LONG).show();

            }
        });

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin=new AdminSQLite(getApplicationContext(), "RentaCar",null, 2);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String idC=txtIdCliente.getText().toString();
                String Nom=txtNombre.getText().toString();
                String Ape=txtApellidos.getText().toString();
                String Dir=txtDireccion.getText().toString();
                String Ciu=txtCiudad.getText().toString();

                ContentValues infor= new ContentValues();
                infor.put("ID_Cliente", idC);
                infor.put("sNombreCliente", Nom);
                infor.put("sApellidosCliente", Ape);
                infor.put("sCiudadCliente", Dir);
                infor.put("sDireccionCliente", Ciu);

                int cat=bd.update("MD_Clientes", infor, "ID_Cliente="+idC, null);
                bd.close();

                if(cat==1){
                    Toast.makeText(getApplicationContext(),"Se modifico el cliente", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se modifico el cliente", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin=new AdminSQLite(getApplicationContext(), "RentaCar",null, 2);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String idC=txtIdCliente.getText().toString();

                Cursor filas=bd.rawQuery("select sNombreCliente, sApellidosCliente, sCiudadCliente, sDireccionCliente from MD_Clientes where ID_Cliente=" + idC, null);

                if(filas.moveToFirst()) {
                    txtNombre.setText(filas.getString(0));
                    txtApellidos.setText(filas.getString(1));
                    txtDireccion.setText(filas.getString(2));
                    txtCiudad.setText(filas.getString(3));
                }
                else {
                    Toast.makeText(getApplicationContext(),"No se encontro el cliente", Toast.LENGTH_LONG).show();
                }

                bd.close();

            }
        });

        btnEli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin=new AdminSQLite(getApplicationContext(), "RentaCar",null, 2);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String idC=txtIdCliente.getText().toString();

                int cat=bd.delete("MD_Clientes", "ID_Cliente="+idC, null);
                bd.close();
                txtIdCliente.setText("");
                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtCiudad.setText("");

                if(cat==1){
                    Toast.makeText(getApplicationContext(),"Se borro el cliente", Toast.LENGTH_LONG).show();
                        }
                else {
                    Toast.makeText(getApplicationContext(),"No se borro el cliente", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}