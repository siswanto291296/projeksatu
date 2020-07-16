package com.tugas.akhirtugas.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.lang.Math.ceil;

public class Kurs {
    public static String kursRp(int rupiah){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(ceil(rupiah));
    }

    //presentase 1,5%
    public static double bunga (double pinjaman){
        return (pinjaman / 100) * 1.5;
    }

    public static double angsuran(double pinjaman, double angsuran){
        return pinjaman / angsuran;
    }

    //pembulatan ke tas
    public static double roundingUp (double total){
        return total + (1000 - (total % 1000));
    }

    public static double total(double pinjaman, double angsuran){
        return roundingUp(bunga(pinjaman) + angsuran(pinjaman, angsuran));
    }
}