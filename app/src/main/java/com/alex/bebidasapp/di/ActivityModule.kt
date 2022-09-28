package com.alex.bebidasapp.di

import com.alex.bebidasapp.data.network.DataSource
import com.alex.bebidasapp.data.network.DataSourceImpl
import com.alex.bebidasapp.repository.Repo
import com.alex.bebidasapp.repository.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    //Aqui definimos donde estan nuestras interfaces instanciadas
    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl):Repo

    @Binds
    abstract fun bindDataSourceImpl(dataSourceImpl: DataSourceImpl): DataSource
}