package berthier.antoine.birudroid.activity

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import berthier.antoine.birudroid.R
import berthier.antoine.birudroid.model.Beer
import berthier.antoine.birudroid.model.User
import berthier.antoine.birudroid.util.FirebaseUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_detail_beer.*

class DetailBeer : AppCompatActivity() {

    private lateinit var _databaseReference: DatabaseReference;
    lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_beer)
        _databaseReference = FirebaseUtil.databaseReference;
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.addAuthStateListener(FirebaseUtil.authStateListener)

        val beer : Beer   = intent.getSerializableExtra("BEER") as Beer

        nameBeer.text = beer.name


        if (beer.idImageBeer != "null"  ){
            val _storage = FirebaseStorage.getInstance("gs://first-firebase-8ddd8.appspot.com")
            val storageRef = _storage.reference.child(beer.idImageBeer)

            storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener {bites ->
                val bitmap =  BitmapFactory.decodeByteArray(bites,0,bites.size)
                ImageBeer.setImageBitmap(bitmap)
                // Got the download URL for 'users/me/profile.png'
            }.addOnFailureListener {
                // Handle any errors
            }
        }


        noteBeer.rating = beer.averger.toFloat()
        Degree.text = resources.getString(R.string.degree) + " " + beer.degree.toString();
        brand.text = if(beer.brand.name == "") "" else resources.getString(R.string.brand) + " " + beer.brand.name;
        Type.text = resources.getString(R.string.type) + " " + beer.typeBeer.name;
        Description.text = if(beer.description == "") "" else resources.getString(R.string.description) + " " + beer.description;

        

        val userCurrent = firebaseAuth.currentUser

        if (userCurrent != null) {
            if(!beer.listeUserVoted.contains(userCurrent.uid)){
                validate.setOnClickListener{
                    beer.listeUserVoted.add(userCurrent.uid)
                    this.notation(beer)
                }
            } else {
                validate.visibility = View.INVISIBLE
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    fun notation(beer: Beer){
        val newAverger = (beer.averger * beer.nbVotent + noteBeer.rating) / (beer.nbVotent+1)
        beer.averger = newAverger.toDouble()
        beer.nbVotent += 1
        noteBeer.rating = newAverger.toFloat()

        var map = HashMap<String,Beer>()
        map.put(beer.id,beer)
        _databaseReference.updateChildren(map as Map<String, Any>)
        validate.visibility = View.INVISIBLE

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}