package berthier.antoine.birudroid.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import berthier.antoine.birudroid.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            menu.findItem(R.id.btn_login)?.isVisible = false
            menu.findItem(R.id.btn_logout)?.isVisible = true
        }else{
            menu.findItem(R.id.btn_login)?.isVisible = true
            menu.findItem(R.id.btn_logout)?.isVisible = false
        }
        return true;
    }
    private fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
    }


    // Convert in static method in FirebaseUtil File
    private fun logout(){
        // We could create a confirmation popup
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                invalidateOptionsMenu()
                // Créer des alerts plutôt que des Toasts
                Toast.makeText(this, "You have been disconnected", Toast.LENGTH_LONG).show()
            }.addOnCanceledListener {
                Toast.makeText(this, "Impossible to disconnect", Toast.LENGTH_LONG).show()
            }
        val user = FirebaseAuth.getInstance().currentUser
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_login -> goToLogin()
            R.id.btn_logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }
}
