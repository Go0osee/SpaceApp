package com.go0ose.spaceapp.presentation.screens.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IDetailsView : MvpView {
    fun showTutorial()
    fun hideTutorial()
    fun shareImage()
}