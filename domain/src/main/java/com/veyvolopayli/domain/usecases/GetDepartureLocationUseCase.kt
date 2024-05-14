package com.veyvolopayli.domain.usecases

import com.veyvolopayli.domain.repository.StorageRepository

class GetDepartureLocationUseCase(
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(): String? {
        return storageRepository.getValue("departure_location")
    }
}