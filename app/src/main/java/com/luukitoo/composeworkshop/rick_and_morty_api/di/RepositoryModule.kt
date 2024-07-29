package com.luukitoo.composeworkshop.rick_and_morty_api.di

import com.luukitoo.composeworkshop.rick_and_morty_api.data.repository.CharactersRepositoryImpl
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCharactersRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository
}
