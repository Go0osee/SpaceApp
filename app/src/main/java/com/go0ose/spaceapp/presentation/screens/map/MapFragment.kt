package com.go0ose.spaceapp.presentation.screens.map

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.go0ose.spaceapp.SpaseApp
import com.go0ose.spaceapp.databinding.FragmentMapBinding
import com.go0ose.spaceapp.presentation.screens.base.BaseFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MapFragment: BaseFragment<FragmentMapBinding>(), IMapView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<MapPresenter>

    private val presenter by moxyPresenter() { presenterProvider.get() }

    override fun binding() = FragmentMapBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaseApp.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}