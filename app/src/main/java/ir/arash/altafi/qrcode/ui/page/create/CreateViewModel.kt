package ir.arash.altafi.qrcode.ui.page.create

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.arash.altafi.qrcode.data.repository.ScanRepository
import ir.arash.altafi.qrcode.utils.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val repository: ScanRepository
) : BaseViewModel<Boolean>() {

    fun addQrCode(text: String, time: Long) {
        callDatabase(
            block = {
                repository.addQrCode(text, time)
            }
        )
    }
}