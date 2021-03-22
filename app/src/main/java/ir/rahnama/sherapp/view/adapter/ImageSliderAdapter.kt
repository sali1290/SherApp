package ir.rahnama.sherapp.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import ir.rahnama.sherapp.R


 class ImageSliderAdapter(var context: Context, var images:MutableList<Bitmap>) :
    SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH>() {



    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflater = LayoutInflater.from(context)
        return SliderAdapterVH(inflater.inflate(R.layout.slider_item,parent, false))
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder!!.image.setImageBitmap(images[position])
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return images.size
    }

    inner class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
       var image :ImageView = itemView.findViewById(R.id.imageview)
    }


}







