package com.ley.musictifyapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ley.musictifyapp.databinding.FragmentCreatePlaylistBinding
import com.ley.musictifyapp.databinding.FragmentNavBarHomeButtonBinding
import java.io.ByteArrayOutputStream

class CreatePlaylist : Fragment() {
    private var _binding: FragmentCreatePlaylistBinding? = null
    private val binding get() = _binding!!
    var secilenGorsel : Uri? = null
    var secilenBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePlaylistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            save(it)
        }
        binding.coverPhotoImageView.setOnClickListener {
            coverPhoto(it)
        }

    }

    fun coverPhoto(view : View){
        activity?.let{
            if(ContextCompat.checkSelfPermission(it.applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                //izin verillmedi, iste
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }
            else{
                //izin verilmiş,istemene gerek yok, galeriye git
                val galeriIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)

            }
        }



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == 1){
            if(grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //izin alındı
                val galeriIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){
            secilenGorsel = data.data
            try{

                context.let {
                    if(secilenGorsel != null){
                        if(Build.VERSION.SDK_INT >= 28){
                            val source = it?.let { it1 -> ImageDecoder.createSource(it1.contentResolver,secilenGorsel!!) }
                            secilenBitmap = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }
                            binding.coverPhotoImageView.setImageBitmap(secilenBitmap)
                        }
                        else{
                            if (it != null) {
                                secilenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver,secilenGorsel)
                                binding.coverPhotoImageView.setImageBitmap(secilenBitmap)
                            }
                        }
                    }
                }

            }catch (e : Exception){
                e.printStackTrace()
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    fun kucukBitmapOlustur(userBitmap: Bitmap,maxBoyut : Int) : Bitmap{
        var width = userBitmap.width
        var height = userBitmap.height

        val bitmapOrani : Double = width.toDouble() / height.toDouble()

        if(bitmapOrani > 1){
            //gorsel yatay
            width = maxBoyut
            val kisaltilmisHeight = width / bitmapOrani
            height = kisaltilmisHeight.toInt()
        }
        else{
            //gorsel dikey
            height = maxBoyut
            val kisaltilmisWidth = height * bitmapOrani
            width = kisaltilmisWidth.toInt()
        }

        return Bitmap.createScaledBitmap(userBitmap,width,height,true)
    }

    fun save(view : View){

        val playlistName = binding.playlistName.text.toString()

        if(secilenBitmap != null){
            val kucukBitmap = kucukBitmapOlustur(secilenBitmap!!,300)

            val outputStream = ByteArrayOutputStream()
            kucukBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteDizisi = outputStream.toByteArray()

            try{
                context?.let{
                    val database = it.openOrCreateDatabase("Playlists", Context.MODE_PRIVATE,null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS playlists(id INTEGER PRIMARY KEY,playlistName VARCHAR, gorsel BLOB)")

                    val sqlString = "INSERT INTO playlists (playlistName, gorsel) VALUES (?, ?)"
                    val statement = database.compileStatement(sqlString)
                    statement.bindString(1,playlistName)
                    statement.bindBlob(2,byteDizisi)
                    statement.execute()

                }


            }catch(e : Exception){
                e.printStackTrace()
            }

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.frameLayout, Playlists())
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }

    }
}













