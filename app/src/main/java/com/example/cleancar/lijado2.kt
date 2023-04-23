package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class lijado2 : AppCompatActivity() {
    private val lijado = FirebaseDatabase.getInstance().getReference("Servicios/lijado")

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lijado2)

        val btn_lijado = findViewById<Button>(R.id.button5)

        btn_lijado.setOnClickListener {
            save_lijado()
            finish()
        }

        lijado.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                dataSnapshot.getValue(Lijado::class.java)
            }
        })
    }

    private fun save_lijado(){
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid

        val concepto = findViewById<TextView>(R.id.textView15)
        val precio = findViewById<TextView>(R.id.textView56)

        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap<*, *>
//                val nombre = userMap["nombre"].toString()
                val email = userMap["correoElectronico"].toString()

                val lijados = Lijado(
                    concepto.text.toString(),
                    precio.text.toString(),
                    email
                )
                lijado.push().setValue(lijados)
                Toast.makeText(baseContext, "Servicio Iniciado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {  }
        })
    }

    data class Lijado(val Tipo_Servicio: String = "", val Costo: String = "", val Email: String = ""){
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t" + Email + "\t"
    }
}