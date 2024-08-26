package com.example.registerloginexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.security.Permission;
import java.security.Permissions;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RentalActivity extends AppCompatActivity implements OnMapReadyCallback, Overlay.OnClickListener {
    private NaverMap naverMap;


    Marker marker;
    InfoWindow infoWindow;
    FusedLocationProviderClient fLC;
    LocationCallback callback;
    public Button btnqr;
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

//        mapView.getMapAsync(this);
//        locationSource =
//                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(RentalActivity.this);
        btnqr = (Button)findViewById(R.id.btnqr);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        btnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(RentalActivity.this,qr_create.class);
                intent3.putExtra("id",id);
                startActivity(intent3);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        this.naverMap = naverMap;

        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(37.500936916629, 126.86674390514),  // 위치 지정
                9                           // 줌 레벨
        );
        naverMap.setCameraPosition(cameraPosition);
        naverMap.setMapType(NaverMap.MapType.Basic);
        naverMap.moveCamera(CameraUpdate.zoomTo(15));
        fLC = LocationServices.getFusedLocationProviderClient(this);
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if(locationResult == null){
                    Toast.makeText(getApplicationContext(),"위치정보 없음",Toast.LENGTH_LONG).show();
                    return;
                }
                List<Location> locations = locationResult.getLocations();
                Location location = locations.get(0);
                if(marker == null)
                    marker = new Marker();
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                marker.setPosition(latLng);
                marker.setMap(naverMap);
                // naverMap.moveCamera(CameraUpdate.scrollTo(latLng));
                marker.setCaptionText("현재위치");

            }
        };
        Marker marker2 = new Marker();
        marker2.setPosition(new LatLng(37.500936,126.866743));
        marker2.setMap(naverMap);
        marker2.setCaptionText("대여소 1번");
        marker2.setOnClickListener(this);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return;
        }
        fLC.requestLocationUpdates(locationRequest, callback, Looper.getMainLooper());
       /* Geocoder geocoder = new Geocoder(this);
        List<Address> locations = null;
        try {
            locations = geocoder.getFromLocationName("동양미래대학교",1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(locations==null||locations.size()==0){
            Toast.makeText(getApplicationContext(),"위치정보 없음",Toast.LENGTH_LONG).show();
            return;
        }
        Address ad = locations.get(0);
        double lat = ad.getLatitude();
        double lng = ad.getLongitude();
        LatLng latLng = new LatLng(lat,lng);
        marker = new Marker();

        marker.setPosition(latLng);
        marker.setMap(naverMap);
        marker.setCaptionText("동양미래대학교");
        marker.setCaptionColor(Color.BLUE);

        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "우리학교";
            }
        });
        infoWindow.open(marker);
        naverMap.moveCamera(CameraUpdate.scrollTo(latLng));*/
    }
    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode != 100) //권한요청할 때 100으로 요청함
            return;
        if(grantResults.length >= 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED //둘다 허용을 했는지 체크
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            // 위치 추적 코드 추가

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(2000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            fLC.requestLocationUpdates(locationRequest, callback, Looper.getMainLooper());
        }
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        if(overlay instanceof Marker){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ci2022kingsman.dongyangmirae.kr")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            UsanApi UsanApi = retrofit.create(UsanApi.class);
            Call<UsanList> call = UsanApi.UsanGet();
            call.enqueue(new Callback<UsanList>() {
                @Override
                public void onResponse(Call<UsanList> call, Response<UsanList> response) {
                    Log.d("Usan", "요청 성공");
                    UsanList usanList = response.body();

                        String id = usanList.getUsan2().get(0).id;
                        String status = usanList.getUsan2().get(0).u_status1;

                        Log.d("Usan", id + ": " + status);


                        if (id.equals("1")) {
                            if (status.equals("return")) {
                                Toast.makeText(RentalActivity.this, "3호관 카페드림 앞, 대여 가능", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RentalActivity.this, "3호관 카페드림 앞, 대여 불가능", Toast.LENGTH_LONG).show();
                            }
                        }
                }

                @Override
                public void onFailure(Call<UsanList> call, Throwable t) {
                    Log.e("Usan", t.getLocalizedMessage());
                }
            });
            return true;
        }
        return false;
    }
}