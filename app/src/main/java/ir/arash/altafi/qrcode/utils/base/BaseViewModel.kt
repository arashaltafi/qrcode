package ir.arash.altafi.qrcode.utils.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    protected val _apiState = MutableStateFlow<ApiState<T>>(ApiState.Default)
    val apiState: StateFlow<ApiState<T>> = _apiState

    protected fun callDatabase(
        block: suspend () -> T,
        onSuccess: (T) -> Unit = { _apiState.value = ApiState.Success(it) },
        onError: (String) -> Unit = { _apiState.value = ApiState.Error(it) },
        onLoading: (Boolean) -> Unit = { isLoading ->
            _apiState.value = if (isLoading) ApiState.Loading else _apiState.value
        }
    ) {
        onLoading(true)

        viewModelScope.launch {
            try {
                val result = block()
                if (result != null) {
                    onSuccess(result)
                } else {
                    onError("Data not found")
                }
            } catch (e: Exception) {
                Log.e("SERVER", "Error: ${e.message}")
                onError(e.localizedMessage ?: "Unknown Error")
            } finally {
                onLoading(false)
            }
        }
    }

    protected fun callCache(
        block: suspend () -> T,
        onSuccess: (T) -> Unit = { _apiState.value = ApiState.Success(it) },
        onError: (String) -> Unit = { _apiState.value = ApiState.Error(it) },
        onLoading: (Boolean) -> Unit = { isLoading ->
            _apiState.value = if (isLoading) ApiState.Loading else _apiState.value
        }
    ) {
        onLoading(true)

        viewModelScope.launch {
            try {
                val result = block()
                if (result != null) {
                    onSuccess(result)
                } else {
                    onError("Data not found")
                }
            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Unknown Error")
            } finally {
                onLoading(false)
            }
        }
    }
}