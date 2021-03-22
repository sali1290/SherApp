package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rahnama.sherapp.model.BookModel

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books:MutableList<BookModel>)

    @Query("DELETE FROM BOOKS")
    suspend fun deleteAll()

    @Query("SELECT * FROM BOOKS WHERE kind = :category_id")
    fun getBooksList(category_id:String): LiveData<MutableList<BookModel>>
}