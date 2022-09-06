package com.example.cars.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cars.R
import com.example.cars.databinding.ActivityMapBinding
import com.example.cars.model.CarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_map.*
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker


@AndroidEntryPoint
class MapActivity : AppCompatActivity(), CarListCallBack {
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapController: IMapController
    private val vm: CarsViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ctx: Context = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))
        setupMap()
        setupObserver()
        setupClickListener()
    }

    private fun setupClickListener() {
        btn_showList_activityNeshanMaps.setOnClickListener {
            val bottomSheet = CarsListBottomSheet(this)
            bottomSheet.show(supportFragmentManager, CarsListBottomSheet.TAG)
        }
    }

    private fun setupObserver() {
        vm.showLoading.observe(this) {
            if (it) {
                pb_waiting_activityNeshanMaps.visibility = View.VISIBLE
            } else {
                pb_waiting_activityNeshanMaps.visibility = View.INVISIBLE
            }
        }

        vm.myList.observe(this) {
            if (it.isNotEmpty()) {
                for (car in it) {
                    addMarkers(car)
                }
                mapController.setCenter(GeoPoint(it[0].latitude, it[0].longitude))
            }
        }
    }

    private fun addMarkers(car: CarView) {
        val latLng = GeoPoint(car.latitude, car.longitude)
        val startMarker = Marker(mapView)
        startMarker.position = latLng
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        startMarker.icon = resources.getDrawable(R.drawable.ic_car, theme)
        startMarker.snippet = generateSnippet(car)
        startMarker.title = car.name
        startMarker.id = car.id
        mapView.overlays.add(startMarker)
    }

    private fun setupMap() {
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapController = mapView.controller
        mapController.setZoom(15.0)
    }

    private fun generateSnippet(car: CarView): String {
        return "<b>license Plate:</b> ${car.licensePlate}<br><b>Color:</b> ${car.color}<br><b>Group:</b> ${car.group}" +
                "<br><b>Transmission:</b>  ${car.transmission}"
    }

    override fun onResume() {
        super.onResume()
        if (mapView != null)
            mapView.onResume()
    }

    override fun onCarClicked(carView: CarView) {
        val selectedMarker = (mapView.overlays.find { (it as Marker).id == carView.id } as Marker)
        selectedMarker.showInfoWindow()
        mapController.animateTo(GeoPoint(carView.latitude, carView.longitude), 17.0, 1500)
    }
}

interface CarListCallBack {
    fun onCarClicked(carView: CarView)
}