package ir.org.tavanesh.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.org.tavanesh.core.domain.advisor.repository.AdviserRepository
import ir.org.tavanesh.core.domain.city.repository.CityRepository
import ir.org.tavanesh.core.domain.consult.repository.ConsultRepository
import ir.org.tavanesh.core.domain.course.repository.CourseRepository
import ir.org.tavanesh.core.domain.exam.repository.ExamRepository
import ir.org.tavanesh.core.domain.logy.repository.LogyRepository
import ir.org.tavanesh.core.domain.question.repository.QuestionRepository
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.UserRepository
import ir.org.tavanesh.core.domain.video.repository.VideoRepository
import ir.org.tavanesh.data.impl.repository.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(Impl: UserRepositoryImpl
    ): UserRepository = Impl

    @Provides
    @Singleton
    fun provideAuthRepository(Impl: AuthRepositoryImpl
    ): AuthRepository = Impl

    @Provides
    @Singleton
    fun provideAdviserRepository(Impl: AdviserRepositoryImpl
    ): AdviserRepository = Impl

    @Provides
    @Singleton
    fun provideCityRepository(Impl: CityRepositoryImpl
    ): CityRepository = Impl

    @Provides
    @Singleton
    fun provideConsultRepository(Impl: ConsultRepositoryImpl
    ): ConsultRepository = Impl

    @Provides
    @Singleton
    fun provideCourseRepository(Impl: CourseRepositoryImpl
    ): CourseRepository = Impl

    @Provides
    @Singleton
    fun provideExamRepository(Impl: ExamRepositoryImpl
    ): ExamRepository = Impl

    @Provides
    @Singleton
    fun provideQuestionRepository(Impl: QuestionRepositoryImpl
    ): QuestionRepository = Impl

    @Provides
    @Singleton
    fun provideVideoRepository(Impl: VideoRepositoryImpl
    ): VideoRepository = Impl

    @Provides
    @Singleton
    fun provideLogyRepository(Impl: LogyRepositoryImpl
    ): LogyRepository = Impl

}