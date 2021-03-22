package ir.rahnama.sherapp.model.remote

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

}