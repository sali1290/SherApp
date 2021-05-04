package ir.rahnama.sherapp.utiles

sealed class ResultHandler<out R> {

        data class Success<out T>(val data: T) : ResultHandler<T>()

        data class Error(val message: String) : ResultHandler<Nothing>()

        object Loading : ResultHandler<Nothing>()

}