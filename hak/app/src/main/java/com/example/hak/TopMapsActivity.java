package com.example.hak;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//トップ画面
public class TopMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    private LatLng location;
    public Marker marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topmap);

        //idがsearchのボタンをクリックした際に呼ばれる関数
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SearchActivity.class);       //案内
                startActivity(intent);

            }
        });

        //idがoptionのボタンをクリックした際に呼ばれる関数
        findViewById(R.id.option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MenuActivity.class);       //案内
                startActivity(intent);

            }
        });

        //idがpostのボタンをクリックした際に呼ばれる関数
        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputActivity.class);       //案内
                startActivity(intent);

            }
        });
        //mMap.setMyLocationEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);


        int maxResults = 1;
        List<Address> lstAddr;
        //String search_key = "郡山市";    使ってません

        //<--Geocoder　住所から緯度経度を取得するために使います-->
        Geocoder gcoder = new Geocoder(this, Locale.getDefault());
        // 位置情報の取得
        try {
            lstAddr = gcoder.getFromLocationName("東京都渋谷区神南2丁目2－1" , maxResults);

            if(lstAddr != null && lstAddr.size() > 0) {
                // 緯度・経度取得
                Address addr = lstAddr.get(0);
                double latitude = addr.getLatitude();
                double longitude = addr.getLongitude();

                //ダイアログ表示
                Toast.makeText(this, "位置\n緯度:" + latitude + "\n経度:" + longitude, Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getApplication(), InputActivity.class);

            }else{
                //ピンを打たせる

//                double latitude = addr.getLatitude();
//                double longitude = addr.getLongitude();

            }
        }
        catch (IOException e) {
            // 例外処理
            return;
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //マップを開いた時の初期地点
        LatLng fcs = new LatLng(37.395837, 140.330031);
        location = fcs;
        // camera 移動
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        // Add a marker in fcs and move the camera

        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //現在地を取得するためにパーミッションチェック
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mMap.setMyLocationEnabled(true);      //現在地を表示する
//
//        } else {
//            Toast.makeText(TopMapsActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                mMap.setMyLocationEnabled(true);
//            }
//
//        }

        //現在地を取得するためにパーミッションチェック
//        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "許可されています。", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "許可されていません。", Toast.LENGTH_SHORT).show();
//        }

        //マップタップイベント
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
//            public boolean onMarkerClick(Marker marker) {
//                // TODO Auto-generated method stub
//                //Intent intent = new Intent(getApplication(), MenuActivity.class);
//                //intent.putExtra("DATA_ID", id);
//                //startActivity(intent);
//                //marker.showInfoWindow();
//                return true;
//            }
            public boolean onMarkerClick(Marker marker) {//ピンタップイベント
                marker.setTitle("検索または投稿");//ウィンドウのタイトル
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {//ウィンドウタップイベント
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        //検索画面へ
                        Toast.makeText(TopMapsActivity.this, "検索画面を開きます", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplication(), SearchActivity.class);       //案内
                        //startActivity(intent);
                    }
                });
                //Toast.makeText(TopMapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        // タップした時のリスナーをセット
//        //<--タップでもピンが立ってしまうのは使いづらそうなので長押しのみ有効化してます--->
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng tapLocation) {
//                // tapされた位置の緯度経度
//                location = new LatLng(tapLocation.latitude, tapLocation.longitude);
//                //String str = String.format(Locale.US, "%f, %f", tapLocation.latitude, tapLocation.longitude);
//                String str = String.format(Locale.US, "%f, %f", tapLocation.latitude, tapLocation.longitude);
//                MarkerOptions options = new MarkerOptions();
//                options.position(location);
//                options.title(str);
//                //options.snippet(location.toString());
//                mMap.addMarker(options);
//                //mMap.addMarker(options.position(location).title(str));
//                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
//            }
//        });
//

        // 長押しのリスナーをセット
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng longpushLocation) {
                LatLng newlocation = new LatLng(longpushLocation.latitude, longpushLocation.longitude);

                //ピン立て
                if (marker != null) {
                    marker.remove();//古いピンを削除する
                }
                MarkerOptions newMaker = new MarkerOptions().position(newlocation).title(""+longpushLocation.latitude+" :"+ longpushLocation.longitude);
                marker = mMap.addMarker(newMaker);
                //カメラは移動しない
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newlocation, 14));

            }
        });
    }
}

