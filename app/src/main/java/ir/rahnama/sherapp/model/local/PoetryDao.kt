package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rahnama.sherapp.model.PoetModel

@Dao
interface PoetryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(poetry:MutableList<PoetModel>)

    @Query("DELETE FROM POETRY_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM POETRY_TABLE WHERE poet_type = :poetry_type")
     fun getPoetryNames(poetry_type:String): LiveData<MutableList<PoetModel>>


}