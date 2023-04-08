package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class lijado2 : AppCompatActivity() {
    private val lijado = FirebaseDatabase.getInstance().getReference("Servicios/lijado")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lijado2)

        val btn_lijado = findViewById<Button>(R.id.button5)

        btn_lijado.setOnClickListener { save_lijado() }

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
        val concepto = findViewById<TextView>(R.id.textView15)
        val precio = findViewById<TextView>(R.id.textView56)

        val lijados = Lijado(
            concepto.text.toString(),
            precio.text.toString()
        )
        lijado.push().setValue(lijados)
    }

    data class Lijado(val Tipo_Servicio: String = "", val Costo: String = ""){
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }
}