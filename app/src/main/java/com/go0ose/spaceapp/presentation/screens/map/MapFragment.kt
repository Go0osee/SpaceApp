package com.go0ose.spaceapp.presentation.screens.map

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import com.github.terrakok.cicerone.Router
import com.go0ose.spaceapp.R
import com.go0ose.spaceapp.SpaseApp
import com.go0ose.spaceapp.databinding.FragmentMapBinding
import com.go0ose.spaceapp.presentation.screens.base.BaseFragment
import com.go0ose.spaceapp.presentation.screens.map.models.ActionMap
import com.go0ose.spaceapp.presentation.screens.map.recycler.MapAdapter
import com.go0ose.spaceapp.presentation.screens.map.recycler.OnItemClickListener
import com.go0ose.spaceapp.utils.setShader
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MapFragment : BaseFragment<FragmentMapBinding>(), IMapView, OnMapReadyCallback {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<MapPresenter>

    private val presenter by moxyPresenter() { presenterProvider.get() }

    private lateinit var googleMap: GoogleMap

    private val onItemListener by lazy {
        object : OnItemClickListener {
            override fun onItemClickDelete(marker: Marker) {
                marker.remove()
                presenter.doWork(ActionMap.DeleteMarker(marker))
            }
        }
    }

    private val adapter = MapAdapter(onItemListener)

    override fun binding() = FragmentMapBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaseApp.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.buttonMapType.setOnClickListener {
            presenter.doWork(ActionMap.OnClickMapType)
        }

        BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet)).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        binding.bottomSheet.recycler.adapter = adapter
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map)
        )
        this.googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        this.googleMap.setOnMapClickListener { latLng ->
            presenter.doWork(ActionMap.OnClickMap(latLng))
        }
        this.googleMap.setOnMarkerClickListener { marker ->
            marker.showInfoWindow()
            false
        }
        presenter.doWork(ActionMap.LoadMarkers)
    }

    override fun showAlertChooseMapType() {
        MaterialAlertDialogBuilder(requireContext())
            .setSingleChoiceItems(
                arrayOf(
                    requireContext().getString(R.string.hybrid),
                    requireContext().getString(R.string.map),
                    requireContext().getString(R.string.satellite),
                ),
                presenter.mapType
            ) { dialog, mapType ->
                presenter.doWork(ActionMap.OnClickItemChooseAlert(mapType))
                dialog.cancel()
            }.show()
    }

    override fun changeMapType(type: Int) {
        googleMap.mapType = type
    }

    override fun showAlertAddMarker(latLng: LatLng) {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_add_marker, null)
        val editName = dialogLayout.findViewById<EditText>(R.id.editMarkerName)
        val negativeButton = dialogLayout.findViewById<TextView>(R.id.cansel)
        negativeButton.setShader()
        val positiveButton = dialogLayout.findViewById<TextView>(R.id.save)
        positiveButton.setShader()
        builder.setCancelable(true)
        builder.setView(dialogLayout)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        positiveButton.setOnClickListener {
            presenter.doWork(ActionMap.OnClickSaveMarker(editName.text.toString(), latLng))
            alertDialog.cancel()
        }
        negativeButton.setOnClickListener {
            alertDialog.cancel()
        }
        alertDialog.show()
    }

    override fun addMarkerMap(name: String, latLng: LatLng) {
        val marker =
            googleMap.addMarker(MarkerOptions().position(latLng)
                .title(name)
                .icon(BitmapDescriptorFactory.fromBitmap(requireContext().getDrawable(R.drawable.ic_marker)!!
                    .toBitmap(48, 64))))
        presenter.doWork(ActionMap.AddMarker(marker!!))
    }

    override fun updateList(list: List<Marker>) {
        adapter.updateItems(list)
    }
}