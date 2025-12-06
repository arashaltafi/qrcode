package ir.arash.altafi.qrcode.utils.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> callCache(
        cacheCall: suspend () -> T
    ): Flow<T> = flow {
        emit(cacheCall())
    }.flowOn(Dispatchers.IO)

    protected fun <T> callDatabase(
        databaseCall: suspend () -> T
    ): Flow<T> = flow {
        emit(databaseCall())
    }.flowOn(Dispatchers.IO)
}