package ir.arash.altafi.qrcode.ui.page.history

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.arash.altafi.qrcode.data.model.TestEntity
import ir.arash.altafi.qrcode.data.repository.HistoryRepository
import ir.arash.altafi.qrcode.utils.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository
) : BaseViewModel<List<TestEntity>>() {

    fun getAll() {
        callDatabase(
            block = {
                repository.getAll()
                    ?: throw Exception("History Failed")
            }
        )
    }
}