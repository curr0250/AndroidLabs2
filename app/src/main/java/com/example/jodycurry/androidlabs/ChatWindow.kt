package com.example.jodycurry.androidlabs

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.jodycurry.androidlabs.R.id.item_list

class ChatWindow : Activity() {

    val ACTIVITY_NAME = "ChatWindow"
//    var item_list = null as? ListView
//    var text_edit = null as? EditText
//    var chat_send = null as? Button

//        val CREATION_STATEMENT =
//                "CREATE TABLE MESSAGES ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//              " aMessage text ) "

    var messages = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)

        var item_list = findViewById<ListView>(R.id.item_list)

        var text_edit = findViewById<EditText>(R.id.text_edit)


        var chat_send = findViewById<Button>(R.id.chatSend)
        chat_send.setOnClickListener {
            var newMessage = text_edit.text
            messages.add(newMessage.toString())
            text_edit.setText( "" )


        }
        var theAdapter = ChatAdapter(this)

        item_list?.setAdapter( theAdapter )
    }

    inner class ChatAdapter(ctx : Context) : ArrayAdapter<String>(ctx, 0 ) {

        override fun getCount(): Int { //how many times
                return messages.size
            }

            override fun getItem(position: Int): String {
                return messages.get(position)
            }

//            fun getView(position: Int, convertView: ListView): View {
//                return
//            }

        override fun getItemId(position:Int):Long {
                return 0
            }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var inflater = LayoutInflater.from(parent?.getContext())

            var thisRow = null as View?

            if(position%2 == 0)

                thisRow = inflater.inflate(R.layout.chat_row_incoming, null)

            else

                thisRow = inflater.inflate(R.layout.chat_row_outgoing, null)

            inflater.inflate(R.layout.chat_row_outgoing, null)

            var textView = thisRow.findViewById<TextView>(R.id.message_text)
            textView.setText( getItem(position))
            return thisRow




        }

        }


    }

