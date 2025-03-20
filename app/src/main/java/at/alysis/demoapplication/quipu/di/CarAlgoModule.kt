package at.alysis.demoapplication.quipu.di

import at.alysis.demoapplication.quipu.CarAlgo
import at.alysis.demoapplication.quipu.CarAlgoDef
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CarAlgoModule {
    @Singleton
    @Binds
    fun bindsCarAlgo(
        carAlgo: CarAlgo
    ): CarAlgoDef
}
