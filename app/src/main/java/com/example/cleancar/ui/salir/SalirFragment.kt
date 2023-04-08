package com.example.cleancar.ui.salir

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleancar.MainActivity
import com.example.cleancar.Tapiceria2
import com.example.cleancar.databinding.FragmentSalirBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SalirFragment : Fragment() {
    //variables principales para la autenticacion con firebase
    private lateinit var firebaseAuth: FirebaseAuth

    private var _binding: FragmentSalirBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val configuracionViewModel =
            ViewModelProvider(this).get(SalirViewModel::class.java)

        _binding = FragmentSalirBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //inicializamos firebase
        firebaseAuth = Firebase.auth

        val buttonlogout = binding.btnCerrarSesion

        buttonlogout.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}