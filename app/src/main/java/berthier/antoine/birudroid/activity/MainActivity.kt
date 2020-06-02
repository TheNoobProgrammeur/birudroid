package berthier.antoine.birudroid.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import berthier.antoine.birudroid.R
import berthier.antoine.birudroid.adapter.BeerAdapter
import berthier.antoine.birudroid.model.Beer
import berthier.antoine.birudroid.util.FirebaseUtil
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var listBeer: ArrayList<Beer>;
    private lateinit var _fireBaseDatase: FirebaseDatabase;
    private lateinit var _databaseReference: DatabaseReference;
    private lateinit var _childEventListener: ChildEventListener;
    private lateinit var rvBeer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseUtil.openFbreference(this)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.addAuthStateListener(FirebaseUtil.authStateListener)


        val navBar: BottomNavigationView = findViewById(R.id.bottom_navigation)

        /*
        * Warning  : there are a setOnNavigationItemSelectedListener
        *                  and a setOnNavigationItemRESelectedListener
        */
        navBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.btn_add_beer -> {
                    goToNewBeer()
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        FirebaseUtil.beerList.clear()
        rvBeer = findViewById(R.id.rvBeers);
        val adapter = BeerAdapter(FirebaseUtil);
        rvBeer.adapter = adapter;
        val beersLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBeer.layoutManager = beersLayoutManager;
        //FirebaseUtil.openFbreference( this, "beers")
    }

    override fun onPause() {
        super.onPause()
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

    private fun goToNewBeer() {
        val newBeerActivity = Intent(this, BeerCreation::class.java)
        startActivity(newBeerActivity)
    }

    private fun logout(){
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.deconnexion))
            .setMessage(resources.getString(R.string.deconnexion_message))
            .setNegativeButton(resources.getString(R.string.annuler)) { dialog, which ->
            }
            .setPositiveButton(resources.getString(R.string.accept_deco)) { dialog, which ->
                FirebaseUtil.logout()
                invalidateOptionsMenu()
            }
            .show()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_login -> goToLogin()
            R.id.btn_logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

}
