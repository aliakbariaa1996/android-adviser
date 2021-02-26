package ir.org.tavanesh.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.org.tavanesh.G
import ir.org.tavanesh.core.utils.SHARED_ADVISER
import ir.org.tavanesh.data.platform.datasource.*
import ir.org.tavanesh.data.platform.impl.ResourcesRepositoryImpl
import ir.org.tavanesh.data.platform.impl.ViewUseCaseRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PlatformModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideAdviserApi(retrofit: Retrofit): AdviserApi = retrofit.create(AdviserApi::class.java)

    @Provides
    @Singleton
    fun provideCityApi(retrofit: Retrofit): CityApi = retrofit.create(CityApi::class.java)

    @Provides
    @Singleton
    fun provideConsultApi(retrofit: Retrofit): ConsultApi = retrofit.create(ConsultApi::class.java)

    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): CourseApi = retrofit.create(CourseApi::class.java)

    @Provides
    @Singleton
    fun provideExamApi(retrofit: Retrofit): ExamApi = retrofit.create(ExamApi::class.java)

    @Provides
    @Singleton
    fun provideQuestionApi(retrofit: Retrofit): QuestionApi = retrofit.create(QuestionApi::class.java)

    @Provides
    @Singleton
    fun provideVideoApi(retrofit: Retrofit): VideoApi = retrofit.create(VideoApi::class.java)

    @Provides
    @Singleton
    fun provideLogyApi(retrofit: Retrofit): LogyApi = retrofit.create(LogyApi::class.java)

    @Provides
    @Singleton
    fun provideLogyDao(): LogyDao {
        return G.database.logyDao()
    }

    @Provides
    @Singleton
    fun provideCityDao(): CityDao {
        return G.database.cityDao()
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_ADVISER, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideResourceRepository(
        resourcesRepositoryImpl: ResourcesRepositoryImpl
    ): ResourcesRepository = resourcesRepositoryImpl

    @Provides
    @Singleton
    fun provideViewUseCaseRepository(
        viewUseCaseRepositoryImpl: ViewUseCaseRepositoryImpl
    ): ViewUseCaseRepository = viewUseCaseRepositoryImpl

}