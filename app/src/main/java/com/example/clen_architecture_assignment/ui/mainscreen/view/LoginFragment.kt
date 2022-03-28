package com.example.clen_architecture_assignment.ui.mainscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.clen_architecture_assignment.R
import com.example.clen_architecture_assignment.databinding.FragmentLoginBinding
import com.example.clen_architecture_assignment.ui.mainscreen.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var bindingFragmentLogin: FragmentLoginBinding
    private lateinit var mainViewModel: MainActivityViewModel
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentLogin =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return bindingFragmentLogin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initViewModel()
        setObserver()
    }

   private fun init() {
        navController = Navigation.findNavController(view ?: View(requireContext()))
    }

    private fun setObserver() {
        mainViewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it.toString().isNotEmpty()) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    it.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
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