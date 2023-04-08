package com.example.cleancar.ui.reserva

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleancar.*
import com.example.cleancar.databinding.ActivityAlfombra2Binding
import com.example.cleancar.databinding.FragmentReservaBinding

class ReservaFragment : Fragment() {

    private var _binding: FragmentReservaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReservaBinding.inflate(inflater, container, false)

        // Obtener la referencia del botón
        val buttonEncerado = binding.btnEncerado
        val buttonTapiceria = binding.btnTapiceria
        val buttonAlfombra = binding.btnAlfombra
        val buttonLavadoCompleto = binding.btnLavadoCompleto
        val buttonPulido = binding.btnPulido
        val buttonInterior = binding.btnInterior
        val buttonMotor = binding.btnMotor
        val buttonLijado = binding.btnLijado

        // Agregar un OnClickListener
        buttonEncerado.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, Encerado2::class.java)
            startActivity(intent)
        }

        //Boton de Tapiceria
        buttonTapiceria.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, Tapiceria2::class.java)
            startActivity(intent)
        }

        //boton de Alfombra
        buttonAlfombra.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, alfombra2::class.java)
            startActivity(intent)
        }

        //boton de Lavado Completo
        buttonLavadoCompleto.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, lavado2::class.java)
            startActivity(intent)
        }

        //boton para ir a Pulido
        buttonPulido.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, pulido2::class.java)
            startActivity(intent)
        }

        //boton para ir a Interior
        buttonInterior.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, Interior2::class.java)
            startActivity(intent)
        }

        //boton para ir a Lavado Motor
        buttonMotor.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, Motor2::class.java)
            startActivity(intent)
        }

        //boton para ir a lavado de lijado
        buttonLijado.setOnClickListener {
            // Iniciar la actividad Encerado2
            val intent = Intent(activity, lijado2::class.java)
            startActivity(intent)
        }


        return binding.root
        }
    }
