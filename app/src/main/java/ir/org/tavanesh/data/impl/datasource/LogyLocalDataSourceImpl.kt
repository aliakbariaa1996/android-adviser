package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.logy.entity.Logy
import ir.org.tavanesh.core.domain.logy.repository.CreateLogyParams
import ir.org.tavanesh.data.datasource.LogyLocalDataSource
import ir.org.tavanesh.data.mappers.toLogy
import ir.org.tavanesh.data.platform.datasource.LogyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogyLocalDataSourceImpl @Inject constructor(
    private val logyDao: LogyDao,
):LogyLocalDataSource{
    override suspend fun createLogy(params: CreateLogyParams): Boolean {
        return withContext(Dispatchers.IO){
            logyDao.insert(params.logy.toLogy())
            true
        }
    }

    override suspend fun clearLogys(): Boolean {
        return withContext(Dispatchers.IO){
            //logyDao.deleteLogys()
            true
        }
    }

    override suspend fun getLogys(): List<Logy> {
        return withContext(Dispatchers.IO){
            val list = logyDao.getLogys()
            list.toLogy()
        }
    }

}