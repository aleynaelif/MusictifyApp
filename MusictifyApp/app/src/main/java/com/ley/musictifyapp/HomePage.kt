package com.ley.musictifyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ley.musictifyapp.databinding.ActivityHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class HomePage : AppCompatActivity() {
    @Inject
    lateinit var glide: RequestManager

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(NavBarHomeButton())

        auth = Firebase.auth


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> replaceFragment(NavBarHomeButton())
                R.id.search -> replaceFragment(Search())

                else->{

                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.createPlaylist){
            replaceFragment(CreatePlaylist())

        }
        else if(item.itemId == R.id.signout){
            auth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}