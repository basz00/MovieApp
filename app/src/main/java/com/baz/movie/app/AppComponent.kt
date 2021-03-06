package com.baz.movie.app

import com.baz.movie.main.MainBuilderModule
import com.baz.movie.movies.details.module.DetailsBuilderModule
import com.baz.movie.movies.nowplaying.module.NowPlayingBuilderModule
import com.baz.movie.movies.popular.module.PopularBuilderModule
import com.baz.movie.movies.upcoming.module.UpcomingBuilderModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelBuilderModule::class,
    MainBuilderModule::class,
    NowPlayingBuilderModule::class,
    UpcomingBuilderModule::class,
    PopularBuilderModule::class,
    DetailsBuilderModule::class])
internal interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}