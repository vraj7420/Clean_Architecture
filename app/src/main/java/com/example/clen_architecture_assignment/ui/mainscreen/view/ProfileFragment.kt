package com.example.clen_architecture_assignment.ui.mainscreen.view

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.clen_architecture_assignment.R
import com.example.clen_architecture_assignment.databinding.FragmentProfileBinding
import com.example.clen_architecture_assignment.ui.mainscreen.viewmodel.MainActivityViewModel
import com.example.clen_architecture_assignment.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_dailog.*
import kotlinx.android.synthetic.main.fragment_profile.*


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var bindingFragmentProfile: FragmentProfileBinding
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var dialog: Dialog
    private lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentProfile =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return bindingFragmentProfile.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        init()
        setListener()
    }


    private fun setViewModel() {
        mainViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        bindingFragmentProfile.viewModel = mainViewModel
        bindingFragmentProfile.lifecycleOwner =viewLifecycleOwner
    }

    private fun init() {
        actionBar = (activity as AppCompatActivity?)?.supportActionBar!!
        actionBar.title=getString(R.string.profile)
        Utils().setImgUsingGlide(requireContext(),
            mainViewModel.loginDataLiveData.value?.ProfileImageURL.toString(),imgProfile)
    }

    private fun setListener() {
        tvEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse(Utils.mailto+{mainViewModel.loginDataLiveData.value?.Email})
            startActivity(Intent.createChooser(intent,getString(R.string.sendMail)))
        }
        tvPhoneNumber.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${mainViewModel.loginDataLiveData.value?.PhoneNumber}")
            startActivity(callIntent)
        }
        tvUserAddressZipcode.setOnClickListener {
            val geoUri = Utils.googleMapUrl+(mainViewModel.loginDataLiveData.value?.UserAddress_Latitude) + "," + (mainViewModel.loginDataLiveData.value?.UserAddress_Latitude)
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(mapIntent)
        }
        btnEditMessage.setOnClickListener {
            openDialogBoxForEditMessage()
        }
        skbDeactivationRiskPercentage.setOnTouchListener { _, _ -> true }
    }

    private fun openDialogBoxForEditMessage() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dailog)
        val btnCancel = dialog.btnCancel
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.tvAccessToken.text = mainViewModel.loginDataLiveData.value?.access_token
        dialog.show()
    }
}