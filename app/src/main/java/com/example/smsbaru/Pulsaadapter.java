package com.example.smsbaru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Pulsaadapter extends ArrayAdapter<String>{
    int groupid;
    ArrayList<String> records;
    Context context;

    public Pulsaadapter(Context context, int vg, int id, ArrayList<String> records){
        super(context, vg, id, records);
        this.context = context;
        groupid=vg;
        this.records = records;
    }

    public View getView(int posisition, View converView, ViewGroup parent){
        TextView no, op, nominal, status, keterangan, waktu, id;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(groupid, parent, false);

        String[] row_items = records.get(posisition).split("__");

        id = (TextView) itemView.findViewById(R.id.id);
        id.setText(row_items[0]);
        no = (TextView) itemView.findViewById(R.id.nomor);
        no.setText(row_items[1]);
        op = (TextView) itemView.findViewById(R.id.op);
        op.setText(row_items[2]);
        nominal = (TextView) itemView.findViewById(R.id.nominal);
        nominal.setText(row_items[3]);
        status = (TextView) itemView.findViewById(R.id.status);
        status.setText(row_items[4]);
        waktu = (TextView) itemView.findViewById(R.id.waktu);
        waktu.setText(row_items[4]);
        keterangan = (TextView) itemView.findViewById(R.id.keterangan);
        keterangan.setText(row_items[5]);

        return itemView;
    }
}
