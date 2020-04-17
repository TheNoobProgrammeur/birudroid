package berthier.antoine.birudroid.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import berthier.antoine.birudroid.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val user = FirebaseAuth.getInstance().currentUser
        if(user == null){
            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())
            // Create and launch sign-in intent
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.ic_login_logo)
                    .setTheme(R.style.LoginTheme)
                    .build(),
                1)
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                if (resultCode == Activity.RESULT_OK) {
                    val goHome = Intent(this, MainActivity::class.java)
                    startActivity(goHome);
                } else {
                    Toast.makeText(this, "error in authent", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
