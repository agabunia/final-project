package com.example.final_project.presentation.screen.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project.databinding.FragmentBottomSheetMapsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetMapsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetMapsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetMapsBinding.inflate(inflater, container, false)

        binding.btnSearchBottomSheet.setOnClickListener {
            val location = binding.etSearch.text.toString()
            (parentFragment as OnSearchButton).locationSearchButtonListener(location = location)
            dismiss()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}