package com.nadyaka.googlemapsproject

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isCompassEnabled = true

        mMap.setOnMapClickListener { point ->
            val marker = MarkerOptions()
                    .position(LatLng(point.latitude, point.longitude))
                    .title("Координаты:" + point.latitude + " " + point.latitude)
            mMap.addMarker(marker)
            Toast.makeText(this, "Координаты точки: ${point.latitude} ${point.longitude}", Toast.LENGTH_LONG).show()
        }

    }

    fun ChangeType(view: View?) {
        if (mMap.mapType == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        } else if (mMap.mapType == GoogleMap.MAP_TYPE_SATELLITE) {
            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        } else if (mMap.mapType == GoogleMap.MAP_TYPE_HYBRID) {
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        } else if (mMap.mapType == GoogleMap.MAP_TYPE_TERRAIN) {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

}