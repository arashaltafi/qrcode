package ir.arash.altafi.qrcode.ui.page.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.data.repository.DetailRepository
import ir.arash.altafi.qrcode.utils.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : BaseViewModel<QRCodeEntity>() {

    fun getById(id: Int) {
        callDatabase(
            block = {
                repository.getById(id)
                    ?: throw Exception("Detail Failed")
            }
        )
    }
}