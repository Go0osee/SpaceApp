package com.go0ose.spaceapp.presentation.screens.map.models

import com.google.android.gms.maps.model.LatLng

sealed class ActionMap {
    object OnClickMapType : ActionMap()
    class OnClickMap(val latLng: LatLng) : ActionMap()
    class OnClickItemChooseAlert(val mapType: Int) : ActionMap()
    class OnClickSaveMarker(val name: String, val latLng: LatLng) : ActionMap()
}