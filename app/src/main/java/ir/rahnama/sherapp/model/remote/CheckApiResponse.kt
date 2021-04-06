package ir.rahnama.sherapp.model.remote

import ir.rahnama.sherapp.utiles.Resource
import retrofit2.Response

abstract class CheckApiResponse  {


    protected suspend fun <T> getResult(call: suspend  () -> Response<T> ) :Resource<T> {

        try {
            val response = call()
            if(response.isSuccessful){
                val data = response.body()
                data?.let { return Resource.success(data) }
            }

            return error("${response.code()} ${response.message()}")

        }catch (e:Exception) {

            return error(e.message ?: e.toString())
        }

    }

    private fun <T> error (message:String) :Resource<T> = Resource.error("NetWork Failed : $message")

}