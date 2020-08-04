package pnj.remedial.finasetianingrum;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.remedial.finasetianingrum.database.DatabaseAlumni;

public class TambahDataActivity extends AppCompatActivity {
    EditText edtNim, edtNama, edtTempat, edtAlamat, edtAgama, edtTlp, edtMasuk, edtLulus, edtKerja, edtJabatan;
    TextView edtTanggal;
    Button actSimpan, actHapus;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        edtNim = findViewById(R.id.edtNim);
        edtNama = findViewById(R.id.edtNama);
        edtTempat = findViewById(R.id.edtTempat);
        edtTanggal = findViewById(R.id.edtTanggal);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtAgama = findViewById(R.id.edtAgama);
        edtTlp = findViewById(R.id.edtTlp);
        edtMasuk = findViewById(R.id.edtMasuk);
        edtLulus = findViewById(R.id.edtLulus);
        edtKerja = findViewById(R.id.edtKerja);
        edtJabatan = findViewById(R.id.edtJabatan);
        actSimpan = findViewById(R.id.actionSimpan);
        actHapus = findViewById(R.id.actionHapus);


        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            actSimpan.setText("UPDATE");
            edtNim.setEnabled(false);
            edtNim.setText(extras.getString("nim", ""));
            edtNama.setText(extras.getString("nama", ""));
            edtTempat.setText(extras.getString("tempat", ""));
            edtTanggal.setText(extras.getString("tanggal", ""));
            edtAlamat.setText(extras.getString("alamat", ""));
            edtAgama.setText(extras.getString("agama", ""));
            edtTlp.setText(extras.getString("tlp", ""));
            edtMasuk.setText(extras.getString("masuk", ""));
            edtLulus.setText(extras.getString("lulus", ""));
            edtKerja.setText(extras.getString("kerja", ""));
            edtJabatan.setText(extras.getString("jabatan", ""));

        }else {
            actHapus.setVisibility(View.GONE);
        }

        edtTanggal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Calendar myCalendar = Calendar.getInstance();
                new DatePickerDialog(TambahDataActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        actSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });

        actHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
            }
        });
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            edtTanggal.setText(simpleDateFormat.format(calendar.getTime().getTime()));
        }
    };



    void simpan() {

        if (edtNim.getText().toString().length() > 0 && edtNama.getText().toString().length() > 0 && edtTempat.getText().toString().length() > 0 && edtTanggal.getText().toString().length() > 0 && edtAlamat.getText().toString().length() > 0 && edtAgama.getText().toString().length() > 0 && edtTlp.getText().toString().length() > 0 && edtMasuk.getText().toString().length() > 0 && edtLulus.getText().toString().length() > 0 && edtKerja.getText().toString().length() > 0 && edtJabatan.getText().toString().length() > 0) {

            database = new DatabaseAlumni(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                contentValues.put("nim", edtNim.getText().toString());
            }

            contentValues.put("nama", edtNama.getText().toString());
            contentValues.put("tempat", edtTempat.getText().toString());
            contentValues.put("tanggal", edtTanggal.getText().toString());
            contentValues.put("alamat", edtAlamat.getText().toString());
            contentValues.put("agama", edtAgama.getText().toString());
            contentValues.put("tlp", edtTlp.getText().toString());
            contentValues.put("masuk", edtMasuk.getText().toString());
            contentValues.put("lulus", edtLulus.getText().toString());
            contentValues.put("kerja", edtKerja.getText().toString());
            contentValues.put("jabatan", edtJabatan.getText().toString());


            if (extras == null) {

                long insert = database.insert("tb_alumni", null, contentValues);
                if (insert != -1) {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {

                long update = database.update("tb_alumni", contentValues, "nim=?", new String[]{"" + edtNim.getText().toString()});
                if (update != -1) {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            database.close();
        }
    }


    void hapus(){
        database = new DatabaseAlumni(this).getWritableDatabase();
        long hapus = database.delete("tb_alumni", "nim=?", new String[]{""+edtNim.getText().toString()});
        if (hapus != -1) {
            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}