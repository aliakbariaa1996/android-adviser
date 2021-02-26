package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.domain.advisor.repository.*
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.AdviserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdviserRepositoryImpl @Inject constructor(
    private val adviserRemoteDataSource: AdviserRemoteDataSource,
): AdviserRepository {
    override suspend fun getAdviserFreeTimesByDate(params: GetAdviserFreeTimesByDataParams): Result<List<AdviserTime>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = adviserRemoteDataSource.getAdviserFreeTimesByDate(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getAdviser(params: GetAdviserParams): Result<Adviser, Failure> {
        return withContext(Dispatchers.IO) {
            try {
               val result = adviserRemoteDataSource.getAdviser(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getAdvisers(params: GetAdvisersParams): Result<List<Adviser>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = adviserRemoteDataSource.getAdvisers(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getAdviceCenters(params: GetAdviceCentersParams): Result<List<AdviceCenter>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = adviserRemoteDataSource.getAdviceCenters(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getAdviceCenter(params: GetAdviceCenterParams): Result<AdviceCenter, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = adviserRemoteDataSource.getAdviceCenter(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

}