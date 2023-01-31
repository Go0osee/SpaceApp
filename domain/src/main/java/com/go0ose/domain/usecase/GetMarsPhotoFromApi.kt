package com.go0ose.domain.usecase

import com.go0ose.domain.repository.NetworkRepository
import javax.inject.Inject

class GetMarsPhotoFromApi @Inject constructor(
    private val networkRepository: NetworkRepository,
) {
    fun execute(page: Int) = networkRepository.getMarsPhotosFromApi(page)
}