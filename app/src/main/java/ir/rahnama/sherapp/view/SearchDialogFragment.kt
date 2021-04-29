package ir.rahnama.sherapp.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.skydoves.powerspinner.SpinnerAnimation
import com.skydoves.powerspinner.SpinnerGravity
import com.skydoves.powerspinner.createPowerSpinnerView
import ir.rahnama.sherapp.R
import kotlinx.android.synthetic.main.fragment_search_dialog.view.*
import kotlin.properties.Delegates


class SearchDialogFragment : DialogFragment() {

    private var pos by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}

