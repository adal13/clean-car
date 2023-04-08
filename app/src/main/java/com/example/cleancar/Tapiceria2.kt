package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Tapiceria2 : AppCompatActivity() {
    private val tapiceria = FirebaseDatabase.getInstance().getReference("Servicios/tapiceria")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapiceria2)

        val btn_tapiceria = findViewById<Button>(R.id.button7)

        btn_tapiceria.setOnClickListener{ save_tapiceria() }

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
        val concepto = findViewById<TextView>(R.id.textView47)
        val precio = findViewById<TextView>(R.id.textView71)

        val tapicerias = Tapicerias(
            concepto.text.toString(),
            precio.text.toString()
        )
        tapiceria.push().setValue(tapicerias)
    }
    data class Tapicerias(val Tipo_Servicio: String = "", val Costo: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }
}