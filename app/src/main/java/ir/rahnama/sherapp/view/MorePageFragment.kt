package ir.rahnama.sherapp.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import kotlinx.android.synthetic.main.main_fab_menu_layout.view.*

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

        view.contactUs_image.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.fromParts("mailto" , "test@gmail.com" , null))
            startActivity(intent)
        })
        view.contactUs_tv.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.fromParts("mailto" , "test@gmail.com" , null))
            startActivity(intent)
        }

    }

    private fun showResourceDialog(){
        val dialog : Dialog = Dialog(requireActivity())
        dialog.setTitle("منابع")
        dialog.setContentView(R.layout.resource_dialog)
        dialog.show()
    }

}

