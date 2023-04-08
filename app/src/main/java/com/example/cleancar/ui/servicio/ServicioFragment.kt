package com.example.cleancar.ui.servicio

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleancar.R
import com.example.cleancar.databinding.FragmentServicioBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Objects

class ServicioFragment : Fragment() {

    //Para la base de datos
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseRef:DatabaseReference

    private lateinit var sensores:Switch
    private lateinit var sensores1:Switch

    private var _binding: FragmentServicioBinding? = null
    private val binding get() = _binding!!

    private var sensor1 : Int=0
    private var sensor2 : Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //de private database
        databaseRef = FirebaseDatabase.getInstance().getReference("Espacios Disponibles")

        _binding = FragmentServicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Vincula el interruptor desde el diseño XML mediante su ID
        sensores = view.findViewById(R.id.switch4)
        sensores1 = view.findViewById(R.id.switch5)

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                sensor1 = snapshot.child("Sensor1").child("Disponibilidad").getValue(Int::class.java) ?:0
                sensor2 = snapshot.child("Sensor2").child("Disponibilidad").getValue(Int::class.java) ?:0

                sensores.isChecked = sensor1 == 1

                sensores1.isChecked = sensor2 == 1

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}