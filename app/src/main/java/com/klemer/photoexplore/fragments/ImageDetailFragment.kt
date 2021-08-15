package com.klemer.photoexplore.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.klemer.photoexplore.R
import com.klemer.photoexplore.databinding.ImageDetailFragmentBinding
import com.klemer.photoexplore.models.PixaBayImage
import com.squareup.picasso.Picasso

class ImageDetailFragment : Fragment(R.layout.image_detail_fragment) {

    companion object {
        fun newInstance() = ImageDetailFragment()
    }

    private lateinit var binding: ImageDetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ImageDetailFragmentBinding.bind(view)

        val image = arguments?.getSerializable("image") as PixaBayImage

        loadImageInfo(image)

    }

    private fun loadImageInfo(image: PixaBayImage) {
        binding.imageViewDetail.apply {
            Picasso
                .get()
                .load(image.largeImage)
                .into(this)
        }

        binding.userAvatar.apply {
            Picasso
                .get()
                .load(image.userAvatar)
                .into(this)
        }

        binding.txtDownloadsCount.apply {
            text = image.downloadsCount
        }

        binding.txtUserUploader.apply {
            text = image.userName
        }
    }
}