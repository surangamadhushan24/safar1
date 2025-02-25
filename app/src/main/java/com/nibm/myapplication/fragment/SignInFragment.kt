package com.nibm.myapplication.fragment

import UserViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nibm.myapplication.R
import com.nibm.myapplication.database.AppDatabase
import com.nibm.myapplication.repository.UserRepository


class SignInFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        // Get database instance and DAO
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(userDao)

        // Correct ViewModelProvider Factory usage
        viewModel = ViewModelProvider(this, UserViewModel.provideFactory(repository)).get(UserViewModel::class.java)

        view.findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.etUsername).text.toString()
            val password = view.findViewById<EditText>(R.id.etPassword).text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.getUser(username, password).observe(viewLifecycleOwner, Observer { user ->
                    if (user != null) {
                        findNavController().navigate(R.id.action_signInFragment_to_dashboardFragment)
                    } else {
                        Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
