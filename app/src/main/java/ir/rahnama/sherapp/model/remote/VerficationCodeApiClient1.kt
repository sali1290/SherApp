package ir.rahnama.sherapp.model.remote

import ir.rahnama.sherapp.model.TicketListModel
import ir.rahnama.sherapp.model.TicketMessageModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface VerficationCodeApiClient1 {

    @POST("verifyValidationCode.php")
    @FormUrlEncoded
     fun verifyValidationCode(@Field("phone") phone :String, @Field("code") code :String, @Field("name") name :String): Call<Int>

    @POST("sendValidationCode.php")
    @FormUrlEncoded
     fun sendValidationCode( @Field("phone") phone :String): Call<Void>

    @POST("AddTicket.php")
    @FormUrlEncoded
     fun addTicket( @Field("name") name :String , @Field("phone") phone :String , @Field("title") title :String , @Field("ticket_category") category :String ):Call<Void>

    @POST("get_ticket_list.php")
    @FormUrlEncoded
     fun getTicketList( @Field("phone") phone :String):Call<MutableList<TicketListModel>>

    @POST("close_ticket.php")
    @FormUrlEncoded
     fun closeTicket( @Field("ticket_id") ticket_id :Int):Call<Void>


    @POST("user_message.php")
    @FormUrlEncoded
     fun sendMessage( @Field("ticket_id") ticket_id :Int , @Field("message") message :String ):Call<Void>


    @POST("get_all_message.php")
    @FormUrlEncoded
     fun getAllMessages( @Field("ticket_id") ticket_id :Int):Call<MutableList<TicketMessageModel>>


    @POST("support_message_seen.php")
    @FormUrlEncoded
     fun MessageSeen( @Field("ticket_id") ticket_id :Int):Call<Void>

}