package com.go0ose.spaceapp.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.go0ose.spaceapp.presentation.screens.details.DetailsFragment
import com.go0ose.spaceapp.presentation.screens.main.MainFragment
import com.go0ose.spaceapp.presentation.screens.map.MapFragment

object Screens {
    fun Main() = FragmentScreen { MainFragment() }
    fun Details(photo: String) = FragmentScreen { DetailsFragment(photo) }
    fun Map() = FragmentScreen { MapFragment() }
}