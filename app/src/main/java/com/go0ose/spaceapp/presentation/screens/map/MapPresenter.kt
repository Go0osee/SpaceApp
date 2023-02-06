package com.go0ose.spaceapp.presentation.screens.map

import com.go0ose.spaceapp.presentation.screens.map.models.ActionMap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MapPresenter : MvpPresenter<IMapView>() {

    var mapType = 0
    private val listMarker = mutableListOf<Marker>()
    private val loadList = mutableListOf<Marker>()

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
            is ActionMap.DeleteMarker -> {
                listMarker.remove(action.marker)
                viewState.updateList(listMarker)
            }
            is ActionMap.AddMarker -> {
                listMarker.add(action.marker)
                viewState.updateList(listMarker)
            }
            is ActionMap.LoadMarkers -> {
                loadList.addAll(listMarker)
                listMarker.clear()
                loadList.forEach{ marker ->
                    viewState.addMarkerMap(marker.title!!, marker.position)
                }
                loadList.clear()
            }
        }
    }
}