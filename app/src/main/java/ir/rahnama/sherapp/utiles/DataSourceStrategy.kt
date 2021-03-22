package ir.rahnama.sherapp.utiles

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import ir.rahnama.sherapp.utiles.Resource.Status.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


data class Resource <out T> (val status :Status , val data : T? = null , val message:String? = null){

    enum class Status{SUCCESS,ERROR,LOADING}


    companion object{
    fun <T> success (data:T) :Resource<T> = Resource(SUCCESS,data)
    fun <T> error (message: String?) :Resource<T> = Resource(ERROR, null , message)
    fun <T> loading () :Resource<T> = Resource(LOADING)
    }
}




 fun <T , A > DataSourceOperation(getFromLocal : () -> LiveData<T> ,
                                 getFromNetwork : suspend () -> Resource<A> ,
                                 saveNetworkResult : suspend (A) -> Unit) : LiveData<Resource<T>> =
    liveData(Dispatchers.IO){
        emit(Resource.loading())

        val localSource = getFromLocal.invoke().map { data -> Resource.success(data)}
        emitSource(localSource)


        val responseStatus = getFromNetwork.invoke()

        if (responseStatus.status == SUCCESS){
            saveNetworkResult(responseStatus.data!!)
            Log.i("api",responseStatus.data!!.toString())
        } else if (responseStatus.status == ERROR){
            emit(Resource.error(responseStatus.message!!))
            emitSource(localSource)
        }

    }


class AutoClearedValue<T : Any>(val fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            _value = null
                        }
                    })
                }
            }
        })
    }


    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)


