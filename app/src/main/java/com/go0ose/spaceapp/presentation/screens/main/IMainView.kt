package com.go0ose.spaceapp.presentation.screens.main

import com.go0ose.domain.models.Photo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.OneExecution

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView {
    @OneExecution
    fun showError(message: String)
    fun showProgress()
    fun hideProgress()
    fun loadRecycler(list: List<Photo>)
}