package ir.rahnama.sherapp.model.remote

import ir.rahnama.sherapp.model.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("get_poster.php")
    suspend fun getPoster ():Response<MutableList<PosterModel>>

    @GET("get_poetry_names.php")
    suspend fun getPoetryList ():Response<MutableList<PoetModel>>

    @GET("get_selection_poetry.php")
    suspend fun getSelectionPoetry () : Response<MutableList<SelectionPoetryModel>>

    @POST("search.php")
    @FormUrlEncoded
    suspend fun getSearchResult (@Field("name") name:String,@Field("id") id:Int):Response<MutableList<SearchModel>>

    @POST("get_poetry_books.php")
    @FormUrlEncoded
    suspend fun getPoetryBooks (@Field("category_id") category_id:Int):Response<MutableList<BookModel>>

    @POST("get_book_content.php")
    @FormUrlEncoded
    suspend fun getBookContent (@Field("category_id") category_id:Int):Response<MutableList<BookContentModel>>

    @POST("get_poem_body.php")
    @FormUrlEncoded
    suspend fun getPoemById (@Field("poem_id") poem_id:Int):Response<MutableList<PoemBodyModel>>


    @GET("get_all_negar.php")
    suspend fun getAllNegare ():Response<MutableList<Negare>>

    @GET("get_all_subscription.php")
    suspend fun getAllSubscription ():Response<MutableList<Subscribtion>>



}