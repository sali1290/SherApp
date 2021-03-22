package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.rahnama.sherapp.model.SelectionPoetryModel


@Dao
interface SelectionPoetryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selectionPoetry:MutableList<SelectionPoetryModel>)

    @Query("DELETE FROM SELECTION_POETRY")
    suspend fun deleteAll()

    @Query("SELECT * FROM SELECTION_POETRY ")
    fun getPoetryNames(): LiveData<MutableList<SelectionPoetryModel>>

}