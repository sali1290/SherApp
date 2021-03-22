package ir.rahnama.sherapp.application

import android.app.Application
import com.orhanobut.hawk.Hawk

class HawkApplicationInit : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        if(!Hawk.contains("logIn")){
            Hawk.put("logIn" , 0)
            Hawk.put("negareCount" , 100)
        }
    }

}