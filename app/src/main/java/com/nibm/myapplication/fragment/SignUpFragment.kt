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
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.nibm.myapplication.R
import com.nibm.myapplication.database.AppDatabase
import com.nibm.myapplication.model.User
import com.nibm.myapplication.repository.UserRepository

class SignUpFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var countryLayout: TextInputLayout
    private lateinit var telLayout: TextInputLayout
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etCountry: TextInputEditText
    private lateinit var etTel: TextInputEditText
    private lateinit var btnSignUp: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)


        usernameLayout = view.findViewById(R.id.usernameLayout)
        passwordLayout = view.findViewById(R.id.passwordLayout)
        countryLayout = view.findViewById(R.id.countryLayout)
        telLayout = view.findViewById(R.id.telLayout)
        etUsername = view.findViewById(R.id.etUsername)
        etPassword = view.findViewById(R.id.etPassword)
        etCountry = view.findViewById(R.id.etCountry)
        etTel = view.findViewById(R.id.etTel)
        btnSignUp = view.findViewById(R.id.btnSignUp)


        val database = AppDatabase.getDatabase(requireContext())
        val repository = UserRepository(database.userDao())


        viewModel = ViewModelProvider(this, UserViewModel.provideFactory(repository))
            .get(UserViewModel::class.java)

        btnSignUp.setOnClickListener {
            validateAndSignUp()
        }

        return view
    }

    private fun validateAndSignUp() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val country = etCountry.text.toString().trim()
        val tel = etTel.text.toString().trim()

        var isValid = true

        // Validate Username
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


        if (country.isEmpty()) {
            countryLayout.error = "Country is required"
            isValid = false
        } else {
            countryLayout.error = null
        }


        if (tel.isEmpty()) {
            telLayout.error = "Telephone is required"
            isValid = false
        } else if (tel.length < 10) {
            telLayout.error = "Telephone must be at least 10 digits"
            isValid = false
        } else {
            telLayout.error = null
        }


        if (isValid) {
            val user = User(
                username = username,
                password = password,
                country = country,
                tel = tel
            )


            viewModel.insert(user)


            Toast.makeText(requireContext(), "Sign up successful!", Toast.LENGTH_SHORT).show()


            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }
}