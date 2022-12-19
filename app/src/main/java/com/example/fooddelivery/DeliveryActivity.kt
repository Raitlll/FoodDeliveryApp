package com.example.fooddelivery

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Transformations.map
import com.example.fooddelivery.databinding.ActivityDeliveryBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import kotlin.math.pow

class DeliveryActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMyLocationClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityDeliveryBinding
    var lnglat: LatLng= LatLng(0.0,0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.location_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationClickListener(this)
        var address: String? = intent.getStringExtra("address")
        val pointer = getLocationFromAddress(this, address)
        val name: String? = intent.getStringExtra("restaurant")
        pointer?.let { MarkerOptions().position(it).title("Marker at $name ") }
            ?.let { mMap.addMarker(it) }
        pointer?.let { CameraUpdateFactory.newLatLng(it) }?.let { mMap.moveCamera(it) }
        pointer?.let { CameraUpdateFactory.newLatLngZoom(it, 12F) }?.let { mMap.moveCamera(it) }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        var m: String? = intent.getStringExtra("total")
        if (m != null) {
            Log.d("sad",m)
        }
        var m2: Double = m?.toDouble() ?: 0.0
        var money:TextView = findViewById(R.id.TotalSumm)
        val sumlat: Double = Math.pow((pointer?.latitude ?: 0) as Double - lnglat.latitude,2.0)
        var sumlng: Double = Math.pow((pointer?.longitude ?: 0) as Double - lnglat.longitude,2.0)
        var sum: Double = Math.sqrt(sumlat + sumlng)
        var total = Math.round(sum) + m2
        money.text = "Total sum:  $total"

        var orderBtn: Button = findViewById(R.id.button1)
        orderBtn.setOnClickListener {
            var intent:Intent = Intent(this,RestaurantActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Order placed successfully!",Toast.LENGTH_SHORT
            ).show()
        }

    }
    override fun onMyLocationClick(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        var latlng = LatLng(latitude, longitude)

        val geocoder = Geocoder(this)
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val loc = addresses[0].getAddressLine(0)

        mMap.addMarker(MarkerOptions().position(latlng).title("You are here $loc"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12F))

    }
    fun getLocationFromAddress(context: Context?, strAddress: String?): LatLng? {
        val coder = Geocoder(context)
        val address: List<Address>?
        var p1: LatLng? = null
        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5)
            if (address == null) {
                return null
            }
            val location = address[0]
            p1 = LatLng(location.latitude, location.longitude)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return p1
    }


}