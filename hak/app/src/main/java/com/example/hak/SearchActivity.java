package com.example.hak;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
/************************************
 * 検索画面
 *
 *起動したとき真ん中から下の方が真っ白かもしれませんがそこに投稿された画像が入ります。
 */

//起動したとき
public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        int maxResults = 1;
        List<Address> lstAddr;
        String search_key = "郡山市";
        Geocoder gcoder = new Geocoder(this, Locale.getDefault());
        // 位置情報の取得
        try {
            lstAddr = gcoder.getFromLocationName("東京都渋谷区神南2丁目2－1" , maxResults);

            if(lstAddr != null && lstAddr.size() > 0) {
                // 緯度・経度取得
                Address addr = lstAddr.get(0);
                double latitude = addr.getLatitude();
                double longitude = addr.getLongitude();

                Toast.makeText(this, "位置\n緯度:" + latitude + "\n経度:" + longitude, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplication(), InputActivity.class);

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

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {  //idがMapのボタンをクリックした際の処理
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DetailActivity.class);   //遷移先指定
                startActivity(intent);
            }
        });

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

        //初期地点 ピンの場所になると思います
        LatLng sydney = new LatLng(-34, 151);
        location = sydney;
        // camera 移動
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
        // Add a marker in Sydney and move the camera

        //<--MarkerOptionsでシドニーの場所に"Marker in Sydney"という吹き出しを出します-->
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}

