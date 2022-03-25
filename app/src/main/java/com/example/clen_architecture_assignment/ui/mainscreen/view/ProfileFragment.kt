package com.example.clen_architecture_assignment.ui.mainscreen.view

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.clen_architecture_assignment.R
import com.example.clen_architecture_assignment.Utils
import com.example.clen_architecture_assignment.databinding.FragmentProfileBinding
import com.example.clen_architecture_assignment.ui.mainscreen.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_dailog.*
import kotlinx.android.synthetic.main.fragment_profile.*


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var bindingFragmentProfile: FragmentProfileBinding
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var dialog: Dialog

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
        mainViewModel = ViewModelProvider(activity!!)[MainActivityViewModel::class.java]
        bindingFragmentProfile.viewModel = mainViewModel
        bindingFragmentProfile.lifecycleOwner =requireActivity()
    }

    private fun init() {
        Glide.with(this)
            .load(mainViewModel.loginData.value?.ProfileImageURL)
            .into(imgProfile)
        dialog = Dialog(requireContext())

    }

    private fun setListener() {
        tvEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse(Utils.mailto+{mainViewModel.loginData.value?.Email})
            intent.type = Utils.intentType
            startActivity(intent)
        }
        tvPhoneNumber.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${mainViewModel.loginData.value?.PhoneNumber}")
            startActivity(callIntent)
        }
        tvUserAddressZipcode.setOnClickListener {
            val geoUri = Utils.googleMapUrl+(mainViewModel.loginData.value?.UserAddress_Latitude) + "," + (mainViewModel.loginData.value?.UserAddress_Latitude)
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(mapIntent)
        }
        btnEditMessage.setOnClickListener {
            openDialogBox()
        }
    }

    private fun openDialogBox() {
        dialog.setContentView(R.layout.custom_dailog)
        val btnCancel = dialog.btnCancel
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.tvAccessToken.text = mainViewModel.loginData.value?.access_token
        dialog.show()
    }
}