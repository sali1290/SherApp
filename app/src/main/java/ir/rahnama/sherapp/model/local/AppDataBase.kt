package ir.rahnama.sherapp.model.local

import android.content.Context
import androidx.room.*
import ir.rahnama.sherapp.model.*

@Database(entities = [PoetModel::class ,
    PosterModel::class,
    SelectionPoetryModel::class ,
    BookModel::class, PoemBodyModel::class ,BookContentModel::class ,
                     Subscribtion::class , Negare::class],version = 1
)
abstract class AppDataBase : RoomDatabase() {

     abstract val poetryDao :PoetryDao
     abstract val posterDao :PosterDao
     abstract val selectionPoetryDao :SelectionPoetryDao
     abstract val booksDao :BooksDao
     abstract val poemBodyDao :PoemBodyDao
     abstract val booksContentDao :BooksContentDao
     abstract val subscribtion : SubscriptionDao
     abstract val negare : NegareDao

     companion object{

         @Volatile
         private  var INSTANCE:AppDataBase? = null
         fun getInstance(context: Context):AppDataBase{
             synchronized(this){
                 var instance = INSTANCE
                 if (instance == null) instance = Room.databaseBuilder(context.applicationContext
                 ,AppDataBase::class.java,"AppDataBase").build()
                 return instance
             }

         }

     }


}