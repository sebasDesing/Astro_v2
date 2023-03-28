package com.example.astrop.ui.sigIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astrop.databinding.FragmentSigInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigInFragment : Fragment() {

    private var _binding: FragmentSigInBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authState: FirebaseAuth.AuthStateListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSigInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth =Firebase.auth
        fakeSigIn()
    }

    private fun fakeSigIn() {
        firebaseAuth.signInWithEmailAndPassword("santosilvano97@gmail.com","123456789a")
            .addOnCompleteListener(requireActivity()) { task->
                if (task.isSuccessful){
                    val user =firebaseAuth.currentUser
                    Log.i("fakesing",user?.uid.toString() )
                }
            }

        firebaseAuth.createUserWithEmailAndPassword("santosilvano97@gmail.com","123456789a")
            .addOnCompleteListener (requireActivity()){task->
                if(task.isSuccessful){

                    Log.i("fakesiup", "CREADA CHAVO" )
                }else{
                    Log.i("fakesiup", "NOO CHAVO" )
                }
            }
    }


}