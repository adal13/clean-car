package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class pulido2 : AppCompatActivity() {
    private val pulido = FirebaseDatabase.getInstance().getReference("Servicios/pulido")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulido2)

        val btn_pulido = findViewById<Button>(R.id.button3)

        btn_pulido.setOnClickListener{ save_pulido() }

        pulido.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                dataSnapshot.getValue(Pulidos::class.java)
            }
        })
    }
    private fun save_pulido() {
        val concepto = findViewById<TextView>(R.id.textView15)
        val precio = findViewById<TextView>(R.id.textView43)

        val pulidos = Pulidos(
            concepto.text.toString(),
            precio.text.toString()
        )
        pulido.push().setValue(pulidos)
    }
    data class Pulidos(val Tipo_Servicio: String = "", val Costo: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }
}