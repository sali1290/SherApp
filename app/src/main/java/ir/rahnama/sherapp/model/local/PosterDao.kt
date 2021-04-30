package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rahnama.sherapp.model.PosterModel

@Dao
interface PosterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(poster:MutableList<PosterModel>)

    @Query("DELETE FROM poster")
    suspend fun deleteAll()

    @Query("SELECT * FROM poster")
    fun getAllPoster(): LiveData<MutableList<PosterModel>>


}