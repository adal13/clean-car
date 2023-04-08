package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Motor2 : AppCompatActivity() {
    private val motor = FirebaseDatabase.getInstance().getReference("Servicios/motor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motor2)

        val btn_motor = findViewById<Button>(R.id.button4)

        btn_motor.setOnClickListener { save_motor() }

        motor.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(datasnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                dataSnapshot.getValue(Motor::class.java)
            }
        })
    }
    private fun save_motor() {
        val concepto = findViewById<TextView>(R.id.textView39)
        val precio = findViewById<TextView>(R.id.textView44)

        val motores = Motor(
            concepto.text.toString(),
            precio.text.toString()
        )
        motor.push().setValue(motores)
    }

    data class Motor(val Tipo_Servicio: String = "", val Costo: String = ""){
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t"
    }
}