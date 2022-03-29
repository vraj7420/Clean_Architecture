package com.example.cleanArchitectureAssignment.ui.mainscreen.view

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
import com.example.cleanArchitectureAssignment.R
import com.example.cleanArchitectureAssignment.databinding.ProfileFragmentBinding
import com.example.cleanArchitectureAssignment.ui.mainscreen.viewmodel.MainActivityViewModel
import com.example.cleanArchitectureAssignment.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_dailog.*
import kotlinx.android.synthetic.main.profile_fragment.*


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var bindingFragmentProfile: ProfileFragmentBinding
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var dialog: Dialog
    private lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentProfile =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
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
        actionBar = (activity as AppCompatActivity?)?.supportActionBar as ActionBar
        actionBar.title=getString(R.string.profile)
        Utils().imgLoadUsingUrl(requireContext(),mainViewModel.loginDataLiveData.value?.ProfileImageURL.toString(),imgProfile)
    }

    private fun setListener() {
        tvEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${mainViewModel.loginDataLiveData.value?.Email}")
            startActivity(Intent.createChooser(intent,getString(R.string.sendMail)))
        }
        tvPhoneNumber.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${mainViewModel.loginDataLiveData.value?.PhoneNumber}")
            startActivity(callIntent)
        }
        tvUserAddressZipcode.setOnClickListener {
            val geoUri = Utils.googleMapUrl+(mainViewModel.loginDataLiveData.value?.UserAddress_Latitude) + "," + (mainViewModel.loginDataLiveData.value?.UserAddress_Longitude)
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(mapIntent)
        }
        btnEditMessage.setOnClickListener {
            openDialogBoxForEditMessage()
        }
        skbDeactivationRiskPercentage.setOnTouchListener { _, _ -> true }
    }

    private fun openDialogBoxForEditMessage() {
        dialog = Dialog(requireContext(),android.R.style.ThemeOverlay_Material_Dialog_Alert)
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