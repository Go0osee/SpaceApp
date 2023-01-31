package com.go0ose.spaceapp.presentation.screens.main

import android.content.Context
import com.github.terrakok.cicerone.Router
import com.go0ose.domain.models.Photo
import com.go0ose.domain.usecase.GetMarsPhotoFromApi
import com.go0ose.spaceapp.R
import com.go0ose.spaceapp.presentation.navigation.Screens
import com.go0ose.spaceapp.utils.isOnline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    private val getMarsPhotoFromApi: GetMarsPhotoFromApi,
    private val router: Router,
    private val context: Context
) : MvpPresenter<IMainView>() {

    private val disposable = CompositeDisposable()

    var page = 0
    var loadingState = false
    val list = mutableListOf<Photo>()


    fun loadPhoto() {
        page = list.size / 25 + 1
        viewState.showProgress()
        loadingState = true

        disposable.add(getMarsPhotoFromApi.execute(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                viewState.hideProgress()
                loadingState = false
                if (context.isOnline()) {
                    viewState.showError(context.getString(R.string.connection_error))
                } else {
                    viewState.showError(context.getString(R.string.no_internet_connection))
                }
            }
            .map { response ->
                val newList = response.photos
                list.addAll(newList)

                viewState.loadRecycler(list)
                viewState.hideProgress()
                loadingState = false

            }.subscribe({}, {})
        )
    }

    fun openDetailsScreen(photo: Photo) {
        router.navigateTo(Screens.Details(photo.imgSrc))
    }
}