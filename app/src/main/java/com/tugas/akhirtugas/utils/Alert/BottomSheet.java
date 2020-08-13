package com.tugas.akhirtugas.utils.Alert;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;
import com.tugas.akhirtugas.session.Session;

public class BottomSheet {
    private OnClick onClick;
    private Activity activity;

    public BottomSheet(OnClick onClick, Activity activity) {
        this.onClick = onClick;
        this.activity = activity;
    }

    public void openPopUp(Session session, DataLongsorItem dataLongsorItem) {
        View view = activity.getLayoutInflater().inflate(R.layout.pop_detail, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(activity);
        dialog.setOnShowListener(dialog1 -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog1;

            FrameLayout bottomSheet = d.findViewById(R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        //inisialisasi
        Button exit, ubah;
        TextView detailKejadian, detailPenanganan;
        exit = view.findViewById(R.id.tutup);
        ubah = view.findViewById(R.id.edit);
        detailKejadian = view.findViewById(R.id.detail_kejadian);
        detailPenanganan = view.findViewById(R.id.detail_penanggulangan);

        //init
        detailKejadian.setText(""+dataLongsorItem.getKejadian());
        detailPenanganan.setText(""+dataLongsorItem.getPenanggulangan());

        dialog.setContentView(view);
        dialog.show();

        exit.setOnClickListener(v -> {
            dialog.dismiss();
        });

        if (session.getSPSudahLogin2()) {
            ubah.setVisibility(View.VISIBLE);
        }

        ubah.setOnClickListener(v -> {
            this.onClick.clicked(dialog, dataLongsorItem);
        });
    }
}
