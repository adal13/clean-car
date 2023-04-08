package com.example.cleancar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.cleancar.databinding.ActivityMenuPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MenuPrincipalActivity : AppCompatActivity() {
    //variables principales para la autenticacion con firebase
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenuPrincipal.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu_principal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //aqui se agrega los id de nav_cofig
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_notificacion,
                R.id.nav_configuracion
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //inicializamos firebase
        firebaseAuth = Firebase.auth


        // Obtener la referencia de la base de datos
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("Usuarios")

// Obtener la ID del usuario actual
        val userID = firebaseAuth.currentUser?.uid

        databaseReference.child(userID!!).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Obtener los datos del usuario de la base de datos
                val userMap = dataSnapshot.value as HashMap<*, *>
                val nombre = userMap["nombre"].toString()
                val email = userMap["correoElectronico"].toString()

                // Actualizar los elementos de la interfaz de usuario en la cabecera del menú hamburguesa
                val headerView = navView.getHeaderView(0)
                val txtNombre = headerView.findViewById<TextView>(R.id.txtNombreUsuario)
                val txtEmail = headerView.findViewById<TextView>(R.id.txtEmailUsuario)
                txtNombre.text = nombre
                txtEmail.text = email
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar errores de la base de datos
            }
        })

    }


    //funcion de Cerrar Sesion
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_Salir ->{
                cerrarSesion()
            }
        }
        return super.onOptionsItemSelected(item)
    } //termina la funcion Cerrar Sesion

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    //cerrar sesion
    private fun cerrarSesion()
    {
        firebaseAuth.signOut()
        Toast.makeText(baseContext,"Sesión Cerrada Correctamente",Toast.LENGTH_SHORT).show()
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }//Termina la funcion cerrar sesion
}