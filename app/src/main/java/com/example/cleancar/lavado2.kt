package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class lavado2 : AppCompatActivity() {
    private val lavado = FirebaseDatabase.getInstance().getReference("Servicios/lavado")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lavado2)

        val btn_lavado = findViewById<Button>(R.id.button)

        btn_lavado.setOnClickListener{ save_lavado() }

        lavado.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                dataSnapshot.getValue(Lavados::class.java)
            }
        })
    }
    private fun save_lavado() {
        val concepto = findViewById<TextView>(R.id.textView18)
        val precio = findViewById<TextView>(R.id.textView21)

        val lavados = Lavados(
            concepto.text.toString(),
            precio.text.toString()
        )
        lavado.push().setValue(lavados)
    }
    data class Lavados(val Tipo_Servicio: String = "", val Costo: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }
}