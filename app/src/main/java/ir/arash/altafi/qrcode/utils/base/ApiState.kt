package ir.arash.altafi.qrcode.utils.base

sealed class ApiState<out T> {
    object Default : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
    object Refreshing : ApiState<Nothing>()
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error(val message: String) : ApiState<Nothing>()
}