package ir.rahnama.sherapp.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
}