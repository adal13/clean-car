package com.example.cleancar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import com.example.cleancar.ui.reserva.ReservaFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.core.view.View
import com.google.firebase.database.ktx.snapshots
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Encerado2 : AppCompatActivity() {
    private val encerado = FirebaseDatabase.getInstance().getReference("Servicios/encerado")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encerado2)

        val btn_encerado = findViewById<Button>(R.id.button2)

        btn_encerado.setOnClickListener { save_encerado() }

        encerado.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                //dataSnapshot.getValue(Encerados::class.java)
            }
        })
    }
    private fun save_encerado() {
        val concepto = findViewById<TextView>(R.id.textView40)
        val precio = findViewById<TextView>(R.id.textView33)

        val encerados = Encerados(
            concepto.text.toString(),
            precio.text.toString()
        )
        encerado.push().setValue(encerados)
    }

//    private fun writeEncerado(encerados: Encerados) {
//        val text = list_textView.text.toString() + encerados.toString() + "\n"
//        list_textView.text = text
//    }

    data class Encerados(val Tipo_Servicio: String = "", val Costo: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }

}