package pnj.remedial.finasetianingrum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pnj.remedial.finasetianingrum.DetailBerita;
import pnj.remedial.finasetianingrum.R;
import pnj.remedial.finasetianingrum.adapter.AdapterBerita;
import pnj.remedial.finasetianingrum.model.BeritaModel;

public class FragmentBerita extends Fragment {
    ListView listView;
    AdapterBerita adapterBerita;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_berita, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        adapterBerita = new AdapterBerita(getActivity(),R.layout.item_berita);
        listView.setAdapter(adapterBerita);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeritaModel datas = (BeritaModel) parent.getAdapter().getItem(position);

                Intent intent = new Intent(getActivity(), DetailBerita.class);
                intent.putExtra("img", datas.getImg());
                intent.putExtra("judul", datas.getTitle());
                intent.putExtra("deskripsi", datas.getDeskripsi());

                startActivity(intent);

            }
        });

        buatData();
    }


    void buatData() {
        String[] judul = {
                "Pemerintah larang para warga Indonesia untuk mudik",
                "Terjadi lagi pembegalan di Bekasi"
        };

        String[] deskripsi = {
                "Terhitung sejak bulan April, corona merebak luas se Indonesia. Oleh karena itu pemerintah memberlakukan larangan mudik ke kampung halaman",
                "Aksi pembegalan marak terjadi dimana pun, salah satu nya di Bekasi. Tepat pada pukul 12 malam, RE mengaku bahwa dia dibegal"
        };

        String[] img = {
                "https://asset.kompas.com/crops/CHGc5xv-8x4CDJ4LKw-2yMn5NtI=/0x0:993x662/750x500/data/photo/2020/04/22/5e9f77b10c5b8.jpg",
                "https://pinang.batampos.co.id/wp-content/uploads/2020/01/Begal-Motor-Ilustrasi-Agung-Kurniawan-Jawa-Pos.jpeg"
        };

        ArrayList<BeritaModel> data = new ArrayList<>();

        for (int i=0; i < judul.length; i++) {
            BeritaModel model = new BeritaModel();
            model.setImg(img[i]);
            model.setTitle(judul[i]);
            model.setDeskripsi(deskripsi[i]);
            data.add(model);
        }

        adapterBerita.addAll(data);
        adapterBerita.notifyDataSetChanged();

    }
}