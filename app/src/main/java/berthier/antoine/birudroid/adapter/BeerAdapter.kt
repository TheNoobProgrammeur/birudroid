package berthier.antoine.birudroid.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import berthier.antoine.birudroid.R
import berthier.antoine.birudroid.activity.DetailBeer
import berthier.antoine.birudroid.model.Beer
import berthier.antoine.birudroid.util.FirebaseUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage

class BeerAdapter (firebaseUtil: FirebaseUtil.Instance):  RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {
    var beerList: ArrayList<Beer>;

    init {
        val _databaseReference: DatabaseReference = FirebaseUtil.databaseReference;
        beerList = FirebaseUtil.beerList;


        val _childListener: ChildEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val beer = dataSnapshot.getValue<Beer>()

                if (beer != null ) {
                    beer.id = dataSnapshot.key.toString();
                    beerList.add(beer);
                    notifyItemInserted(beerList.size-1);
                };


            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        }
        _databaseReference.addChildEventListener(_childListener);
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val context: Context = parent.context;
        val itemView: View = LayoutInflater.from(context).inflate(berthier.antoine.birudroid.R.layout.rv_beer_row, parent, false);
        return BeerViewHolder(itemView);
    }

    override fun getItemCount(): Int {
        return beerList.size;
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer: Beer = beerList.get(position);
        holder.bind(beer)
    }

    class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var tvBeerName: TextView = itemView.findViewById(R.id.tvBeerName);
        var tvBeerDegree: TextView = itemView.findViewById(R.id.tvBeerDegree);
        var tvBeerDescription: TextView = itemView.findViewById(R.id.tvBeerDescription);
        var tvBeerImage: ImageView = itemView.findViewById(R.id.ivBeer);
        var tvAverage: TextView = itemView.findViewById(R.id.tvAverage);
        private var context: Context? = null
        init {
            itemView.setOnClickListener(this);
            context = itemView.context;
        }
        fun bind(beer: Beer){

            if (beer.idImageBeer != "null"  ){
                val _storage = FirebaseStorage.getInstance("gs://first-firebase-8ddd8.appspot.com")
                val storageRef = _storage.reference.child(beer.idImageBeer)

                storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener {bites ->
                    val bitmap =  BitmapFactory.decodeByteArray(bites,0,bites.size)
                    tvBeerImage.setImageBitmap(bitmap)
                    // Got the download URL for 'users/me/profile.png'
                }.addOnFailureListener {
                    // Handle any errors
                }
            }

            tvBeerName.text = beer.name;
            tvBeerDegree.text = beer.degree.toString()+"°C"
            tvBeerDescription.text = beer.description;
            tvAverage.text = if(beer.averger==0.00) "" else beer.averger.toString();

        }

        override fun onClick(v: View) {
            val position = adapterPosition
            val firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.addAuthStateListener(FirebaseUtil.authStateListener)
            val user = firebaseAuth.currentUser

            if(user != null){
                val beer: Beer = FirebaseUtil.beerList[position]
                val detailBeerActivity = Intent( context,DetailBeer::class.java)
                detailBeerActivity.putExtra("BEER",beer)
                context?.startActivity(detailBeerActivity)
            }
        }
    }

}




