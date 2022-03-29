package com.example.cleanArchitectureAssignment.ui.mainscreen.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cleanArchitectureAssignment.R
import com.example.cleanArchitectureAssignment.databinding.LoginFragmentBinding
import com.example.cleanArchitectureAssignment.ui.mainscreen.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var bindingFragmentLogin:LoginFragmentBinding
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var navController: NavController
    private lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentLogin = DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false)
        return bindingFragmentLogin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initViewModel()
        initObserver()
    }

   private fun init() {
       actionBar = (activity as AppCompatActivity?)?.supportActionBar as ActionBar
       actionBar.title=""
        navController = Navigation.findNavController(view ?: View(requireContext()))
    }

    @SuppressLint("ResourceAsColor")
    private fun initObserver() {
        mainViewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it.toString().isNotEmpty()) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    it.toString(),
                    Snackbar.LENGTH_LONG
                ).setActionTextColor(resources.getColor(R.color.black)).show()
            }
        }
        mainViewModel.loginDataLiveData.observe(viewLifecycleOwner) {
            navController.navigate(R.id.action_loginFragment_to_profileFragment)
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        bindingFragmentLogin.viewModel = mainViewModel
        bindingFragmentLogin.lifecycleOwner = viewLifecycleOwner
    }

}