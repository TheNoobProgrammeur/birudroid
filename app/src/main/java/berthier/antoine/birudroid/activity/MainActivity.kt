package berthier.antoine.birudroid.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import berthier.antoine.birudroid.R
import berthier.antoine.birudroid.util.FirebaseUtil
import com.google.android.material.badge.BadgeDrawable
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseUtil.openFbreference(this)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.addAuthStateListener(FirebaseUtil.authStateListener)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflaterLogin: MenuInflater = menuInflater
        inflaterLogin.inflate(R.menu.login_menu, menu)
        val user = firebaseAuth.currentUser
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
        startActivity(Intent(this, LoginActivity::class.java).addFlags(FLAG_ACTIVITY_NO_HISTORY))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_login -> goToLogin()
            R.id.btn_logout -> {FirebaseUtil.logout(); invalidateOptionsMenu()}
        }
        return super.onOptionsItemSelected(item)
    }
}
