package berthier.antoine.birudroid.activity

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import berthier.antoine.birudroid.R
import berthier.antoine.birudroid.model.Beer
import berthier.antoine.birudroid.model.Brand
import berthier.antoine.birudroid.model.TypeBeer
import berthier.antoine.birudroid.util.FirebaseUtil
import com.google.firebase.auth.FirebaseAuth
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_beer_creation.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

class BeerCreation : AppCompatActivity() {

    private lateinit var _fireBaseDatase: FirebaseDatabase;
    private lateinit var _databaseReference: DatabaseReference;
    private lateinit var _storage : FirebaseStorage;
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var alertDialogBuilder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_creation)
        //var listTypeOfBeer: ArrayList<String> = createArrayListBeer()
        alertDialogBuilder = AlertDialog.Builder(this)

        // init firebase
        FirebaseUtil.openFbreference(this, "beers")
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.addAuthStateListener(FirebaseUtil.authStateListener)

        //FirebaseUtil.openFbReference("beers", this);
        _fireBaseDatase = FirebaseUtil.fireBaseDatabase
        //la c'est la reference de la bdd
        _databaseReference = FirebaseUtil.databaseReference;
        //la celle de la bdd pour les images
        _storage = FirebaseStorage.getInstance("gs://birudroid-139c9.appspot.com")

        val spinner: Spinner = findViewById(R.id.beerType)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.type_of_beer,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        this.addedButton.setOnClickListener()
        {
            sendNewBeer();
        }

        this.beerPictureCV.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PermissionChecker.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 44)

            } else {
                /* on n'a pas la permissions :-/ */
                alertDialogBuilder.setMessage("We do not have the permission to take a picture. Change your configuration to allow it if you want to add a picture")
                alertDialogBuilder.setNeutralButton("ok") {dialog: DialogInterface, which: Int-> Toast.makeText(applicationContext, "ok", Toast.LENGTH_SHORT)}
                alertDialogBuilder.show()
            }
            Log.d("INFO","BUTON IS ACTIVATE")
            CropImage.activity(null)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape( CropImageView.CropShape.OVAL)
                .setCropMenuCropButtonTitle("Valider")
                .setBorderLineColor(Color.RED)
                .start(this)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    fun sendNewBeer()
    {
        val netralButton = {dialog: DialogInterface, which: Int-> Toast.makeText(applicationContext, "Maybe", Toast.LENGTH_SHORT)}
        println("le text ")
        val message :String = getString(R.string.missing_data)
        if (!checkData()) {
            alertDialogBuilder.setMessage(message)
            alertDialogBuilder.setNeutralButton("ok") {dialog: DialogInterface, which: Int-> Toast.makeText(applicationContext, "ok", Toast.LENGTH_SHORT)}
            alertDialogBuilder.show()
            return
        }
        else {
            val nameBeer: String = beerNameInput.text.toString()
            val brand: String = beerBrand.text.toString()
            val degree: String = beerDegree.text.toString()
            val spinner: Spinner = findViewById(R.id.beerType);
            val typeBeer: String = spinner.getSelectedItem().toString();
            val idImd: String = initBeerImageData()
            /* we dont check if there is a picture, to have one is not mandatory */
            var newBeer: Beer =
                Beer(
                    "",
                    nameBeer,
                    degree.toDouble(),
                    Brand("0", brand, ""),
                    TypeBeer.valueOf(typeBeer.replace(" ", "_").toUpperCase()),
                    0,
                    0.0,
                    idImd
                );
            _databaseReference.push().setValue(newBeer);
            Toast.makeText(this, getString(R.string.beer_added), Toast.LENGTH_LONG).show();
            this.finish()
        }

    }


    fun checkData(): Boolean {
        val nameBeer :String = beerNameInput.text.toString()
        val brand :String = beerBrand.text.toString()
        val degree :String = beerDegree.text.toString()

        if (nameBeer.length <= 0){
            return false
        }
        if (brand.length <= 0){
            return false
        }
        if (degree.length <= 0){
            return false
        }
        return true
    }

    fun initBeerImageData() :String {
        //on crée un uuid unique pour l'image
        var uuid = UUID.randomUUID().toString()
        var storageRef = _storage.reference.child(uuid)

        try {
            var bitmap = (beerPictureCV.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            storageRef.putBytes(data)
        }
        catch (e: Exception)
        {
            return "0"
        }
        return uuid
    }

    /* cette fonction c'est pour retourner  a l'accueil depuis la tool bar du haut */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)

            if (resultCode == Activity.RESULT_OK) {
                (findViewById<View>(R.id.beerPictureCV) as ImageView).setImageURI(
                    result.uri
                )
                Toast.makeText(
                    this,
                    "Cropping successful, Sample: " + result.sampleSize,
                    Toast.LENGTH_LONG
                )
                    .show()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}
