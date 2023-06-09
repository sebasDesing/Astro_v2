package com.example.astrop.ui.sigIn

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.astrop.R
import com.example.astrop.databinding.FragmentSigInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

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
                                account.email.toString(),
                                account.photoUrl.toString()
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
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        val toolbar = requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout)
        bottomNavigation.visibility = View.GONE
        toolbar.visibility = View.GONE


        binding.sigInFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)
        firebaseAuth = Firebase.auth

        /*Realiza la autentificación de la cuenta de google en al presionar al boton*/
        autentication()
        /* Almacena en sharedpreferences */
        session()
        setAnimation()
    }

    private fun autentication() {
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
    }

    private fun setAnimation() {
        val alphaAnimatorImg = ObjectAnimator.ofFloat(binding.imgBg, "alpha", 0.5f, 1f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }
        alphaAnimatorImg.start()
    }

    private fun session() {
        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        val email = prefs.getString(getString(R.string.key_email), null)
        val user = prefs.getString(getString(R.string.key_nameU), null)
        val photo = prefs.getString(getString(R.string.key_photo_url), null)

        if (email != null) {
            goHome(user.toString(), email.toString(),"$photo" )
        }


    }

    /*REDIRIGE AL HOME SI ES QUE HAY UNA SESION ACTIVA  */
    private fun goHome(nameUser: String, email: String, photoUrl: String) {
        Log.i("sesionn", "$nameUser , $email ,$photoUrl")
        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        ).edit()
        prefs.putString( getString(R.string.key_email), email)
        prefs.putString(getString(R.string.key_nameU), nameUser)
        prefs.putString(getString(R.string.key_photo_url), photoUrl)
            .apply()
        binding.prg.visibility = View.GONE
        findNavController().navigate(R.id.homeFragment2)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}