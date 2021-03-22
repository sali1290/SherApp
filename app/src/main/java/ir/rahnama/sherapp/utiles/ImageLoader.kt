package ir.rahnama.sherapp.utiles

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.rahnama.sherapp.R


fun getProgressDrawable(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 6f
        centerRadius = 25f
        start()
    }
}



fun ImageView.loadImage (uri : String?, progressDrawable: CircularProgressDrawable){
    uri?.let {
        val options : RequestOptions = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher)
        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }

}


@BindingAdapter("android:imageLoader")
fun LoadImage ( view : ImageView , url : String ) {
    view.loadImage(url , getProgressDrawable(view.context))
}