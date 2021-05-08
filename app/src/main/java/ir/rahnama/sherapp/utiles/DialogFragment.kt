package ir.rahnama.sherapp.utiles

import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import com.jem.rubberpicker.RubberSeekBar
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.view.HomeFragmentDirections
import ir.rahnama.sherapp.view.MainActivity
import kotlinx.android.synthetic.main.abjad_fab_layout.view.*
import kotlinx.android.synthetic.main.brightness_main_fab_layout.*
import kotlinx.android.synthetic.main.choose_theme_main_fab_layout.view.*
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.*
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.colorWheel
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.view.image_background_dialog_size
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.view.text_back_color_dialog
import kotlinx.android.synthetic.main.color_text_mian_fab_layout.*
import kotlinx.android.synthetic.main.color_text_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.font_size_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.font_size_mian_fab_layout.view.btn_save_dialog_font
import kotlinx.android.synthetic.main.font_size_mian_fab_layout.view.image_back_font_dialog_size
import kotlinx.android.synthetic.main.fragment_more.view.*
import kotlinx.android.synthetic.main.fragment_show_ticket_content.view.*
import kotlinx.android.synthetic.main.height_size_text_fab.view.*
import kotlinx.android.synthetic.main.height_size_text_fab.view.btn_save_dialog_height_size
import kotlinx.android.synthetic.main.height_size_text_fab.view.image_back_dialog_height_size
import kotlinx.android.synthetic.main.main_fab_menu_layout.view.*
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.btn_save_dialog_size
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.image_back_dialog_size
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.text_size_dialog
import kotlinx.android.synthetic.main.sub_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.volume_button_fab.view.*


class MainDialogFragmentPopUp : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fab_menu_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.shop_ImageView.setOnClickListener {
            loadFragments(view.shop_ImageView.id, view)
            dialog?.dismiss()
        }

        //abjad
        view.abjad_mainFab_Linear.setOnClickListener {
            dialog?.dismiss()
            AbjadFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        view.settings_Fab_Lieaner.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }
        view.poem_fav_popup.setOnClickListener {
            dialog?.dismiss()
            loadFragments(view.poem_fav_popup.id, view)
        }
        view.ticket_mainFab_Linear.setOnClickListener {
            loadFragments(R.id.ticket_mainFab_Linear, view)
            dialog?.dismiss()
        }
        view.fal_hafez_mainFab_Linear.setOnClickListener {
            loadFragments(R.id.fal_hafez_mainFab_Linear , view)
            dialog?.dismiss()
        }
        view.search_mainFab_Linear.setOnClickListener {

            //dialog?.dismiss()
        }
        view.dictionary_mainFab_Linear.setOnClickListener{
            val toast = Toast(requireActivity())
            toast.setText("این ویژگی فعلا در دسترس نیست!")
            toast.duration = Toast.LENGTH_LONG
            toast.show()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun loadFragments(id: Int, view: View) {
        when (id) {
            R.id.shop_ImageView -> {
                val action = HomeFragmentDirections.actionHomeToShop("shop", 0)
                view.let { NavHostFragment.findNavController(this).navigate(action) }
            }
            R.id.poem_fav_popup -> {
                val action = HomeFragmentDirections.actionHomeFragmentToFragmentFav()
                view.let { NavHostFragment.findNavController(this).navigate(action) }
            }
            R.id.ticket_mainFab_Linear -> {
                val action = HomeFragmentDirections.actionHomeToTicket()
                view.let { NavHostFragment.findNavController(this).navigate(action) }
            }
            R.id.fal_hafez_mainFab_Linear -> {
                val action = HomeFragmentDirections.actionHomeFragmentToFalHafzFragment()
                view.let { NavHostFragment.findNavController(this).navigate(action) }
            }
        }
    }
}

