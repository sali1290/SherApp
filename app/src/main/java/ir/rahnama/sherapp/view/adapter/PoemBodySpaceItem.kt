package ir.rahnama.sherapp.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Rect
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class PoemBodySpaceItem: RecyclerView.ItemDecoration(){

    private var space by Delegates.notNull<Int>()
    private lateinit var shared: SharedPreferences

    override fun getItemOffsets(
        @NonNull outRect: Rect,
        @NonNull view: View,
        @NonNull parent: RecyclerView,
        @NonNull state: RecyclerView.State
    ) {
        shared=view.context.getSharedPreferences("shared",Context.MODE_PRIVATE)
        space = shared.getInt("height_size",0)
        val bbh:Boolean = shared.getBoolean("bbh",false)

        if (bbh) {


            val itemPosition = parent.getChildAdapterPosition(view)
            val itemCount = state.itemCount

            outRect.bottom = space

        }

    }





}