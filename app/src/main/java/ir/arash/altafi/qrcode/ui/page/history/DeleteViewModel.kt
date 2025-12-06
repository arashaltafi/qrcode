package ir.arash.altafi.qrcode.ui.page.history

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.data.repository.DeleteRepository
import ir.arash.altafi.qrcode.utils.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(
    private val repository: DeleteRepository
) : BaseViewModel<Unit>() {

    fun delete(qrcode: QRCodeEntity) {
        callDatabase(
            block = {
                repository.delete(qrcode)
            }
        )
    }
}