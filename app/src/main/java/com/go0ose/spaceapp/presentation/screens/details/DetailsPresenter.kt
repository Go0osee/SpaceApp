package com.go0ose.spaceapp.presentation.screens.details

import android.content.Context
import com.github.terrakok.cicerone.Router
import com.go0ose.spaceapp.presentation.screens.details.models.ActionDetails
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailsPresenter(
    private val router: Router,
    private val context: Context,
) : MvpPresenter<IDetailsView>() {

    override fun attachView(view: IDetailsView?) {
        super.attachView(view)

        if (isFirst()) {
            viewState.showTutorial()
        }
    }

    fun doWork(action: ActionDetails) {
        when (action) {
            is ActionDetails.OnClickButtonShare -> {
                viewState.shareImage()
            }
            is ActionDetails.OnClickButtonBack -> {
                router.exit()
            }
            is ActionDetails.OnClickTutorial -> {
                viewState.hideTutorial()
            }
        }
    }

    private fun isFirst(): Boolean {
        val prefs = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val isFirst = prefs.getBoolean("isFirst", true)
        return if (isFirst) {
            prefs.edit()
                .putBoolean("isFirst", false)
                .apply()
            true
        } else {
            false
        }
    }
}