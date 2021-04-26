package ir.rahnama.sherapp.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.NavHostFragment
import ir.rahnama.sherapp.R
import kotlinx.android.synthetic.main.fragment_us_info.view.*

class MorePageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_us_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (view.context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
            .getString("theme", "def")) {
            "System default" -> {
                view.theme.setTextColor(Color.WHITE)
                view.theme_spinner.setBackgroundResource(R.drawable.fab_background_night)
                view.theme_spinner.setPopupBackgroundResource(R.drawable.fab_background_night)
                view.theme_spinner.setSelection(0)
            }
            "Light" -> {
                view.theme_spinner.setSelection(1)
            }
            "Dark" -> {
                view.theme.setTextColor(Color.WHITE)
                view.theme_spinner.setBackgroundResource(R.drawable.fab_background_night)
                view.theme_spinner.setPopupBackgroundResource(R.drawable.fab_background_night)
                view.theme_spinner.setSelection(2)
            }
        }

        view.theme_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //change app theme
                when (position) {
                    0 -> {
                            view?.context?.getSharedPreferences("Theme", Context.MODE_PRIVATE)
                                ?.edit()
                                ?.putString("theme", "System default")?.apply()
                        MainActivity.ThemeManager.applyTheme("System")
                    }

                    1 -> {
                        if (MainActivity().isDarkTheme(requireActivity())) {
                            view?.context?.getSharedPreferences("Theme", Context.MODE_PRIVATE)
                                ?.edit()
                                ?.putString("theme", "Light")?.apply()
                            MainActivity().toggleTheme(MainActivity().isDarkTheme(requireActivity()))
                        }
                    }


                    2 -> {
                        if (!MainActivity().isDarkTheme(requireActivity())) {
                            view?.context?.getSharedPreferences("Theme", Context.MODE_PRIVATE)
                                ?.edit()
                                ?.putString("theme", "Dark")?.apply()
                            MainActivity().toggleTheme(MainActivity().isDarkTheme(requireActivity()))
                        }
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        view.resource_image.setOnClickListener(View.OnClickListener {
            showResourceDialog()
        })
        view.textView_resource.setOnClickListener(View.OnClickListener {
            showResourceDialog() })

        view.about_us_btn.setOnClickListener(View.OnClickListener {
           val action = ActionOnlyNavDirections(R.id.action_morePageFragment_to_aboutUsFragment)
            view?.let{ NavHostFragment.findNavController(this).navigate(action)}
        })
        view.aboutUs_image.setOnClickListener(View.OnClickListener {
            val action = ActionOnlyNavDirections(R.id.action_morePageFragment_to_aboutUsFragment)
            view?.let{ NavHostFragment.findNavController(this).navigate(action)}
        })
    }

    private fun showResourceDialog(){
        val dialog : Dialog = Dialog(requireActivity())
        dialog.setTitle("منابع")
        dialog.setContentView(R.layout.resource_dialog)
        dialog.show()
    }

}

