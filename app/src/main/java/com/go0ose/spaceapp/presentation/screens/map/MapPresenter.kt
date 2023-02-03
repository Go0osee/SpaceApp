package com.go0ose.spaceapp.presentation.screens.map

import com.go0ose.spaceapp.presentation.screens.map.models.ActionMap
import com.google.android.gms.maps.GoogleMap
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MapPresenter : MvpPresenter<IMapView>() {

    var mapType = 0

    fun doWork(action: ActionMap) {

        when (action) {
            is ActionMap.OnClickMapType -> {
                viewState.showAlertChooseMapType()
            }
            is ActionMap.OnClickMap -> {
                viewState.showAlertAddMarker(action.latLng)
            }
            is ActionMap.OnClickItemChooseAlert -> {
                mapType = action.mapType

                viewState.changeMapType(
                    when (mapType) {
                        1 -> GoogleMap.MAP_TYPE_NORMAL
                        2 -> GoogleMap.MAP_TYPE_SATELLITE
                        else -> GoogleMap.MAP_TYPE_HYBRID
                    }
                )
            }
            is ActionMap.OnClickSaveMarker -> {
                viewState.addMarkerMap(action.name, action.latLng)
            }
        }
    }
}