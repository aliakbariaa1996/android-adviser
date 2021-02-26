package ir.org.tavanesh.data.platform.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.org.tavanesh.data.models.CityEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: CityEntity)

    @Query("select COUNT(id) from tbl_city ")
    fun countRecords(): Int

    @Query("select * from tbl_city where parentId= 0")
    fun getProvinces(): List<CityEntity>

    @Query("SELECT * FROM tbl_city where parentId= :provinceId and parentId!= 0")
    fun getCities(provinceId:Int):  List<CityEntity>

}