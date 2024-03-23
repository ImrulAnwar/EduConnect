package com.imrul.educonnect.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.imrul.educonnect.data.network.AuthenticationDataSourceImplementation
import com.imrul.educonnect.data.repository.AuthenticationRepoImplementation
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}