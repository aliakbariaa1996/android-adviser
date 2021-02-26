package ir.org.tavanesh.data.platform.datasource

import androidx.room.*
import ir.org.tavanesh.data.models.LogyEntity

@Dao
interface LogyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: LogyEntity)

    @Query("select COUNT(id) from tbl_city ")
    fun countRecords(): Int

    @Query("select * from tbl_logy")
    fun getLogys(): List<LogyEntity>

    @Delete
    fun deleteLogys(case: LogyEntity): Int
}