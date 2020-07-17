package com.tugas.akhirtugas.utils;

import java.util.Date;

public class Function {

    //set icon
    public static String setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = "&#xf00d;";
            } else {
                icon = "&#xf02e;";
            }
        } else {
            switch(id) {
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
            }
        }
        return icon;
    }

    public static String cuaca(int cuaca){
        //https://openweathermap.org/weather-conditions
        //https://openweathermap.org/current
        //https://openweathermap.desk.com/customer/en/portal/articles/1996493-switching-between-temperature-units
        //https://api.openweathermap.org/data/2.5/weather?q=kesesirejo&units=metric&appid=cbfdb21fa1793c10b14b6b6d00fbef03
        //https://androstock.com/tutorials/create-a-weather-app-on-android-android-studio.html
        String hasil = "";
        switch (cuaca){
            //Badai Petir
            case 200 : return hasil =  "Badai petir dengan hujan ringan";
            case 201 : return hasil =  "Badai hujan";
            case 202 : return hasil =  "Badai hujan deras";
            case 210 : return hasil =  "Badai petir ringan";
            case 211 : return hasil =  "Hujan badai";
            case 212 : return hasil =  "Badai petir yang berat";
            case 221 : return hasil =  "Badai badai";
            case 230 : return hasil =  "Hujan badai dengan gerimis ringan";
            case 231 : return hasil =  "Hujan badai dengan gerimis";
            case 233 : return hasil =  "Hujan badai dengan gerimis yang deras";

            //Gerimis
            case 300 : return hasil =  "Gerimis intensitas cahaya";
            case 301 : return hasil =  "Gerimis";
            case 302 : return hasil =  "Gerimis intensitas tinggi";
            case 310 : return hasil =  "Intensitas cahaya hujan gerimis";
            case 311 : return hasil =  "Hujan gerimis";
            case 312 : return hasil =  "Intensitas berat hujan gerimis";
            case 313 : return hasil =  "Mandi hujan dan gerimis";
            case 314 : return hasil =  "Hujan deras dan gerimis";
            case 321 : return hasil =  "Gerimis mandi";

            //hujan
            case 500 : return hasil =  "Gerimis";
            case 501 : return hasil =  "Hujan sedang";
            case 502 : return hasil =  "Hujan intensitas tinggi";
            case 503 : return hasil =  "Hujan yang sangat lebat";
            case 504 : return hasil =  "Hujan yang ekstrim";
            case 511 : return hasil =  "Hujan beku";
            case 520 : return hasil =  "Intensitas cahaya hujan deras";
            case 521 : return hasil =  "Hujan deras";
            case 522 : return hasil =  "Hujan deras intensitas tinggi";
            case 531 : return hasil =  "Hujan shower yang compang-camping";

            //salju
            case 600 : return hasil =  "Salju ringan";
            case 601 : return hasil =  "Salju";
            case 602 : return hasil =  "Salju lebat";
            case 611 : return hasil =  "Hujan bercampur salju";
            case 612 : return hasil =  "Hujan es mandi";
            case 615 : return hasil =  "Hujan ringan dan salju";
            case 616 : return hasil =  "Hujan dan salju";
            case 620 : return hasil =  "Hujan salju ringan";
            case 621 : return hasil =  "Mandi salju";
            case 622 : return hasil =  "Hujan salju deras";

            //Atmosfer
            case 701 : return hasil =  "Kabut";
            case 711 : return hasil =  "Merokok";
            case 721 : return hasil =  "Kabut";
            case 731 : return hasil =  "Pasir, debu berputar";
            case 741 : return hasil =  "Kabut";
            case 751 : return hasil =  "Pasir";
            case 761 : return hasil =  "Debu";
            case 762 : return hasil =  "Abu vulkanik";
            case 771 : return hasil =  "Badai";
            case 781 : return hasil =  "Angin topan";

            //Bersihkan
            case 800 : return hasil =  "Langit cerah";

            //Awan
            case 801 : return hasil =  "Beberapa awan";
            case 802 : return hasil =  "Sebagian berawan";
            case 803 : return hasil =  "Awan yang mendung";
            case 804 : return hasil =  "Awan mendung";

        }

        return hasil;
    }
}