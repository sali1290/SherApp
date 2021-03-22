package ir.rahnama.sherapp.model.remote

import ir.rahnama.sherapp.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("get_poetry_names.php")
    suspend fun getPoetryList ():Response<MutableList<PoetModel>>

    @GET("get_selection_poetry.php")
    suspend fun getSelectionPoetry () : Response<MutableList<SelectionPoetryModel>>

    @POST("get_poetry_books.php")
    @FormUrlEncoded
    suspend fun getPoetryBooks (@Field("category_id") category_id:String):Response<MutableList<BookModel>>

    @POST("get_book_content.php")
    @FormUrlEncoded
    suspend fun getBookContent (@Field("category_id") category_id:String):Response<MutableList<BookContentModel>>


    @POST("get_poem_body.php")
    @FormUrlEncoded
    suspend fun getPoemById (@Field("poem_id") poem_id:String):Response<MutableList<PoemBodyModel>>


    @GET("get_all_negar.php")
    suspend fun getAllNegare ():Response<MutableList<Negare>>

    @GET("get_all_subscription.php")
    suspend fun getAllSubscription ():Response<MutableList<Subscribtion>>

    @POST("AddTicket.php")
    @FormUrlEncoded
    suspend fun addTicket( @Field("name") name :String , @Field("phone") phone :String , @Field("title") title :String , @Field("ticket_category") category :String ):Call<Void>

    @POST("get_ticket_list.php")
    @FormUrlEncoded
    suspend fun getTicketList( @Field("phone") phone :String):Call<MutableList<TicketListModel>>

    @POST("close_ticket.php")
    @FormUrlEncoded
    suspend fun closeTicket( @Field("ticket_id") ticket_id :Int):Call<Void>


    @POST("user_message.php")
    @FormUrlEncoded
    suspend fun sendMessage( @Field("ticket_id") ticket_id :Int , @Field("message") message :String ):Call<Void>


    @POST("get_all_message.php")
    @FormUrlEncoded
    suspend fun getAllMessages( @Field("ticket_id") ticket_id :Int):Call<MutableList<TicketMessageModel>>


    @POST("support_message_seen.php")
    @FormUrlEncoded
    suspend fun MessageSeen( @Field("ticket_id") ticket_id :Int):Call<Void>

}