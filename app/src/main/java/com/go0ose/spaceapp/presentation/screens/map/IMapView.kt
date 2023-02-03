package com.go0ose.spaceapp.presentation.screens.map

import com.google.android.gms.maps.model.LatLng
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMapView : MvpView {
    fun showAlertChooseMapType()
    fun changeMapType(type: Int)
    fun showAlertAddMarker(latLng: LatLng)
    fun addMarkerMap(name: String, latLng: LatLng)
}