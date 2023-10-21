package com.example.todoapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import java.security.AuthProvider

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navControl:NavController
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }
    private fun init(view: View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }
    //navigate to sign in
    private fun registerEvents(){
        binding.authTextView.setOnClickListener{
            navControl.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        //Navigate to Home and Registation
        binding.nextButton.setOnClickListener{
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passET.text.toString().trim()
            val repass = binding.repassET.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()){
                if (pass == repass){
                    binding.progressBar.visibility=View.VISIBLE
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(
                        OnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(context , "Registered Successfully" , Toast.LENGTH_SHORT).show()
                                navControl.navigate(R.id.action_signUpFragment_to_homeFragment)
                            } else {
                                Toast.makeText(context , it.exception?.message , Toast.LENGTH_SHORT).show()
                            }

                            binding.progressBar.visibility=View.GONE
                        })
                } else {
                    Toast.makeText(context , "Password does not matched !" , Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(context , "Empty fields not allowed !" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}