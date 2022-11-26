package me.dwidar.movieapp.src.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.dwidar.movieapp.src.app.BaseApplication
import me.dwidar.movieapp.src.app.DetailsScreen.service.IService.IDetailsScreenService
import me.dwidar.movieapp.src.app.DetailsScreen.service.ImplService.ImplDetailsScreenService
import me.dwidar.movieapp.src.app.MainScreen.service.IService.IMainScreenService
import me.dwidar.movieapp.src.app.MainScreen.service.ImplService.ImplMainScreenService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app : Context) : BaseApplication
    {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideImplMainService() : IMainScreenService
    {
        return ImplMainScreenService()
    }

    @Singleton
    @Provides
    fun provideImplDetailsService() : IDetailsScreenService
    {
        return ImplDetailsScreenService()
    }

}