package com.klemer.photoexplore.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.klemer.photoexplore.MainActivity
import com.klemer.photoexplore.R
import com.klemer.photoexplore.adapters.ImagesAdapter
import com.klemer.photoexplore.databinding.ImagesFragmentsBinding
import com.klemer.photoexplore.extensions.hideKeyboard
import com.klemer.photoexplore.helpers.AutoGridLayout
import com.klemer.photoexplore.interfaces.ImageClickListener
import com.klemer.photoexplore.models.PixaBayImage
import com.klemer.photoexplore.viewmodels.ImagesFragmentViewModel

class ImagesFragments : Fragment(R.layout.images_fragments), ImageClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ImagesFragmentViewModel
    private lateinit var searchEditText: TextInputEditText
    private lateinit var buttonSearch: ImageView
    private lateinit var fragContext: Context
    private lateinit var binding: ImagesFragmentsBinding
    private lateinit var progressBar: ProgressBar

    private val observerImages = Observer<List<PixaBayImage>> {
        recyclerView.adapter = ImagesAdapter(it, this)
        showProgress(false)
    }

    companion object {
        fun newInstance() = ImagesFragments()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragContext = requireContext()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ImagesFragmentsBinding.bind(view)

        viewModel =
            ViewModelProvider(this)[ImagesFragmentViewModel::class.java]

        viewModel.images.observe(viewLifecycleOwner, observerImages)

        loadComponents()

        getLastImages()

    }

    override fun onStart() {
        super.onStart()
        bindNavItems()
    }

    @SuppressLint("RtlHardcoded")
    private fun bindNavItems() {

        val act = requireActivity() as MainActivity

        searchEditText = act.findViewById(R.id.editTextSearch)
        buttonSearch = act.findViewById(R.id.btnSearch)

        buttonSearch.setOnClickListener {

            showProgress(true)

            viewModel.searchImages(searchEditText.text.toString())
            act.hideKeyboard(it)
            searchEditText.clearFocus()
        }
    }

    private fun loadComponents() {
        recyclerView = binding.recyclerViewImages
        recyclerView.layoutManager = AutoGridLayout(requireContext(), 180)
        progressBar = binding.progressBar
    }

    private fun getLastImages() {
        showProgress(true)
        viewModel.getLastImages()
    }

    override fun onImageClick(image: PixaBayImage) {
        val bundle = Bundle()

        bundle.putSerializable("image", image)

        val fragment = ImageDetailFragment.newInstance()

        fragment.arguments = bundle

        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .hide(this)
            .add(R.id.container_root, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}