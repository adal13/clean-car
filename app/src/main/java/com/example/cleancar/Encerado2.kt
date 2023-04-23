package com.example.cleancar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.cleancar.databinding.ActivityMenuPrincipalBinding
//import com.example.cleancar.databinding.ActivityMenuPrincipalBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Encerado2 : AppCompatActivity() {
    private val encerado = FirebaseDatabase.getInstance().getReference("Servicios/encerado")

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encerado2)

        val btn_encerado = findViewById<Button>(R.id.button2)

        btn_encerado.setOnClickListener {
            save_encerado()
            finish()
        }

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
        val databaseRef = FirebaseDatabase.getInstance().getReference("Usuarios")
        firebaseAuth = Firebase.auth
        val userID = firebaseAuth.currentUser?.uid
//        val navView: NavigationView = binding.navView
//        val userID = firebaseAuth.currentUser?.uid

//        val inflater = LayoutInflater.from(this)
//        val view = inflater.inflate(R.layout.activity_main, null)

        val concepto = findViewById<TextView>(R.id.textView40)
        val precio = findViewById<TextView>(R.id.textView33)
//        val correo = view.findViewById<EditText>(R.id.edtEmail)


//        val bundle = intent.extras
//        val dato = bundle?.getString("Correo")


//        val objetoIntent: Intent=intent
//        var correo = objetoIntent.getStringExtra("Correo")
//        val correosave = User("$dato")


        databaseRef.child(userID!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap<*, *>
//                val nombre = userMap["nombre"].toString()
                val email = userMap["correoElectronico"].toString()

                // Actualizar los elementos de la interfaz de usuario en la cabecera del menú hamburguesa
//                val headerView = navView.getHeaderView(0)
//                val txtNombre = findViewById<TextView>(R.id.txtNombreUsuario)
//                val txtEmail = findViewById<TextView>(R.id.txtEmailUsuario)
//                txtNombre.text = nombre
//                txtEmail.text = email

                val encerados = Encerados(
                    concepto.text.toString(),
                    precio.text.toString(),
                    email
                )
                encerado.push().setValue(encerados)
                Toast.makeText(baseContext, "Servicio Iniciado", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {  }
        })


    }

    data class Encerados(val Tipo_Servicio: String = "", val Costo: String = "", val Email: String = "") {
        override fun toString() = Tipo_Servicio + "\t" + Costo + "\t" + Email + "\t"
    }

}