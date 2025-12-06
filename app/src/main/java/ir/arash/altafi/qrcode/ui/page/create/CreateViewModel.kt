package ir.arash.altafi.qrcode.ui.page.create

import android.graphics.Bitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.arash.altafi.qrcode.data.repository.CreateRepository
import ir.arash.altafi.qrcode.utils.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val repository: CreateRepository
) : BaseViewModel<Boolean>() {

    fun addQrCode(
        text: String,
        bitmap: Bitmap,
        time: Long
    ) {
        callDatabase(
            block = {
                repository.addQrCode(text, bitmap, time)
            }
        )
    }
}