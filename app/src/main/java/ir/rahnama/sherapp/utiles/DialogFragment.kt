package ir.rahnama.sherapp.utiles

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import ir.rahnama.sherapp.R
import ir.rahnama.sherapp.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.*
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.colorWheel
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.gradientSeekBar
import kotlinx.android.synthetic.main.color_background_text_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.color_text_mian_fab_layout.*
import kotlinx.android.synthetic.main.color_text_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.font_size_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.font_size_mian_fab_layout.view.btn_save_dialog_font
import kotlinx.android.synthetic.main.font_size_mian_fab_layout.view.image_back_font_dialog_size
import kotlinx.android.synthetic.main.main_fab_menu_layout.view.*
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.*
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.btn_save_dialog_size
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.image_back_dialog_size
import kotlinx.android.synthetic.main.size_mian_fab_layout.view.text_size_dialog
import kotlinx.android.synthetic.main.sub_mian_fab_layout.view.*


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
        view.settings_Fab_Lieaner.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }
        view.poem_fav_popup.setOnClickListener {
            dialog?.dismiss()
            loadFragments(view.poem_fav_popup.id,view)
        }

        view.negare_mainFab_Linear.setOnClickListener {
            loadFragments(R.id.negare_mainFab_Linear , view)
            dialog?.dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun loadFragments(id: Int, view: View) {
        when (id) {
            R.id.shop_ImageView -> {
                val action = HomeFragmentDirections.actionHomeToShop("shop" , 0)
                view?.let { NavHostFragment.findNavController(this).navigate(action) }
            }
            R.id.poem_fav_popup -> {
                val action = HomeFragmentDirections.actionHomeFragmentToFragmentFav()
                view?.let { NavHostFragment.findNavController(this).navigate(action) }
            }
            R.id.negare_mainFab_Linear -> {
                val action = HomeFragmentDirections.actionHomeToShop("shop" , 1)
                view?.let { NavHostFragment.findNavController(this).navigate(action) }
            }
            R.id.negare_mainFab_Linear -> {
                val action = HomeFragmentDirections.actionHomeToShop("shop" , 1)
                view?.let { NavHostFragment.findNavController(this).navigate(action) }
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

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}

class SizeDialogFragmentPopUp : DialogFragment() {

    var sizeNum = 16
    var bb: Boolean? = true

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
            val shared: SharedPreferences = context?.getSharedPreferences("shared", MODE_PRIVATE)!!
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

class FontSizeDialogFragmentPopUp : DialogFragment() {
    var fontId: Int? = null
    var typeface: Typeface? = null
    var bb: Boolean? = true

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

        view.radio_group_font_text.setOnCheckedChangeListener { group, checkedId ->

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
    var bb: Boolean? = true
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

        colorWheel.rgb = Color.rgb(13, 37, 42)
        color = colorWheel.rgb
        val startcolor = Color.argb(0,0,0,0)
        val endcolor = Color.argb(0xff,0xff,0xff,0xff)
        gradientSeekBar.startColor = startcolor
        gradientSeekBar.endColor = endcolor
        gradientSeekBar.setColors(startcolor,endcolor)


        view.image_back_font_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        colorWheel.colorChangeListener = { rgb: Int ->

            view.text_color_dialog.setTextColor(rgb)

        view.btn_save_dialog_color.setOnClickListener {
                color = rgb
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

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

class BackgroundTextDialogFragmentPopUp : DialogFragment() {
    var bb: Boolean? = true
    var background: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.color_background_text_mian_fab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorWheel.rgb = Color.rgb(13, 37, 42)
        background = colorWheel.rgb
        val startcolor = Color.argb(0,0,0,0)
        val endcolor = Color.argb(0xff,0xff,0xff,0xff)
        gradientSeekBar.startColor = startcolor
        gradientSeekBar.endColor = endcolor
        gradientSeekBar.setColors(startcolor,endcolor)


        view.image_background_dialog_size.setOnClickListener {
            dialog?.dismiss()
            SubDialogFragmentPopUp().show(
                requireActivity().supportFragmentManager,
                "popup"
            )
        }

        colorWheel.colorChangeListener = { rgb: Int ->

            view.text_back_color_dialog.setTextColor(rgb)

        view.btn_save_dialog_background.setOnClickListener {
                background = rgb
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
    }

        override fun onStart() {
            super.onStart()
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
}





