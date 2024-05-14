package com.veyvolopayli.domain.usecases

import com.veyvolopayli.domain.repository.StorageRepository

class SaveDepartureLocationUseCase(
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(value: String) {
        storageRepository.saveValue("departure_location", value)
    }
}