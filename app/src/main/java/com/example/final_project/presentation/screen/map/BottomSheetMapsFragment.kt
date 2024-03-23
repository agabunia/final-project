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

    private var submissionListener: AddressSubmissionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetMapsBinding.inflate(inflater, container, false)

        binding.btnSave.setOnClickListener {
            val newLocation = binding.etSearch.text.toString()
            //ლოგიკა არის შესაცვლელი, არ დამავიწყდეს
            submissionListener?.onAddressSubmitted(newLocation)
            dismiss()
        }
        return binding.root
    }

    interface AddressSubmissionListener {
        fun onAddressSubmitted(address: String)
    }
    fun setAddressSubmissionListener(listener: AddressSubmissionListener) {
        submissionListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}