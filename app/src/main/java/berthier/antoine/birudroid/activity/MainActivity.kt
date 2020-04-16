package berthier.antoine.birudroid.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import berthier.antoine.birudroid.R
import berthier.antoine.birudroid.model.User
import berthier.antoine.birudroid.model.UserManager
import berthier.antoine.birudroid.util.FirebaseUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseUtil.openFbreference(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d("test: ", "ping Main: ");
        UserManager.getInstance(this).create("adrien", "test", "id33333")
        Log.d("user main: ", UserManager.getInstance(this).haveUser().toString());
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
        //.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_login -> goToLogin()
            R.id.btn_logout -> {FirebaseUtil.logout(); invalidateOptionsMenu()}
        }
        return super.onOptionsItemSelected(item)
    }
}
