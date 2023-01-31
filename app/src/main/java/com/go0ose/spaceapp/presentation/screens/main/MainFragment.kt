package com.go0ose.spaceapp.presentation.screens.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Router
import com.go0ose.domain.models.Photo
import com.go0ose.domain.usecase.GetMarsPhotoFromApi
import com.go0ose.spaceapp.SpaseApp
import com.go0ose.spaceapp.databinding.FragmentMainBinding
import com.go0ose.spaceapp.presentation.screens.base.BaseFragment
import com.go0ose.spaceapp.presentation.screens.main.recycler.MainScreenAdapter
import com.go0ose.spaceapp.presentation.screens.main.recycler.OnItemClickListener
import com.go0ose.spaceapp.utils.showToast
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainFragment : BaseFragment<FragmentMainBinding>(), IMainView {

    @Inject
    lateinit var getMarsPhotoFromApi: GetMarsPhotoFromApi

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    private val presenter by moxyPresenter() { presenterProvider.get() }

    private val onItemListener by lazy {
        object : OnItemClickListener {
            override fun onItemClick(photo: Photo) {
                presenter.openDetailsScreen(photo)
            }
        }
    }

    private var adapter = MainScreenAdapter(onItemListener)

    override fun binding() = FragmentMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaseApp.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initScrolledListener()
        if (presenter.list.isEmpty()) {
            presenter.loadPhoto()
        }
    }

    private fun initScrolledListener() {
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (layoutManager != null) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (!presenter.loadingState && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    ) {
                        presenter.loadPhoto()
                    }
                }
            }
        })
    }

    private fun initRecycler() {
        with(binding) {
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = adapter
        }
    }

    override fun showError(message: String) {
        requireContext().showToast(message)
    }

    override fun showProgress() {
        binding.progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = ProgressBar.GONE
    }

    override fun loadRecycler(list: List<Photo>) {
        adapter.updateItems(list)
    }
}