package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.rahnama.sherapp.model.PoemBodyModel


@Dao
interface PoemBodyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (poemBody: MutableList<PoemBodyModel>)

    @Query("DELETE FROM poem_body")
    suspend fun deleteAll()

    @Query("SELECT * FROM poem_body WHERE category_id = :poem_id")
    fun getPoemBody (poem_id : String) :LiveData<MutableList<PoemBodyModel>>

}