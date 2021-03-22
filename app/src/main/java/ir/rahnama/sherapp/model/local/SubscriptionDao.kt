package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rahnama.sherapp.model.Subscribtion


@Dao
interface SubscriptionDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books:MutableList<Subscribtion>)

    @Query("DELETE FROM sub")
    suspend fun deleteAll()

    @Query("SELECT * FROM sub")
    fun getAllSubscriptions(): LiveData<MutableList<Subscribtion>>

}