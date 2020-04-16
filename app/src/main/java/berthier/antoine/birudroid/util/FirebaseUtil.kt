package berthier.antoine.birudroid.util

import android.app.Activity
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class FirebaseUtil {
    companion object Instance{
        lateinit var mCallerActivity: Activity
        fun openFbreference(callerActivity: Activity){
            mCallerActivity = callerActivity
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
