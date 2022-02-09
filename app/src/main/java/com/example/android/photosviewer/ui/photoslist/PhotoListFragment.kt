package com.example.android.photosviewer.ui.photoslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.photosviewer.databinding.FragmentPhotosListBinding
import com.example.android.photosviewer.ui.adapter.PhotoAdapter
import com.example.android.photosviewer.util.disableUserInteraction
import com.example.android.photosviewer.util.reEnableUserInteraction
import com.example.android.photosviewer.util.showSnackbar

class PhotoListFragment : Fragment() {

    private var _binding: FragmentPhotosListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotoListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "ViewModel can only be accessed after OnViewCreated() ic called"
        }
        ViewModelProvider(this, PhotoListViewModel.Factory(activity.application))
            .get(PhotoListViewModel::class.java)
    }

    private var photoAdapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        photoAdapter = PhotoAdapter { photo, _ ->
            // TODO
            //  navigate / show full screen photo
        }
        binding.photosRecyclerView.adapter = photoAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
    }

    private fun setupViewModelObservations() {
        viewModel.successMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, true)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, false)
        }
        viewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            if (isLoading) {
                disableUserInteraction()
            } else {
                reEnableUserInteraction()
            }
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            it?.apply {
                photoAdapter?.dataList = it
                binding.photosRecyclerView.smoothScrollToPosition(0)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}