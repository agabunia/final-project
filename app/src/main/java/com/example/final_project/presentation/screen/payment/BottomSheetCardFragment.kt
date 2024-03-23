package com.example.final_project.presentation.screen.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project.databinding.FragmentBottomSheetCardBinding
import com.example.final_project.presentation.PaymentInformation
import com.example.final_project.presentation.screen.payment.PaymentFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetMapsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetCardBinding.inflate(inflater, container, false)

        binding.btnSave.setOnClickListener {
            val cardNumber = binding.etCardNumber.text.toString()
            val expireDate = binding.etCardExpireDate.text.toString()
            val ccv = binding.etCCV.text.toString()

            if (isValidInput(cardNumber, expireDate, ccv)) {
                val paymentInfo = PaymentInformation(cardNumber, expireDate, ccv)
                sendPaymentInfoToParent(paymentInfo)
                dismiss()
            } else {

            }
        }

        return binding.root
    }

    private fun isValidInput(cardNumber: String, expireDate: String, ccv: String): Boolean {
        return cardNumber.length == 16 && cardNumber.all { it.isDigit() } &&
                expireDate.length == 4 && expireDate.all { it.isDigit() } &&
                ccv.length == 3 && ccv.all { it.isDigit() }
    }

    private fun sendPaymentInfoToParent(paymentInfo: PaymentInformation) {
        val parentFragment = parentFragment
        if (parentFragment is PaymentFragment) {
            parentFragment.receivePaymentInfo(paymentInfo)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}