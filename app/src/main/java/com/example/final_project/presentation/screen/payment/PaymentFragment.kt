package com.example.final_project.presentation.screen.payment

import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentPaymentBinding
import com.example.final_project.presentation.PaymentInformation
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.screen.address.AddressFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    override fun bind() {

    }

    override fun bindListeners() {
        binding.btnBackArrow.setOnClickListener {
            navigateToProfileFragment()
        }
    }

    override fun bindObserves() {

    }

    fun receivePaymentInfo(paymentInfo: PaymentInformation) {
    }

    private fun navigateToProfileFragment(){
        val action = PaymentFragmentDirections.actionPaymentFragmentToProfileFragment()
        findNavController().navigate(action)
    }

}