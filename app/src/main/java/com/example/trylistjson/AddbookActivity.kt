package com.example.trylistjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddbookActivity : AppCompatActivity() {
   //лист который можно имзменять
    private var contactList: MutableList<book> = mutableListOf()

    private lateinit var title:EditText
    private lateinit var genre:EditText
    private lateinit var author:EditText
    private lateinit var year_of_publishing:EditText
    private lateinit var cover_type:EditText
    private lateinit var NUMBER_OF_PAGES:EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        // метод
        getContacts()

        title = findViewById(R.id.editTextTexttitle)
        genre = findViewById(R.id.editTextTexjanr)
        author = findViewById(R.id.editTextTextEmailAuhtor)
        year_of_publishing = findViewById(R.id.editTextTextyear_of_publishing)
        cover_type = findViewById(R.id.editTextTextcover_type)
        NUMBER_OF_PAGES = findViewById(R.id.editTextTextNUMBER_OF_PAGES)
        button = findViewById(R.id.button3)

        button.setOnClickListener {
            //отправляет значения в метод
            addBook(title.text.toString(), genre.text.toString(),author.text.toString(),year_of_publishing.text.toString(), cover_type.text.toString(),NUMBER_OF_PAGES.text.toString())
            //выводит значения
            Toast.makeText(this, "all good", Toast.LENGTH_SHORT).show()
            Log.d("HeyBro!", contactList.toString())
        }
    }
   // метод
    private fun getContacts(){
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<book>>(json, object: TypeToken<List<book>>(){}.type)
        contactList.addAll(tempList)
    }

    private fun addBook(title:String, genre:String,author:String,year_of_publishing:String, cover_type:String,NUMBER_OF_PAGES:String){
        val contact = book(title, genre,author,year_of_publishing,cover_type,NUMBER_OF_PAGES)
        contactList.add(contact)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit {
            this.putString("json", Gson().toJson(contactList).toString())
        }
    }
}
