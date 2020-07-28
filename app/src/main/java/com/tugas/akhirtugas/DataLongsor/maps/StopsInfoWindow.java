package com.tugas.akhirtugas.DataLongsor.maps;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;

import java.util.HashMap;

import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal2;

public class StopsInfoWindow implements GoogleMap.InfoWindowAdapter {
    private HashMap<Marker, DataLongsorItem> stopsMarkersInfo;
    private View view;
    private Activity context;

    public StopsInfoWindow(HashMap<Marker, DataLongsorItem> stopsMarkersInfo, Activity context) {
        this.stopsMarkersInfo = stopsMarkersInfo;
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
       return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        DataLongsorItem stop = stopsMarkersInfo.get(marker);
        if (stop != null) {
            view = context.getLayoutInflater().inflate(R.layout.pop_maps, null);

            TextView name = view.findViewById(R.id.txt_judul);
            TextView korban = view.findViewById(R.id.txt_korban);
            TextView tgl = view.findViewById(R.id.txt_tgl);
            TextView waktu = view.findViewById(R.id.txt_waktu);
            TextView alamat = view.findViewById(R.id.txt_alamat);

            name.setText(""+stop.getJenisBencana());
            korban.setText(""+stop.getKorban()+" Jiwa");
            tgl.setText(""+ubahTanggal2(stop.getTanggal()));
            waktu.setText(""+stop.getWaktu());
            alamat.setText(""+stop.getLokasi());
        }
        return view;
    }
}
