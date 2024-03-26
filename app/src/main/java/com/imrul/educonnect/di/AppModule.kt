package com.imrul.educonnect.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.imrul.educonnect.data.network.AuthenticationDataSourceImplementation
import com.imrul.educonnect.data.repository.AuthenticationRepoImplementation
import com.imrul.educonnect.domain.network.ConnectivityObserver
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import com.imrul.educonnect.domain.user_cases.RegisterUseCase
import com.imrul.educonnect.domain.utils.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAuthDataSource(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth
    ) = AuthenticationDataSourceImplementation(auth, firestore)

    @Provides
    @Singleton
    fun provideAuthRepository(
        dataSource: AuthenticationDataSourceImplementation
    ): AuthenticationRepository = AuthenticationRepoImplementation(dataSource)

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthenticationRepository) = RegisterUseCase(repository)

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext appContext: Context
    ): ConnectivityObserver = NetworkConnectivityObserver(appContext)
}