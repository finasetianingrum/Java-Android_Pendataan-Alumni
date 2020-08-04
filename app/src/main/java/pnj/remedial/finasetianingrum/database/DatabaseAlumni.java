package pnj.remedial.finasetianingrum.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAlumni extends SQLiteOpenHelper {

    public DatabaseAlumni(Context context) {
        super(context, "db_alumni", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_alumni (nim TEXT PRIMARY KEY, nama TEXT, " +
                "tempat TEXT, tanggal TEXT, alamat TEXT, agama TEXT, " +
                "tlp TEXT, masuk TEXT, lulus TEXT, kerja TEXT, jabatan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}



