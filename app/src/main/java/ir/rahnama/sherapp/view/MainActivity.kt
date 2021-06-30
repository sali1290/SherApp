package ir.rahnama.sherapp.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.view.MainActivity.ThemeManager.DARK_MODE
import ir.rahnama.sherapp.view.MainActivity.ThemeManager.LIGHT_MODE
import ir.rahnama.sherapp.view.MainActivity.ThemeManager.applyTheme
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_show_poems.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

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