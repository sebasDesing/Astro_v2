package com.example.astrop.ui.sigIn

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.astrop.R
import com.example.astrop.databinding.FragmentSigInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigInFragment : Fragment() {

    private var _binding: FragmentSigInBinding? = null
    private val binding get() = _binding!!


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authState: FirebaseAuth.AuthStateListener

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.i("acountGG", "${account.email}")
                            goHome(
                                account.givenName.toString(),
                                account.photoUrl.toString(),
                                account.email.toString()
                            )
                        }
                    }
                }
            } catch (e: ApiException) {
                Log.i("acountGG", "$e")
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = Firebase.auth
        binding.googleBtn.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            binding.prg.visibility = View.VISIBLE
            val googleClient = GoogleSignIn.getClient(requireActivity(), googleConf)
            googleClient.signOut()
            googleSignInLauncher.launch(googleClient.signInIntent)

        }

        session()
    }

    private fun session() {
        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        val email = prefs.getString("email", null)
        val user = prefs.getString("nameU", null)
        val photo = prefs.getString("imgU", null)
        if (email != null) {
            goHome(user.toString(), photo.toString(), email.toString())
        }


    }

    private fun goHome(nameUser: String, email: String, photoUrl: String) {
        Log.i("ses", "$nameUser , $email ,$photoUrl")
        val navigation =
            SigInFragmentDirections.actionSigInFragmentToHomeFragment(
                nameUser, email, photoUrl
            )
        binding.prg.visibility =View.GONE
        findNavController().navigate(navigation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}