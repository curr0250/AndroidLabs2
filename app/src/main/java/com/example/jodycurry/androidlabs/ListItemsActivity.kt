package com.example.jodycurry.androidlabs


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.example.jodycurry.androidlabs.R.id.switch1
import kotlinx.android.synthetic.main.activity_list_items.*

class ListItemsActivity : Activity() {

    val ACTIVITY_NAME = "ListItemsActivity"
    val REQUEST_IMAGE_CAPTURE = 1
    var button2  = null as? ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

/////setOnCheckedChange for switch
        var box = findViewById<CheckBox>(R.id.checkBox)
        box.setOnCheckedChangeListener{e , f->

            val builder = AlertDialog.Builder(this)

// 2. Chain together various setter methods to set the dialog characteristics

            builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                    .setTitle(R.string.dialog_title)

                    .setPositiveButton(R.string.ok) { e, f ->

                        val resultIntent = Intent( )

                        resultIntent.putExtra("Response", "ListItemsActivity passed: My information to share")

                        setResult(Activity.RESULT_OK, resultIntent)

                        finish()
                        // User clicked OK button


                    }

                    .setNegativeButton(R.string.cancel, {e, f ->

// User cancelled the dialog

        }).show()
        }


        var switch = findViewById<Switch>(R.id.switch1)
        switch.setOnCheckedChangeListener{e , f->
            if(switch.isChecked)
            {
                val text = "Switch is On"

                val duration = Toast.LENGTH_SHORT
                Toast.makeText(this@ListItemsActivity, text, duration).show()
            }
            else
            {
                val text = "Switch is off"

                val duration = Toast.LENGTH_LONG
                Toast.makeText(this@ListItemsActivity, text, duration).show()
            }
        }


         button2 = findViewById(R.id.imageButton)
        button2?.setOnClickListener {

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }



        }


    }

    override fun onStart()
    {
        super.onStart()
        Log.i(ACTIVITY_NAME, "In onStart")
    }


    override fun onResume()
    {
        super.onResume()
        //Log.i(ACTIVITY_NAME, "In onResume")
    }


    override fun onPause()
    {
        super.onPause()
        //Log.i(ACTIVITY_NAME, "In onPause")
    }

    override fun onStop()
    {
        super.onStop()
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data.extras.get("data") as Bitmap
            button2?.setImageBitmap(imageBitmap)
        }
    }
}
