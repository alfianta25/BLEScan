package com.yoga.blescan.domain.usecase

import com.yoga.blescan.domain.repository.BLRepository

class StartScanUseCase(private val repository: BLRepository) {
    operator fun invoke() = repository.startScanning()
}