class SubDialogFragmentPopUp : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sub_mian_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Text Size
        view.fab_font_size_popup.setOnClickListener {
            dialog?.dismiss()
            SizeDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }


        //Home
        view.back_subFab_Linear.setOnClickListener {
            dialog?.dismiss()
            MainDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        //Font Text
        view.fab_font_text_view_popup.setOnClickListener {
            dialog?.dismiss()
            FontSizeDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        //Color Text
        view.fab_color_text_popup.setOnClickListener {
            dialog?.dismiss()
            ColorTextDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        //Background Color
        view.fab_background_color_popup.setOnClickListener {
            dialog?.dismiss()
            BackgroundTextDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        //brightness
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.lighting_subFab_Linear.setOnClickListener {
                dialog?.dismiss()
                BrightnessFragmentPopUp().show(
                    requireActivity().supportFragmentManager,
                    "popup"
                )
            }
        } else
            view.lighting_subFab_Linear.visibility = View.INVISIBLE

        //height
        view.fontMargin_subFab_Linear.setOnClickListener {
            dialog?.dismiss()
            HeightSizeDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )

        }

        //more
        view.more_subFab_Linear.setOnClickListener {
            dialog?.dismiss()
            loadFragments(R.id.setting_ImageView, view)
        }

        //theme
        view.theme_subFab_Linear.setOnClickListener {
            dialog?.dismiss()
            ThemeChooserPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    //for more page
    private fun loadFragments(id: Int, view: View) {
        when (id) {
            R.id.setting_ImageView -> {
                val action = HomeFragmentDirections.actionHomeFragmentToMorePageFragment()
                view?.let { NavHostFragment.findNavController(this).navigate(action) }
            }
        }
    }

}


class SizeDialogFragmentPopUp : DialogFragment() {

    private var sizeNum = 16
    private var bb: Boolean? = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.size_mian_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        view.image_minus_size_text.setOnClickListener {

            sizeNum--
            view.text_size_dialog.textSize = sizeNum.toFloat()
            view.text_number_font_size.text = sizeNum.toString()


        }

        view.image_plus_size_text.setOnClickListener {

            sizeNum++
            view.text_size_dialog.textSize = sizeNum.toFloat()
            view.text_number_font_size.text = sizeNum.toString()

        }

        view.text_number_font_size.text = sizeNum.toString()

        view.btn_save_dialog_size.setOnClickListener {
            val shared: SharedPreferences =
                context?.getSharedPreferences("shared", MODE_PRIVATE)!!
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putInt("text_size", sizeNum)
            bb?.let { it1 -> editor.putBoolean("bb", it1) }
            editor.apply()
            dialog?.dismiss()

        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}

class HeightSizeDialogFragmentPopUp : DialogFragment() {

    private var heightspace = 16
    private var bbh: Boolean? = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.height_size_text_fab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_dialog_height_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        view.image_minus_height_size_text.setOnClickListener {

            heightspace--
            view.text_number_height_size.text = heightspace.toString()

        }

        view.image_plus_height_size_text.setOnClickListener {

            heightspace++
            view.text_number_height_size.text = heightspace.toString()

        }

        view.text_number_height_size.text = heightspace.toString()

        view.btn_save_dialog_height_size.setOnClickListener {
            val shared: SharedPreferences =
                context?.getSharedPreferences("shared", MODE_PRIVATE)!!
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putInt("height_size", heightspace)
            bbh?.let { it1 -> editor.putBoolean("bbh", it1) }
            editor.apply()
            dialog?.dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}

class FontSizeDialogFragmentPopUp : DialogFragment() {
    private var fontId: Int? = null
    private var typeface: Typeface? = null
    private var bb: Boolean? = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.font_size_mian_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_font_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        view.radio_group_font_text.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {

                R.id.radio_button_bnazanin_font -> {
                    fontId = R.font.bnazanin

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        typeface = resources.getFont(fontId!!)
                        view.text_size_dialog.typeface = typeface
                    } else {
                        typeface = context?.let { ResourcesCompat.getFont(it, fontId!!) }
                        view.text_size_dialog.typeface = typeface
                    }

                }

                R.id.radio_button_vazir_font -> {
                    fontId = R.font.vazir

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        typeface = resources.getFont(fontId!!)
                        view.text_size_dialog.typeface = typeface
                    } else {
                        typeface = context?.let { ResourcesCompat.getFont(it, fontId!!) }
                        view.text_size_dialog.typeface = typeface
                    }

                }

                R.id.radio_button_tahoma_font -> {
                    fontId = R.font.tahoma

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        typeface = resources.getFont(fontId!!)
                        view.text_size_dialog.typeface = typeface
                    } else {
                        typeface = context?.let { ResourcesCompat.getFont(it, fontId!!) }
                        view.text_size_dialog.typeface = typeface
                    }

                }

                R.id.radio_button_sahel_font -> {
                    fontId = R.font.sahel

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        typeface = resources.getFont(fontId!!)
                        view.text_size_dialog.typeface = typeface
                    } else {
                        typeface = context?.let { ResourcesCompat.getFont(it, fontId!!) }
                        view.text_size_dialog.typeface = typeface
                    }

                }


            }

        }

        view.btn_save_dialog_font.setOnClickListener {
            val shared: SharedPreferences =
                context?.getSharedPreferences("shared_font", MODE_PRIVATE)!!
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putInt("font_text", fontId!!)
            bb?.let { it1 -> editor.putBoolean("bbf", it1) }
            editor.apply()
            dialog?.dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}

