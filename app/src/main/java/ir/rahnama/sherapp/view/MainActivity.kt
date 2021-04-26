package ir.rahnama.sherapp.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.core.graphics.drawable.DrawableCompat.applyTheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orhanobut.hawk.Hawk
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.repository.Repository
import ir.rahnama.sherapp.view.MainActivity.ThemeManager.DARK_MODE
import ir.rahnama.sherapp.view.MainActivity.ThemeManager.LIGHT_MODE
import ir.rahnama.sherapp.view.MainActivity.ThemeManager.applyTheme
import ir.rahnama.sherapp.view.adapter.ImageSliderAdapter
import ir.rahnama.sherapp.viewmodel.PoetryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_show_poems.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){


    lateinit var image_slider: SliderView


    //this method set font for all view in app
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ViewPumpContextWrapper.wrap(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        when(getSharedPreferences("Theme", Context.MODE_PRIVATE).getString("theme", "def")){
            "Light" -> {
                applyTheme(LIGHT_MODE)
            }
            "Dark" -> {
                applyTheme(DARK_MODE)
            }
            "System default" -> {
                applyTheme("System")
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // image_slider=findViewById(R.id.imageSlider)


        /* //همه اینا برای تست اسلایدر است و باید تغییر کند
        val icon = BitmapFactory.decodeResource(resources, R.drawable.image_test)
        val images:MutableList<Bitmap> = arrayListOf()
        images.add(icon)
        images.add(icon)
        images.add(icon)
        val adapter = ImageSliderAdapter(this,images)
        image_slider.setSliderAdapter(adapter)
        image_slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        image_slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        image_slider.startAutoCycle();*/

    }

    fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    fun toggleTheme(isDark: Boolean): Boolean {
        val mode = when (isDark) {
            true -> LIGHT_MODE
            false -> DARK_MODE
        }
        applyTheme(mode)
        return true
    }

    object ThemeManager {

        const val LIGHT_MODE = "Light"
        const val DARK_MODE = "Dark"
        private const val AUTO_BATTERY_MODE = "Auto-battery"
        private const val FOLLOW_SYSTEM_MODE = "System"

        fun applyTheme(themePreference: String) {
            when (themePreference) {
                LIGHT_MODE -> setDefaultNightMode(MODE_NIGHT_NO)
                DARK_MODE -> setDefaultNightMode(MODE_NIGHT_YES)
                AUTO_BATTERY_MODE -> setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY)
                FOLLOW_SYSTEM_MODE -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

}