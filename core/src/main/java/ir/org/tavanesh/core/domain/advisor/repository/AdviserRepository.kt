package ir.org.tavanesh.core.domain.advisor.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.utils.Failure

interface AdviserRepository {
    suspend fun getAdviserFreeTimesByDate(params: GetAdviserFreeTimesByDataParams): Result<List<AdviserTime>, Failure>

    suspend fun getAdviser(params: GetAdviserParams): Result<Adviser, Failure>

    suspend fun getAdvisers(params: GetAdvisersParams): Result<List<Adviser>, Failure>

    suspend fun getAdviceCenters(params: GetAdviceCentersParams): Result<List<AdviceCenter>, Failure>

    suspend fun getAdviceCenter(params: GetAdviceCenterParams): Result<AdviceCenter, Failure>
}