class ColorTextDialogFragmentPopUp : DialogFragment() {
    private var bb: Boolean? = true
    var color: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.color_text_mian_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_font_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }
        colorWheel.rgb = Color.rgb(13, 37, 42)

        colorWheel.colorChangeListener = { rgb: Int ->
            val startColor = Color.WHITE
            val endColor = Color.BLACK
            verticalgradientSeekBar.startColor = startColor
            verticalgradientSeekBar.setColors(rgb, startColor)
            horizontalgradientSeekBar.endColor = endColor
            horizontalgradientSeekBar.setColors(rgb, endColor)

            view.text_color_dialog.setTextColor(rgb)

        }
        verticalgradientSeekBar.colorChangeListener = { _: Float, argb: Int ->

            view.text_color_dialog.setTextColor(argb)
        }

        horizontalgradientSeekBar.colorChangeListener = { _: Float, argb: Int ->

            view.text_color_dialog.setTextColor(argb)
        }

        view.btn_save_dialog_color.setOnClickListener {
            color = view.text_color_dialog.currentTextColor
            val shared: SharedPreferences =
                context?.getSharedPreferences("shared_color", MODE_PRIVATE)!!
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putInt("text_color", color!!)
            bb?.let { it1 -> editor.putBoolean("bbc", it1) }
            editor.apply()
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

class BackgroundTextDialogFragmentPopUp : DialogFragment() {
    private var bb: Boolean? = true
    var background: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.color_background_text_mian_fab_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorWheel.rgb = Color.rgb(13, 37, 42)
        view.image_background_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        colorWheel.colorChangeListener = { rgb: Int ->

            view.text_back_color_dialog.setTextColor(rgb)
            val startColor = Color.WHITE
            val endColor = Color.BLACK
            backverticalgradientSeekBar.startColor = startColor
            backverticalgradientSeekBar.setColors(rgb, startColor)
            backhorizontalgradientSeekBar.endColor = endColor
            backhorizontalgradientSeekBar.setColors(rgb, endColor)

        }

        backverticalgradientSeekBar.colorChangeListener = { _: Float, argb: Int ->

            view.text_back_color_dialog.setTextColor(argb)
        }

        backhorizontalgradientSeekBar.colorChangeListener = { _: Float, argb: Int ->

            view.text_back_color_dialog.setTextColor(argb)
        }

        view.btn_save_dialog_background?.setOnClickListener {
            background = view.text_back_color_dialog.currentTextColor
            val shared: SharedPreferences =
                context?.getSharedPreferences("shared_background_color", MODE_PRIVATE)!!
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putInt("background_color", background!!)
            bb?.let { it1 -> editor.putBoolean("bbcg", it1) }
            editor.apply()
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )

        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

class BrightnessFragmentPopUp : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.brightness_main_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.System.canWrite(context)) {

                startManageWriteSettingsPermission()

            } else {
                brightnessChanger()
            }
        }

        view.image_background_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

    }

    private fun startManageWriteSettingsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent(
                Settings.ACTION_MANAGE_WRITE_SETTINGS,
                Uri.parse("package:${context?.packageName}")
            ).let {
                startActivityForResult(it, REQUEST_CODE_WRITE_SETTINGS_PERMISSION)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_WRITE_SETTINGS_PERMISSION -> {
                if (context?.canWriteSettings == true) {
                    brightnessChanger()
                } else {
                    Toast.makeText(
                        context,
                        "Write settings permission is not granted!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private val Context.canWriteSettings: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.System.canWrite(this)

    companion object {
        private const val REQUEST_CODE_WRITE_SETTINGS_PERMISSION = 5
    }

    private fun brightnessChanger() {

        rubberSeekBar?.setCurrentValue(
            (Settings.System.getInt(
                context?.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS
            ) / 2.5).toInt()
        )
        rubberSeekBar?.setOnRubberSeekBarChangeListener(object :
            RubberSeekBar.OnRubberSeekBarChangeListener {
            override fun onProgressChanged(seekBar: RubberSeekBar, value: Int, fromUser: Boolean) {
                Settings.System.putInt(
                    context?.contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS, (value * 2.5).toInt()
                )
            }

            override fun onStartTrackingTouch(seekBar: RubberSeekBar) {}
            override fun onStopTrackingTouch(seekBar: RubberSeekBar) {}
        })
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

class NextVolumeFragmentPopUp : DialogFragment() {

    private var bb: Boolean? = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.volume_button_fab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_dialog_volume.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }
        /*val shared: SharedPreferences =
                context?.getSharedPreferences("shared", MODE_PRIVATE)!!
        val editor: SharedPreferences.Editor = shared.edit()
        editor.putInt("situation", )
        bb?.let { it1 -> editor.putBoolean("bb", it1) }
        editor.apply()
        dialog?.dismiss()*/

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}

class ThemeChooserPopUp : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_theme_main_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_theme_dialog.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        when (view.context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
            .getString("theme", "def")) {
            "System default" -> {
                view.radio_group_theme.check(R.id.radio_button_system_theme)
            }
            "Light" -> {
                view.radio_group_theme.check(R.id.radio_button_light_theme)
            }
            "Dark" -> {
                view.radio_group_theme.check(R.id.radio_button_night_theme)
            }
        }


        view.radio_group_theme.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_button_light_theme) {
                if (MainActivity().isDarkTheme(requireActivity())) {
                    view.context?.getSharedPreferences("Theme", Context.MODE_PRIVATE)
                        ?.edit()
                        ?.putString("theme", "Light")?.apply()
                    MainActivity().toggleTheme(MainActivity().isDarkTheme(requireActivity()))
                }
            } else if (checkedId == R.id.radio_button_night_theme) {
                if (!MainActivity().isDarkTheme(requireActivity())) {
                    view?.context?.getSharedPreferences("Theme", Context.MODE_PRIVATE)
                        ?.edit()
                        ?.putString("theme", "Dark")?.apply()
                    MainActivity().toggleTheme(MainActivity().isDarkTheme(requireActivity()))
                }
            } else if (checkedId == R.id.radio_button_system_theme) {
                view?.context?.getSharedPreferences("Theme", Context.MODE_PRIVATE)
                    ?.edit()
                    ?.putString("theme", "System default")?.apply()
                MainActivity.ThemeManager.applyTheme("System")
            }
        })



    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}

