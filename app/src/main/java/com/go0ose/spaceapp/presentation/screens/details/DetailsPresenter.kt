package com.go0ose.spaceapp.presentation.screens.details

import android.content.Context
import com.github.terrakok.cicerone.Router
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
        } else {
            hideTutorial()
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

    fun hideTutorial() {
        viewState.hideTutorial()
    }

    fun back() {
        router.exit()
    }
}