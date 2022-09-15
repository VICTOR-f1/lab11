package com.example.trylistjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowBookActivity : AppCompatActivity() {

    private val contactList: MutableList<book> = mutableListOf()
    private lateinit var view1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)
        view1 = findViewById(R.id.view1)
        getContacts()
        contactList.forEach{
            Log.d("HeyBro!",it.toString())
            //выводит значения
            view1.text=contactList.toString()
            Log.d("HeyBro!", contactList.toString())
        }
    }
     // метод
    private fun getContacts(){
       //  оьбект класса getSharedPreferences
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
         //
        val tempList = Gson().fromJson<List<book>>(json, object: TypeToken<List<book>>(){}.type)
        contactList.addAll(tempList)
    }
}