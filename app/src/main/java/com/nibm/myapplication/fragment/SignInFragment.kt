package com.nibm.myapplication.fragment

import UserViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nibm.myapplication.R
import com.nibm.myapplication.database.AppDatabase
import com.nibm.myapplication.repository.UserRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSignIn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        // Initialize Views
        usernameLayout = view.findViewById(R.id.usernameLayout)
        passwordLayout = view.findViewById(R.id.passwordLayout)
        etUsername = view.findViewById(R.id.etUsername)
        etPassword = view.findViewById(R.id.etPassword)
        btnSignIn = view.findViewById(R.id.btnSignIn)

        // Get database instance and DAO
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(userDao)

        // Initialize ViewModel with Factory
        viewModel = ViewModelProvider(this, UserViewModel.provideFactory(repository))
            .get(UserViewModel::class.java)

        btnSignIn.setOnClickListener {
            validateAndSignIn()
        }

        return view
    }

    private fun validateAndSignIn() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        var isValid = true

        if (username.isEmpty()) {
            usernameLayout.error = "Username is required"
            isValid = false
        } else {
            usernameLayout.error = null
        }

        if (password.isEmpty()) {
            passwordLayout.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            passwordLayout.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordLayout.error = null
        }

        if (isValid) {
            authenticateUser(username, password)
        }
    }

    private fun authenticateUser(username: String, password: String) {
        viewModel.getUser(username, password).observe(viewLifecycleOwner) { user ->
            if (user != null) {
                findNavController().navigate(R.id.action_signInFragment_to_dashboardFragment)
            } else {
                Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
