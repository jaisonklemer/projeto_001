package com.klemer.photoexplore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.klemer.photoexplore.R
import com.klemer.photoexplore.interfaces.ImageClickListener
import com.klemer.photoexplore.models.PixaBayImage
import com.squareup.picasso.Picasso

class ImagesAdapter(
    private val images: List<PixaBayImage>,
    private val imageClick: ImageClickListener
) :
    RecyclerView.Adapter<ImagesAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_image, parent, false)
        return ImagesAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImagesAdapterViewHolder, position: Int) {
        holder.bind(images[position])
        holder.itemView.apply {
            setOnClickListener {
                imageClick.onImageClick(images[position])
            }
        }
    }

    override fun getItemCount() = images.size
}

class ImagesAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(image: PixaBayImage) {
        itemView.findViewById<ImageView>(R.id.imageItemList).apply {
            Picasso
                .get()
                .load(image.webFormatImage)
                .into(this)
        }

        if(image.userAvatar.isNotEmpty()){
            itemView.findViewById<ImageView>(R.id.userAvatar).apply {
                Picasso
                    .get()
                    .load(image.userAvatar)
                    .into(this)
            }
        }


        itemView.findViewById<TextView>(R.id.txtUserName).apply {
            text = image.userName
        }
    }

}