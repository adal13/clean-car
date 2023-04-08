package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class alfombra2 : AppCompatActivity() {
    private val alfombra = FirebaseDatabase.getInstance().getReference("Servicios/alfombra")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alfombra2)

        val btn_alfombra = findViewById<Button>(R.id.button6)

        btn_alfombra.setOnClickListener { save_alfombra() }

        alfombra.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                dataSnapshot.getValue(Alfombra::class.java)
            }
        })
    }
    private fun save_alfombra() {
        val concepto = findViewById<TextView>(R.id.textView48)
        val precio = findViewById<TextView>(R.id.textView62)

        val alfombras = Alfombra(
            concepto.text.toString(),
            precio.text.toString()
        )
        alfombra.push().setValue(alfombras)
    }
    data class Alfombra(val Tipo_Servicio: String = "", val Costo: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }

}