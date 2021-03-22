package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rahnama.sherapp.model.Negare

@Dao
interface NegareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(negare:MutableList<Negare>)

    @Query("DELETE FROM negar")
    suspend fun deleteAll()

    @Query("SELECT * FROM negar")
    fun getAllNegare(): LiveData<MutableList<Negare>>

}