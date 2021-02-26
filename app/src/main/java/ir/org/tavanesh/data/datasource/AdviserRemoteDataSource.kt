package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.domain.advisor.repository.*

interface AdviserRemoteDataSource {
    suspend fun getAdviserFreeTimesByDate(params: GetAdviserFreeTimesByDataParams): List<AdviserTime>

    suspend fun getAdviser(params: GetAdviserParams): Adviser

    suspend fun getAdvisers(params: GetAdvisersParams): List<Adviser>

    suspend fun getAdviceCenters(params: GetAdviceCentersParams): List<AdviceCenter>

    suspend fun getAdviceCenter(params: GetAdviceCenterParams): AdviceCenter
}