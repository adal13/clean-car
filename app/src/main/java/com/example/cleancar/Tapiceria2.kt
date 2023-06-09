package com.example.cleancar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.cleancar.ui.reserva.ReservaFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class Tapiceria2 : AppCompatActivity() {
    private val tapiceria = FirebaseDatabase.getInstance().getReference("Servicios/tapiceria")

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapiceria2)

        val btn_tapiceria = findViewById<Button>(R.id.button7)

        btn_tapiceria.setOnClickListener{
            save_tapiceria()
            finish()
        }

        tapiceria.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                dataSnapshot.getValue(Tapicerias::class.java)
            }
        })
    }

    private fun save_tapiceria() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val concepto = findViewById<TextView>(R.id.textView47)
        val precio = findViewById<TextView>(R.id.textView71)

        val enviar = findViewById<Button>(R.id.button7)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap<*, *>
//                val nombre = userMap["nombre"].toString()
                val email = userMap["correoElectronico"].toString()

                val tapicerias = Tapicerias(
                    concepto.text.toString(),
                    precio.text.toString(),
                    email
                )
                tapiceria.push().setValue(tapicerias)
                Toast.makeText(baseContext, "Servicio Iniciado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {  }
        })

    }
    data class Tapicerias(val Tipo_Servicio: String = "", val Costo: String = "", val Email: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t" + Email + "\t"
    }
}