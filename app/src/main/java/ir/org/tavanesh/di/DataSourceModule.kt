package ir.org.tavanesh.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.org.tavanesh.data.datasource.*
import ir.org.tavanesh.data.impl.datasource.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(Impl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource = Impl

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(Impl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideUserLocalDataSource(Impl: UserLocalDataSourceImpl
    ): UserLocalDataSource = Impl

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(Impl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideAdviserRemoteDataSource(Impl: AdviserRemoteDataSourceImpl
    ): AdviserRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideCityLocalDataSource(Impl:CityLocalDataSourceImpl
    ): CityLocalDataSource = Impl

    @Provides
    @Singleton
    fun provideCityRemoteDataSource(Impl: CityRemoteDataSourceImpl
    ): CityRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideConsultRemoteDataSource(Impl: ConsultRemoteDataSourceImpl
    ): ConsultRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideCourseRemoteDataSource(Impl: CourseRemoteDataSourceImpl
    ): CourseRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideExamRemoteDataSource(Impl: ExamRemoteDataSourceImpl
    ): ExamRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideQuestionRemoteDataSource(Impl: QuestionRemoteDataSourceImpl
    ): QuestionRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideVideoRemoteDataSource(Impl: VideoRemoteDataSourceImpl
    ): VideoRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideLogyRemoteDataSource(Impl: LogyRemoteDataSourceImpl
    ): LogyRemoteDataSource = Impl

    @Provides
    @Singleton
    fun provideLogyLocalDataSource(Impl: LogyLocalDataSourceImpl
    ): LogyLocalDataSource = Impl
}