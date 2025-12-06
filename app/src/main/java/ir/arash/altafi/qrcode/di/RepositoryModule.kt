package ir.arash.altafi.qrcode.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.arash.altafi.qrcode.data.db.TestDao
import ir.arash.altafi.qrcode.data.repository.HistoryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHistoryRepository(
        dao: TestDao,
    ) = HistoryRepository(dao)

}