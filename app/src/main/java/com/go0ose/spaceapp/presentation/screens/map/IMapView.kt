package com.go0ose.spaceapp.presentation.screens.map

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface IMapView : MvpView {
    fun showAlertChooseMapType()
    fun changeMapType(type: Int)
    fun showAlertAddMarker(latLng: LatLng)
    fun addMarkerMap(name: String, latLng: LatLng)
    fun updateList(list: List<Marker>)
}