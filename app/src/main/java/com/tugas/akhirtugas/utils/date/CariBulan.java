package com.tugas.akhirtugas.utils.date;

public class CariBulan {

    //cari nama bulan convert dari bil bulat ke string bulan
    public static String cariNamaBulan(String bulan) {
        String[] namaBulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

        //karena index array dari 0 maka mins -1
        int cari = Integer.parseInt(bulan);
        return namaBulan[cari - 1]; // bulan[8-1] = agustus
    }

    //cari nama bulan convert dari bil bulat ke string bulan
    public static String cariNomerBulan(String bulan) {
        String hasil = "";
        String[] namaBulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        String[] nomerBulan = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        for (int i = 0; i < nomerBulan.length; i++) {
            //jika nama bulan sama dengan bulan
            //maka
            if (namaBulan[i].equalsIgnoreCase(bulan)){
                //ambil nomer bulan
                hasil = nomerBulan[i];
            }
        }
        return hasil;
    }
}
