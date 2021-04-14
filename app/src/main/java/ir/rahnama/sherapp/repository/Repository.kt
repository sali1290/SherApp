package ir.rahnama.sherapp.repository


import ir.rahnama.sherapp.model.local.*
import ir.rahnama.sherapp.model.remote.ApiClient
import ir.rahnama.sherapp.utiles.DataSourceOperation
import javax.inject.Inject


class Repository @Inject constructor(
     val poetryDao: PoetryDao ,
     val selectionPoetryDao: SelectionPoetryDao ,
     val booksDao: BooksDao,
     val poemBodyDao: PoemBodyDao,
     val booksContentDao: BooksContentDao,
     val subscribtion: SubscriptionDao ,
     val negar : NegareDao ,
     val apiClient: ApiClient) {



    fun getPoetryNames(type:String) = DataSourceOperation(
        getFromLocal = {poetryDao.getPoetryNames(type)} ,
        getFromNetwork = {apiClient.getPoetryName()},
        saveNetworkResult = {poetryDao.insert(it)}
    )


    fun getSelectionPoetry() = DataSourceOperation(
        getFromLocal = {selectionPoetryDao.getPoetryNames()} ,
        getFromNetwork = {apiClient.getSelectionPoetry()},
        saveNetworkResult = {selectionPoetryDao.insert(it)}
    )


    fun getBooksList(category_id :String) = DataSourceOperation(
        getFromLocal = {booksDao.getBooksList(category_id)} ,
        getFromNetwork = {apiClient.getPoetryBooks(category_id)},
        saveNetworkResult = {booksDao.insert(it)}
    )


    fun getBooksContent(category_id :String) = DataSourceOperation(
        getFromLocal = {booksContentDao.getBooksContent(category_id)} ,
        getFromNetwork = {apiClient.getBookContent(category_id)},
        saveNetworkResult = {booksContentDao.insert(it)}
    )

    fun getPoemById(poem_id :String) = DataSourceOperation(
        getFromLocal = {poemBodyDao.getPoemBody(poem_id)} ,
        getFromNetwork = {apiClient.getPoemById(poem_id)},
        saveNetworkResult = {poemBodyDao.insert(it)}
    )

     fun getLastSeenPoem(poem_id:String) = poemBodyDao.getPoemBody(poem_id)

    fun getNegar() = DataSourceOperation(
        getFromLocal = {negar.getAllNegare()} ,
        getFromNetwork = {apiClient.getAllNegare()},
        saveNetworkResult = {negar.insert(it)}
    )

    fun getSubs() = DataSourceOperation(
        getFromLocal = {subscribtion.getAllSubscriptions()} ,
        getFromNetwork = {apiClient.getAllSubscription()} ,
        saveNetworkResult = {subscribtion.insert(it)}
    )

}