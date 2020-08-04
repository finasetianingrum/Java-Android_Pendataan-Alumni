package pnj.remedial.finasetianingrum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pnj.remedial.finasetianingrum.R;
import pnj.remedial.finasetianingrum.model.AlumniModel;

public class AdapterAlumni extends ArrayAdapter<AlumniModel> {

    Context context;
    int resource;

    public AdapterAlumni(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Holder holder;
            if(convertView==null){
                holder = new Holder();
                convertView = LayoutInflater.from(context).inflate(resource, parent, false);


                holder.txtNim = convertView.findViewById(R.id.txtNim);
                holder.txtNama = convertView.findViewById(R.id.txtNama);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }


            holder.txtNim.setText("Nim : "+ getItem(position).getNim());
            holder.txtNama.setText("Nama : "+ getItem(position).getNama());

            return convertView;

        }

        class Holder {
            TextView txtNim, txtNama;
        }
}
