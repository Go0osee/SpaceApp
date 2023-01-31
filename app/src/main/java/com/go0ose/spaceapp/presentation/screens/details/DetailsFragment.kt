package com.go0ose.spaceapp.presentation.screens.details

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.go0ose.spaceapp.SpaseApp
import com.go0ose.spaceapp.databinding.FragmentDetailsBinding
import com.go0ose.spaceapp.presentation.screens.base.BaseFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class DetailsFragment(private val photo: String) : BaseFragment<FragmentDetailsBinding>(), IDetailsView  {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<DetailsPresenter>

    private val presenter by moxyPresenter() { presenterProvider.get() }

    override fun binding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaseApp.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}