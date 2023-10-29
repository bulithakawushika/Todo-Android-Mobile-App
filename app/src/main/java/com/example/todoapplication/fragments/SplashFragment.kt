package com.example.todoapplication.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapplication.R
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple fragment for displaying a splash screen while checking the user's authentication status.
 */
class SplashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()
        // Initialize the navigation controller
        navController = Navigation.findNavController(view)

        // Delayed navigation based on user authentication status
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if (auth.currentUser != null ){
                // If the user is authenticated, navigate to the home fragment
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            }else {
                // If the user is not authenticated, navigate to the sign-in fragment
                navController.navigate(R.id.action_splashFragment_to_signInFragment)
            }
        },2000) // Delay for 2000 milliseconds (2 seconds)

    }
}