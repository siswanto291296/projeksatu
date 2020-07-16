package com.tugas.akhirtugas.utils.date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertDate {
    //    String pola3 = "EEEE, MMM dd, yyyy"; // Kam, Jan 27, 2011

    //ambil tahun
    public static String getTahun(String tanggal) {
        //ubah tanggal
        String pola3 = "yyyy"; // 2011

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;

        try {
            date = (Date) formatter.parse(tanggal);
            SimpleDateFormat newFormat = new SimpleDateFormat(pola3, Locale.ENGLISH);

            return newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ambil tahun
    public static String ubahTanggal(String inputDateStr) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); //pola server
        DateFormat outputFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy"); //pola pengubah
//        String inputDateStr="2013-06-24";
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr); //ubah ke date
            return outputFormat.format(date); //ubah ke pola
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ambil tahun
    public static String ubahTanggal2(String inputDateStr) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); //pola server
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy"); //pola pengubah
//        String inputDateStr="2013-06-24";
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr); //ubah ke date
            return outputFormat.format(date); //ubah ke pola
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ambil tahun
    public static String ubahTanggal3(String inputDateStr) {
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy"); //pola server
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); //pola pengubah
//        String inputDateStr="2013-06-24";
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr); //ubah ke date
            return outputFormat.format(date); //ubah ke pola
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //custom tahun
    public static String customTanggal(String inputDateStr, String polaTanggal, String ubah) {
        DateFormat inputFormat = new SimpleDateFormat(polaTanggal); //pola server
        DateFormat outputFormat = new SimpleDateFormat(ubah); //pola pengubah
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr); //ubah ke date
            return outputFormat.format(date); //ubah ke pola
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //ambil bulan
    public static String getBulan(String tanggal) {
        //ubah tanggal
        String pola3 = "MMMM"; // Jan

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;

        try {
            date = (Date) formatter.parse(tanggal);
            SimpleDateFormat newFormat = new SimpleDateFormat(pola3, Locale.ENGLISH);


            return newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("NewApi")
    public static String getBulan2(String tanggal){
        //String str = "2016-11-27";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(tanggal, formatter);

        // access date fields
        int year = date.getYear(); // 2016
        int day = date.getDayOfMonth(); // 27
        Month month = date.getMonth(); // JANUARY
        int bulan = date.getMonthValue();
        int monthAsInt = month.getValue(); // 1

        return ""+month;
    }

    //ammbil hari
    public String getHari(String tanggal) {
        //ubah tanggal
        String pola3 = "dd"; // 27

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;

        try {
            date = (Date) formatter.parse(tanggal);
//            SimpleDateFormat newFormat = new SimpleDateFormat(pola3, new Locale("id"));
            SimpleDateFormat newFormat = new SimpleDateFormat(pola3);

            return newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //tanggal hari ini
    public static String tglHariIni() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(now);
    }

    public static String funDate(Context context) {
        final String[] tanggal = {null};

        final Calendar myCalendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String formatTanggal = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                tanggal[0] = sdf.format(myCalendar.getTime());
            }
        },
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        return tanggal[0];
    }

    public static String getThreeMonth(String tgl) {
        String tanggal = null;
        try {
            //Date getTime (): Mengembalikan objek Tanggal yang mewakili nilai waktu Kalender ini
            //membatalkan setTime (Tanggal aDate): Menetapkan waktu Kalender ini dengan contoh Tanggal yang ditentukan
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //String strDate = "2014-10-28"; //tgl awal
            Date date = sdf.parse(tgl);
            Calendar calendar = Calendar.getInstance(); //buat object
            calendar.setTime(date); //masukan tgl ke calender
            //Menambahkan atau mengurangi jumlah waktu yang ditentukan ke
            //bidang kalender yang diberikan, berdasarkan pada aturan kalender.
            calendar.add(Calendar.DATE, 82);
            Date yesterday = calendar.getTime();

            //System.out.println("kemarin " +yesterday);
            //System.out.println("hari ini " +date);

            //System.out.println("Kemarin = " +sdf.format(yesterday)); //Kemarin = 2014-10-27
            //System.out.println("Hari ini = " +sdf.format(date)); //Hari ini = 2014-10-28

            tanggal = sdf.format(yesterday); //2014-10-27
            return sdf.format(yesterday);
        } catch (ParseException ex) {
            Logger.getLogger(ConvertDate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tanggal;
    }

    public static String getSixMonth(String tgl) {
        String tanggal = null;
        try {
            //Date getTime (): Mengembalikan objek Tanggal yang mewakili nilai waktu Kalender ini
            //membatalkan setTime (Tanggal aDate): Menetapkan waktu Kalender ini dengan contoh Tanggal yang ditentukan
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //String strDate = "2014-10-28"; //tgl awal
            Date date = sdf.parse(tgl);
            Calendar calendar = Calendar.getInstance(); //buat object
            calendar.setTime(date); //masukan tgl ke calender
            //Menambahkan atau mengurangi jumlah waktu yang ditentukan ke
            //bidang kalender yang diberikan, berdasarkan pada aturan kalender.
            // 1 bulan = 30 hari, 6 x 30 = 180 hari
            //minus 2 karena utk notif nantinya
            calendar.add(Calendar.DATE, 178);
            Date yesterday = calendar.getTime();

            //System.out.println("kemarin " +yesterday);
            //System.out.println("hari ini " +date);

            //System.out.println("Kemarin = " +sdf.format(yesterday)); //Kemarin = 2014-10-27
            //System.out.println("Hari ini = " +sdf.format(date)); //Hari ini = 2014-10-28

            tanggal = sdf.format(yesterday); //2014-10-27
            return sdf.format(yesterday);
        } catch (ParseException ex) {
            Logger.getLogger(ConvertDate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tanggal;
    }

    public static Date changeStringToDate(TextView date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = null; //2014-11-28
        try {
            date3 = sdf.parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date3;
    }

    public static Date changeStringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = null; //2014-11-28
        try {
            date3 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date3;
    }
}