package ir.arash.altafi.qrcode.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.arash.altafi.qrcode.data.db.QRCodeDao
import ir.arash.altafi.qrcode.data.repository.CreateRepository
import ir.arash.altafi.qrcode.data.repository.DeleteRepository
import ir.arash.altafi.qrcode.data.repository.DetailRepository
import ir.arash.altafi.qrcode.data.repository.HistoryRepository
import ir.arash.altafi.qrcode.data.repository.ScanRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHistoryRepository(
        qrCodeDao: QRCodeDao,
    ) = HistoryRepository(qrCodeDao)

    @Singleton
    @Provides
    fun provideScanRepository(
        qrCodeDao: QRCodeDao,
    ) = ScanRepository(qrCodeDao)

    @Singleton
    @Provides
    fun provideCreateRepository(
        qrCodeDao: QRCodeDao,
    ) = CreateRepository(qrCodeDao)

    @Singleton
    @Provides
    fun provideDeleteRepository(
        qrCodeDao: QRCodeDao,
    ) = DeleteRepository(qrCodeDao)

    @Singleton
    @Provides
    fun provideDetailRepository(
        qrCodeDao: QRCodeDao,
    ) = DetailRepository(qrCodeDao)
}