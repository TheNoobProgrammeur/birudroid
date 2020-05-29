package berthier.antoine.birudroid.util

import android.app.Activity
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseUtil {
    companion object Instance{
        lateinit var mCallerActivity: Activity
        lateinit var authStateListener: FirebaseAuth.AuthStateListener
        var fireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance();
        lateinit var caller: Activity
        lateinit var authListener: FirebaseAuth.AuthStateListener;
        lateinit var databaseReference: DatabaseReference;
        var fireBaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance();

        fun openFbreference(callerActivity: Activity, ref: String = "beers"){
            fireBaseAuth = FirebaseAuth.getInstance();
            mCallerActivity = callerActivity
            authStateListener = FirebaseAuth.AuthStateListener {
                callerActivity.invalidateOptionsMenu()
            }
            databaseReference = fireBaseDatabase.reference.child(ref);
        }

        private fun signIn(callerActivity: Activity){
            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())
            // Create and launch sign-in intent
            caller.startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                1);
        }

        fun logout(){
            // We could create a confirmation popup
            AuthUI.getInstance()
                .signOut(mCallerActivity)
                .addOnCompleteListener {
                    // Créer des alerts plutôt que des Toasts
                    Toast.makeText(mCallerActivity, "You have been disconnected", Toast.LENGTH_LONG).show()
                }.addOnCanceledListener {
                    Toast.makeText(mCallerActivity, "Impossible to disconnect", Toast.LENGTH_LONG).show()
                }
        }
    }
}
