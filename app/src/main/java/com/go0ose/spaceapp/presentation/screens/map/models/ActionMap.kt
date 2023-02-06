package com.go0ose.spaceapp.presentation.screens.map.models

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

sealed class ActionMap {
    object OnClickMapType : ActionMap()
    class OnClickMap(val latLng: LatLng) : ActionMap()
    class OnClickItemChooseAlert(val mapType: Int) : ActionMap()
    class OnClickSaveMarker(val name: String, val latLng: LatLng) : ActionMap()
    class AddMarker(val marker: Marker) : ActionMap()
    class DeleteMarker(val marker: Marker) : ActionMap()
    object LoadMarkers : ActionMap()
}