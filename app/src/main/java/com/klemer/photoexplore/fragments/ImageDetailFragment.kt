package com.klemer.photoexplore.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.klemer.photoexplore.R
import com.klemer.photoexplore.databinding.ImageDetailFragmentBinding
import com.klemer.photoexplore.enums.Default
import com.klemer.photoexplore.models.PixaBayImage
import com.klemer.photoexplore.viewmodels.ImageDetailViewModel
import com.squareup.picasso.Picasso

class ImageDetailFragment : Fragment(R.layout.image_detail_fragment) {

    private lateinit var binding: ImageDetailFragmentBinding
    private lateinit var viewModel: ImageDetailViewModel

    companion object {
        fun newInstance() = ImageDetailFragment()
    }

    private val observerImage = Observer<PixaBayImage> { image ->
        loadImageInfo(image)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ImageDetailFragmentBinding.bind(view)

        viewModel = ViewModelProvider(this)[ImageDetailViewModel::class.java]
        viewModel.image.observe(viewLifecycleOwner, observerImage)

        viewModel.treatArguments(arguments)

    }

    private fun loadImageInfo(image: PixaBayImage) {
        binding.imageViewDetail.apply {
            Picasso
                .get()
                .load(image.largeImage)
                .into(this)
        }

        binding.userAvatar.apply {
            val avatar = if (image.userAvatar.isEmpty()) Default.AVATAR.url else image.userAvatar

            Picasso
                .get()
                .load(avatar)
                .into(this)
        }

        binding.txtDownloadsCount.apply {
            text = image.downloadsCount
        }

        binding.txtUserUploader.apply {
            text = image.userName
        }

        binding.txtViewDimensions.apply {
            text = "${image.imageWidth}x${image.imageHeight}"
        }
    }
}