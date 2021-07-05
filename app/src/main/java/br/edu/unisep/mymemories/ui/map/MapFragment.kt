package br.edu.unisep.mymemories.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.unisep.mymemories.databinding.FragmentMapBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import br.edu.unisep.mymemories.R

class MapFragment : Fragment() {

    private lateinit var mapViewModel: MapViewModel

    private val binding: FragmentMapBinding by lazy {
        FragmentMapBinding.inflate(layoutInflater)
    }

    private lateinit var mMap: GoogleMap

    private lateinit var mLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMap()
    }

    private fun setupMap() {

        val supportMapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        supportMapFragment.getMapAsync(OnMapReadyCallback {
            fun onMapReady(googleMap: GoogleMap) {

                googleMap.setOnMapClickListener { GoogleMap.OnMapClickListener {

                    fun onMapClick(latLng: LatLng) {
                        val markerOptions: MarkerOptions = MarkerOptions()

                        markerOptions.position(latLng)

                        markerOptions.title(latLng.latitude.toString() + latLng.longitude.toString())

                        googleMap.clear()

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            latLng, 10F
                        ))

                        googleMap.addMarker(markerOptions)
                    }

                } }
            }
        })
    }

}