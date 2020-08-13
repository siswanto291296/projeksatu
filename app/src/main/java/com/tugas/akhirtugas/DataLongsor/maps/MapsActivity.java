package com.tugas.akhirtugas.DataLongsor.maps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tugas.akhirtugas.Berita.detail.DetailBerita;
import com.tugas.akhirtugas.DataLongsor.form.FormDataLongsor;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.kecamatan.KecamatanItem;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;
import com.tugas.akhirtugas.model.longsor.ResponseLongsor;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;
import com.tugas.akhirtugas.session.Session;
import com.tugas.akhirtugas.utils.Alert.BottomSheet;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.utils.Contans.DATA;

//https://developers.google.com/maps/documentation/utilities/polylineutility
//https://www.doogal.co.uk/polylines.php
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, com.tugas.akhirtugas.utils.Alert.OnClick {
    @BindView(R.id.btn_tambah)
    Button btnTambah;
    private GoogleMap mMap;
    KecamatanItem data;
    String idKec;
    Session sharedLogin;
    StopsInfoWindow infoWindow;
    HashMap<Marker, DataLongsorItem> stopsMarkersInfo = new HashMap<>();
    BottomSheet bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bottomSheet = new BottomSheet(this::clicked, this);

        infoWindow = new StopsInfoWindow(stopsMarkersInfo, MapsActivity.this);
        UI();
    }

    private void UI() {
        sharedLogin = new Session(this);
        data = getIntent().getParcelableExtra(DATA);
        if (sharedLogin.getSPSudahLogin2()) btnTambah.setVisibility(View.VISIBLE);

        if (data != null) {
            idKec = data.getIdKec();
        }

        if (getIntent().getStringExtra("idKec") != null) {
            idKec = getIntent().getStringExtra("idKec");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getDataLongsor(idKec);

        mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new  LatLng(-6.85961170943853, 109.66254344970703),
                        new  LatLng(-6.852283051826313, 109.66597667724609),
                        new  LatLng(-6.8485334625033305, 109.65911022216797),
                        new  LatLng(-6.849726516849085, 109.65893856079101),
                        new  LatLng(-6.846999531103292, 109.63902584106445),
                        new  LatLng(-6.840352438068915, 109.62426296264648),
                        new  LatLng(-6.843249900257229, 109.62323299438476),
                        new  LatLng(-6.842500178154427, 109.62108722717285),
                        new  LatLng(-6.84113666658553, 109.62194553405762),
                        new  LatLng(-6.84113666658553, 109.59834209472656),
                        new  LatLng(-6.841903642322088, 109.59868541748047),
                        new  LatLng(-6.842670616826781, 109.59980121643066),
                        new  LatLng(-6.843437590099452, 109.59945789367676),
                        new  LatLng(-6.844204562139989, 109.59731212646484),
                        new  LatLng(-6.845568064947081, 109.59791294128418),
                        new  LatLng(-6.8465906894960975, 109.59473720581055),
                        new  LatLng(-6.848465495477347, 109.5949088671875),
                        new  LatLng(-6.848721150267635, 109.591218147583),
                        new  LatLng(-6.851448126171276, 109.59207645446777),
                        new  LatLng(-6.857413331584218, 109.58812824279785),
                        new  LatLng(-6.8674687944676185, 109.5853816607666),
                        new  LatLng(-6.871047806256488, 109.58606830627441),
                        new  LatLng(-6.874797218283647, 109.58400836975098),
                        new  LatLng(-6.881784679931039, 109.58126178771973),
                        new  LatLng(-6.884170618895273, 109.5795451739502),
                        new  LatLng(-6.889964992145434, 109.57559696228027),
                        new  LatLng(-6.890135413816464, 109.57868686706543),
                        new  LatLng(-6.897804325592362, 109.5689021685791),
                        new  LatLng(-6.907006855801576, 109.56787220031738),
                        new  LatLng(-6.914164255525603, 109.55722919494629),
                        new  LatLng(-6.927576234286346, 109.546271323616),
                        new  LatLng(-6.936437374631908, 109.53734493201443),
                        new  LatLng(-6.940867882297211, 109.5188055033035),
                        new  LatLng(-6.948365569474611, 109.51502895301053),
                        new  LatLng(-6.954840748544782, 109.51056575720975),
                        new  LatLng(-6.9630197945051835, 109.50335597937772),
                        new  LatLng(-6.968131625830812, 109.49820613806912),
                        new  LatLng(-6.976651220820098, 109.49820613806912),
                        new  LatLng(-6.98380756081988, 109.49854946082303),
                        new  LatLng(-6.98891916521905, 109.4971761698074),
                        new  LatLng(-7.000164498061908, 109.49648952429959),
                        new  LatLng(-7.008001994051774, 109.49442958777615),
                        new  LatLng(-7.015498606032449, 109.49339961951443),
                        new  LatLng(-7.023335844044777, 109.48996639197537),
                        new  LatLng(-7.029469242546907, 109.48859310095975),
                        new  LatLng(-7.041054329886918, 109.48859310095975),
                        new  LatLng(-7.047187494321087, 109.48893642371365),
                        new  LatLng(-7.058431418281537, 109.49065303748318),
                        new  LatLng(-7.064905068775906, 109.48996639197537),
                        new  LatLng(-7.078874216139841, 109.49374294226834),
                        new  LatLng(-7.084325476040344, 109.49580287879178),
                        new  LatLng(-7.093524330938312, 109.5026693338699),
                        new  LatLng(-7.101019558316578, 109.50129604285428),
                        new  LatLng(-7.109536713885791, 109.49339961951443),
                        new  LatLng(-7.118735064528633, 109.48790645545193),
                        new  LatLng(-7.134065238992535, 109.48962306922147),
                        new  LatLng(-7.141219144790757, 109.49202632849881),
                        new  LatLng(-7.148372938680297, 109.49374294226834),
                        new  LatLng(-7.161317614231242, 109.48893642371365),
                        new  LatLng(-7.165405330203149, 109.48481655066678),
                        new  LatLng(-7.173921287367168, 109.4861898416824),
                        new  LatLng(-7.182777713817141, 109.49614620154568),
                        new  LatLng(-7.189590232163589, 109.5078191751785),
                        new  LatLng(-7.194699553874582, 109.51811885779568),
                        new  LatLng(-7.198446353244334, 109.52086543982693),
                        new  LatLng(-7.204918024711972, 109.51743221228787),
                        new  LatLng(-7.21343324110585, 109.51399898474881),
                        new  LatLng(-7.219223496775456, 109.51159572547147),
                        new  LatLng(-7.222970093303566, 109.51211070960233),
                        new  LatLng(-7.22552457313535, 109.51554393714139),
                        new  LatLng(-7.227397849168835, 109.52086543982693),
                        new  LatLng(-7.232847335278406, 109.51897716468045),
                        new  LatLng(-7.235742348022479, 109.51811885779568),
                        new  LatLng(-7.233017630659673, 109.524298667366),
                        new  LatLng(-7.23029289685181, 109.52859020178983),
                        new  LatLng(-7.232439377343497, 109.53930111444436),
                        new  LatLng(-7.225968099424363, 109.55887051141701),
                        new  LatLng(-7.225286906882683, 109.57191677606545),
                        new  LatLng(-7.225286906882683, 109.59835262811623),
                        new  LatLng(-7.2133658712839175, 109.60933895624123),
                        new  LatLng(-7.195654038861672, 109.61963863885842),
                        new  LatLng(-7.189522858799155, 109.6292516759678),
                        new  LatLng(-7.183391595973568, 109.64435787713967),
                        new  LatLng(-7.183050967835727, 109.64916439569436),
                        new  LatLng(-7.186457237727426, 109.65946407831154),
                        new  LatLng(-7.188500987407127, 109.67182369745217),
                        new  LatLng(-7.186457237727426, 109.68727322137795),
                        new  LatLng(-7.182710339442678, 109.69482632196389),
                        new  LatLng(-7.186956750727781, 109.69689393565967),
                        new  LatLng(-7.203647085518833, 109.70444703624561),
                        new  LatLng(-7.2169307904233015, 109.73225617931202),
                        new  LatLng(-7.217952597733578, 109.74564576671436),
                        new  LatLng(-7.209096857739438, 109.76384187267139),
                        new  LatLng(-7.194450446352795, 109.77414155528858),
                        new  LatLng(-7.181847341933672, 109.78238130138233),
                        new  LatLng(-7.186956750727781, 109.79645753429249),
                        new  LatLng(-7.186616125261848, 109.80435395763233),
                        new  LatLng(-7.179122300431544, 109.81190705821827),
                        new  LatLng(-7.174012803618562, 109.81637025401905),
                        new  LatLng(-7.1675406919938345, 109.80332398937061),
                        new  LatLng(-7.156980733584014, 109.78547120616749),
                        new  LatLng(-7.139948027775528, 109.77620149181202),
                        new  LatLng(-7.118721190997521, 109.79215291152153),
                        new  LatLng(-7.101687062523288, 109.78837636122856),
                        new  LatLng(-7.086419403950487, 109.78694269924618),
                        new  LatLng(-7.076709346514124, 109.78496859341122),
                        new  LatLng(-7.066317655244057, 109.77947542934872),
                        new  LatLng(-7.056444603781951, 109.77295968199162),
                        new  LatLng(-7.045711727153698, 109.7741613116303),
                        new  LatLng(-7.033956386690453, 109.77021309996037),
                        new  LatLng(-7.016467794162541, 109.76025674009709),
                        new  LatLng(-7.006245132825863, 109.7499570574799),
                        new  LatLng(-6.986821459002179, 109.72970101499943),
                        new  LatLng(-6.980346721618271, 109.71493813658147),
                        new  LatLng(-6.961944347572507, 109.69571206236272),
                        new  LatLng(-6.948312493951903, 109.68266579771428),
                        new  LatLng(-6.941798520966879, 109.67933652412395),
                        new  LatLng(-6.936345593314475, 109.6762466193388),
                        new  LatLng(-6.926462001054615, 109.67693326484661),
                        new  LatLng(-6.923053817753026, 109.67178342353802),
                        new  LatLng(-6.920668074794519, 109.66371533882122),
                        new  LatLng(-6.919475198793306, 109.65839383613567),
                        new  LatLng(-6.918282319778123, 109.65324399482708),
                        new  LatLng(-6.91640778952319, 109.64466092597942),
                        new  LatLng(-6.911295396476378, 109.63951108467083),
                        new  LatLng(-6.9015816973845725, 109.64088437568645),
                        new  LatLng(-6.892038219942223, 109.64311597358684),
                        new  LatLng(-6.8879480993464774, 109.64637753974895),
                        new  LatLng(-6.885050909249694, 109.64380261909466),
                        new  LatLng(-6.879387321706768, 109.64830003133687),
                        new  LatLng(-6.875126665084957, 109.6525915657607),
                        new  LatLng(-6.870013826714435, 109.64984498372945),
                        new  LatLng(-6.8664348071452395, 109.65653977743062),
                        new  LatLng(-6.867116527234443, 109.6610029732314),
                        new  LatLng(-6.867116527234443, 109.66477952352437),
                        new  LatLng(-6.864560071863853, 109.66821275106344),
                        new  LatLng(-6.864560071863853, 109.66563783040914),
                        new  LatLng(-6.863196623384389, 109.6610029732314),
                        new  LatLng(-6.859497817172855, 109.6626284882615)
                )
        ).setColor(getResources().getColor(R.color.colorPrimaryDark));

        //jika dia sebagao admin
        //if (sharedLogin.getSPSudahLogin2()) {
            //onclik info windows
            mMap.setOnInfoWindowClickListener(marker -> {
                DataLongsorItem data = stopsMarkersInfo.get(marker);
                bottomSheet.openPopUp(sharedLogin, data);
                /*startActivity(new Intent(this, FormDataLongsor.class)
                        .putExtra(DATA, data));*/

            });
        //}
    }

    private void getDataLongsor(String id) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseLongsor> loginCall = apiService.getByIdDataLongsor(id);
        loginCall.enqueue(new Callback<ResponseLongsor>() {
            @Override
            public void onResponse(Call<ResponseLongsor> call, Response<ResponseLongsor> response) {
                ResponseLongsor ress = response.body();
                try {
                    if (ress.isResponse()) {
                        //listMarker = ress.getDataLongsor();
                        initMarker(ress.getDataLongsor());
                    } else {
                        Toast.makeText(MapsActivity.this, "Data Kosong!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MapsActivity.this, "error = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLongsor> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMarker(List<DataLongsorItem> listData) {
        for (int i = 0; i < listData.size(); i++) {
            //set latlng nya
            LatLng location = new
                    LatLng(Double.parseDouble(listData.get(i).getLatitude()),
                    Double.parseDouble(listData.get(i).getLongitude()));
           /* //add polyline
            mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(location));*/
            //tambahkan markernya
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title(listData.get(i).getJenisBencana())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.avalanche))
            );
            //set latlng index ke 0
            LatLng latLng = new
                    LatLng(Double.parseDouble(listData.get(0).getLatitude()),
                    Double.parseDouble(listData.get(0).getLongitude()));
            //lalu arahkan zooming ke marker index ke 0
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new
                    LatLng(latLng.latitude, latLng.longitude), 9.9f)); //17.0f
            //hashmap
            stopsMarkersInfo.put(marker, listData.get(i));
        }

        mMap.setInfoWindowAdapter(infoWindow); // passed the HashMap
    }

    @OnClick(R.id.btn_tambah)
    public void onViewClicked() {
        startActivity(new Intent(MapsActivity.this, FormDataLongsor.class).putExtra("idKec", idKec));
    }

    @Override
    public void clicked(Dialog dialog, DataLongsorItem dataLongsorItem) {
        startActivity(new Intent(this, FormDataLongsor.class)
                .putExtra(DATA, dataLongsorItem));
        dialog.dismiss();
    }
}