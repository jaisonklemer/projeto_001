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
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.klemer.photoexplore.BuildConfig
import com.klemer.photoexplore.MainActivity
import com.klemer.photoexplore.R
import com.klemer.photoexplore.adapters.ImagesAdapter
import com.klemer.photoexplore.databinding.ImagesFragmentsBinding
import com.klemer.photoexplore.endpoints.PixaBayEndpoints
import com.klemer.photoexplore.extensions.hideKeyboard
import com.klemer.photoexplore.extensions.showToast
import com.klemer.photoexplore.helpers.AutoGridLayout
import com.klemer.photoexplore.interfaces.ImageClickListener
import com.klemer.photoexplore.models.PixaBayImage
import com.klemer.photoexplore.models.PixaBayResponse
import com.klemer.photoexplore.services.RetrofitService
import com.klemer.photoexplore.viewmodels.ImagesFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesFragments : Fragment(R.layout.images_fragments), Callback<PixaBayResponse>,
    ImageClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ImagesFragmentViewModel
    private lateinit var searchEditText: TextInputEditText
    private lateinit var buttonSearch: ImageView
    private lateinit var fragContext: Context
    private lateinit var binding: ImagesFragmentsBinding
    private lateinit var progressBar: ProgressBar

    private val observerImages = Observer<List<PixaBayImage>> {

        recyclerView.adapter = ImagesAdapter(it, this)
    }

    companion object {
        fun newInstance() = ImagesFragments()
    }

    private val pixabay by lazy {
        RetrofitService()
            .getInstance(BuildConfig.PIXABAY_API_URL)
            .create(PixaBayEndpoints::class.java)
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
            searchImages(searchEditText.text.toString())
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
        pixabay.getLatestImages(BuildConfig.PIXABAY_API_KEY, 200).clone().enqueue(this)
    }

    private fun searchImages(query: String) {
        showProgress(true)
        val searchQuery = query.replace(" ", "+")

        pixabay.searchImages(BuildConfig.PIXABAY_API_KEY, searchQuery, 200).clone()
            .enqueue(this)
    }

    override fun onResponse(call: Call<PixaBayResponse>, response: Response<PixaBayResponse>) {
        showProgress(false)
        if (response.code() == 200)
            updateImagesList(response.body()!!.images)
    }

    override fun onFailure(call: Call<PixaBayResponse>, t: Throwable) {
        showProgress(false)
        fragContext.showToast("${t.message}")
    }

    override fun onImageClick(image: PixaBayImage) {
        val bundle = Bundle()
        bundle.putSerializable("image", image)
        val fragment = ImageDetailFragment.newInstance()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager
            .beginTransaction()
            .hide(this)
            .add(R.id.container_root, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun updateImagesList(images: List<PixaBayImage>) {
        viewModel.updateImages(images)
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}