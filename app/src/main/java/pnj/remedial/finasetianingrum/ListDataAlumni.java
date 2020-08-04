package pnj.remedial.finasetianingrum;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pnj.remedial.finasetianingrum.adapter.AdapterAlumni;
import pnj.remedial.finasetianingrum.database.DatabaseAlumni;
import pnj.remedial.finasetianingrum.model.AlumniModel;

public class ListDataAlumni extends AppCompatActivity {

    ListView listView;
    AdapterAlumni adapterAlumni;
    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_alumni);
        listView = findViewById(R.id.listView);
        adapterAlumni = new AdapterAlumni(this, R.layout.item_alumni);
        listView.setAdapter(adapterAlumni);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlumniModel model = (AlumniModel) parent.getAdapter().getItem(position);
                Intent intent = new Intent(ListDataAlumni.this, TambahDataActivity.class);
                intent.putExtra("nim", model.getNim());
                intent.putExtra("nama", model.getNama());
                intent.putExtra("tempat", model.getTempat());
                intent.putExtra("tanggal", model.getTanggal());
                intent.putExtra("alamat", model.getAlamat());
                intent.putExtra("agama", model.getAgama());
                intent.putExtra("tlp", model.getTlp());
                intent.putExtra("masuk", model.getMasuk());
                intent.putExtra("lulus", model.getLulus());
                intent.putExtra("kerja", model.getKerja());
                intent.putExtra("jabatan", model.getJabatan());
                startActivity(intent);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    void getData(){
        database = new DatabaseAlumni(this).getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM tb_alumni", null);
        ArrayList<AlumniModel> data = new ArrayList<>();
        adapterAlumni.clear();
        data.clear();

        if (cursor.moveToFirst()) {
            do {
                AlumniModel model = new AlumniModel();
                model.setNim(cursor.getString(0));
                model.setNama(cursor.getString(1));
                model.setTempat(cursor.getString(2));
                model.setTanggal(cursor.getString(3));
                model.setAlamat(cursor.getString(4));
                model.setAgama(cursor.getString(5));
                model.setTlp(cursor.getString(6));
                model.setMasuk(cursor.getString(7));
                model.setLulus(cursor.getString(8));
                model.setKerja(cursor.getString(9));
                model.setJabatan(cursor.getString(10));

                data.add(model);

            } while (cursor.moveToNext());
            cursor.close();
            database.close();

            adapterAlumni.addAll(data);
        }
    }
}
