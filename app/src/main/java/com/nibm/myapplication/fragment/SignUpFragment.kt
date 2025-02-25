package com.nibm.myapplication.fragment

import UserViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nibm.myapplication.R
import com.nibm.myapplication.database.AppDatabase
import com.nibm.myapplication.model.User
import com.nibm.myapplication.repository.UserRepository


class SignUpFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        // Get database instance and DAO
        val database = AppDatabase.getDatabase(requireContext())
        val repository = UserRepository(database.userDao())

        // Use ViewModelProvider with Factory
        viewModel = ViewModelProvider(this, UserViewModel.provideFactory(repository))[UserViewModel::class.java]

        view.findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.etUsername).text.toString()
            val password = view.findViewById<EditText>(R.id.etPassword).text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val user = User(username = username, password = password)
                viewModel.insert(user)
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }

        return view
    }
}
