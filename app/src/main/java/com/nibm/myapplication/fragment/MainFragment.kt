package com.nibm.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.nibm.myapplication.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
        }

        view.findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signInFragment)
        }

        return view
    }
}