class AbjadFragmentPopUp : DialogFragment() {

    private val abjad = mapOf(
        "ا" to 1 , "ب" to 2 ,"ج" to 3 , "د" to 4 , "ه" to 5 , "و" to 6 ,
        "ز" to 7 , "ح" to 8 , "ط" to 9 , "ی" to 10 , "ک" to 20 , "ل" to 30,
        "م" to 40 , "ن" to 50 , "س" to 60, "ع" to 70 , "ق" to 80 , "ص" to 90 , "ق" to 100,
        "ر" to 200 , "ش" to 300 , "ت" to 400 , "ث" to 500 , "خ" to 600 , "ذ" to 700 , "ض" to 800,
        "ظ" to 900, "غ" to 1000, "گ" to 2000, "چ" to 3000, "پ" to 4000, "ژ" to 5000,
        )

    private val mp = mapOf(
        "a" to 1 , "b" to 2 , "c" to 3 , "d" to 4
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.abjad_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.image_back_dialog_abjad.setOnClickListener {
            dialog?.dismiss()
            MainDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        view.btn_save_dialog_abjad.setOnClickListener {


            val pT = view.abjad_input.text.toString()
            val len = pT.length
            val chars:CharArray = pT.toCharArray()
            val resultArray: MutableList<Int> = arrayListOf()

            for (i in 0 until len) {

                resultArray.add(abjad.getValue(chars[i].toString()))
            }
            if (resultArray.size == len) {
                var submit = 0
                for (element in resultArray) {
                    submit += element

                }

                view.text_result_abjad.text = submit.toString()

            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}