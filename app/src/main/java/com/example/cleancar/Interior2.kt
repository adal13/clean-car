package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class Interior2 : AppCompatActivity() {
    private val interior = FirebaseDatabase.getInstance().getReference("Servicios/interior")

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interior2)

        val btn_interior = findViewById<Button>(R.id.button8)

        btn_interior.setOnClickListener{ save_pulido() }

        interior.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                dataSnapshot.getValue(Interiores::class.java)
            }
        })
    }
    private fun save_pulido() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val concepto = findViewById<TextView>(R.id.textView72)
        val precio = findViewById<TextView>(R.id.textView81)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap<*, *>
//                val nombre = userMap["nombre"].toString()
                val email = userMap["correoElectronico"].toString()

                val interiores = Interiores(
                    concepto.text.toString(),
                    precio.text.toString(),
                    email
                )
                interior.push().setValue(interiores)

            }
            override fun onCancelled(error: DatabaseError) {  }
        })


    }

    data class Interiores(val Tipo_Servicio: String = "", val Costo: String = "", val Email: String = ""){
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t" + Email + "\t"

    }
}