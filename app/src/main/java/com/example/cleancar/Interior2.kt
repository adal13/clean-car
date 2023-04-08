package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Interior2 : AppCompatActivity() {
    private val interior = FirebaseDatabase.getInstance().getReference("Servicios/interior")

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
        val concepto = findViewById<TextView>(R.id.textView72)
        val precio = findViewById<TextView>(R.id.textView81)

        val interiores = Interiores(
            concepto.text.toString(),
            precio.text.toString()
        )
        interior.push().setValue(interiores)
    }

    data class Interiores(val Tipo_Servicio: String = "", val Costo: String = ""){
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"

    }
}