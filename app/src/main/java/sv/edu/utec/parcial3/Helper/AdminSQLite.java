package sv.edu.utec.parcial3.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite extends SQLiteOpenHelper {
    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //@Override
    //public void onCreate() {
       // onCreate(null);
    //}

    @Override
    public void onCreate(SQLiteDatabase RentaCar) {
        RentaCar.execSQL("CREATE TABLE MD_Clientes(ID_Cliente text primary key, sNombreCliente text, sApellidosCliente text, " +
                "sDireccionCliente text, sCiudadCliente text )");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
