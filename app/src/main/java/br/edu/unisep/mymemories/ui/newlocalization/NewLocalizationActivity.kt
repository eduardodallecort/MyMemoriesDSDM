package br.edu.unisep.mymemories.ui.newlocalization

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import br.edu.unisep.mymemories.databinding.ActivityNewLocalizationBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.R
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.grpc.InternalChannelz.id


class NewLocalizationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var mLocationClient: FusedLocationProviderClient

    private val binding: ActivityNewLocalizationBinding by lazy {
        ActivityNewLocalizationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupMap()
        setupLocationClient()

    }

    private fun setupMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.normal) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupLocationClient() {
        mLocationClient = LocationServices.getFusedLocationProviderClient(this)

        var locationRequest = LocationRequest.create().apply {
            interval = 15000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates()
        } else {
            permissionsLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        var locationRequest = LocationRequest.create().apply {
            interval = 15000
            fastestInterval = 3000
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }

        mLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.getMainLooper())

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener { location ->
            addMarker(location.latitude, location.longitude)
        }

        addMarker(-26.0779669, -53.0526023)
    }

    private val mLocationCallback = object: LocationCallback(){
        override fun onLocationResult(result: LocationResult) {
            addMarker(result.lastLocation.latitude, result.lastLocation.longitude)
            mLocationClient.removeLocationUpdates(this)
        }
    }

    private fun addMarker(latitude: Double, longitude: Double) {
        mMap.clear()

        val point = LatLng(latitude, longitude)

        mMap.addMarker(MarkerOptions().position(point))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 18f))
    }

    private val permissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            requestLocationUpdates()
        }
    }
}