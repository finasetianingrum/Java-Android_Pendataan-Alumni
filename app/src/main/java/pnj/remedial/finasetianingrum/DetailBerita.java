package pnj.remedial.finasetianingrum;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailBerita extends AppCompatActivity {
    ImageView img;
    TextView txtJudul, txtDeskripsi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        img = findViewById(R.id.img);
        txtJudul = findViewById(R.id.txtJudul);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            getSupportActionBar().setTitle(extras.getString("judul", ""));
            txtJudul.setText(extras.getString("judul", ""));
            txtDeskripsi.setText(extras.getString("deskripsi", ""));

            Picasso.get().load(extras.getString("img", "")).into(img);
            }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

    }
        return super.onOptionsItemSelected(item);
}
}
