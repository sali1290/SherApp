package ir.rahnama.sherapp.utiles

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import ir.rahnama.sherapp.R


@HiltAndroidApp
class CustomApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        ViewPump.init(ViewPump.builder().addInterceptor(CalligraphyInterceptor(CalligraphyConfig.Builder()
                            .setDefaultFontPath("sahel.ttf")
                            .setFontAttrId(R.attr.font)
                            .build())).build()
        )

    }

}