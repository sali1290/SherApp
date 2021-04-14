package ir.rahnama.sherapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rahnama.sherapp.model.BookContentModel

@Dao
interface BooksContentDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookContents:MutableList<BookContentModel>)

    @Query("DELETE FROM book_content")
    suspend fun deleteAll()

    @Query("SELECT * FROM book_content WHERE catgeory_id = :category_id")
     fun getBooksContent (category_id : String) : LiveData<MutableList<BookContentModel>>

}