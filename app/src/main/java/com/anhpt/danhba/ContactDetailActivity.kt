package com.anhpt.danhba

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        try {
            val nameR = intent.getStringExtra("name")
            val phoneR = intent.getStringExtra("phone")
            val emailR = intent.getStringExtra("email")

            val nameTextView = findViewById<TextView>(R.id.textName)
            val phoneNumberTextView = findViewById<TextView>(R.id.textPhoneNumber)
            val emailTextView = findViewById<TextView>(R.id.textEmail)

            nameTextView.text = nameR
            phoneNumberTextView.text = phoneR
            emailTextView.text = emailR

            findViewById<ImageView>(R.id.iconPhone).setOnClickListener {
                val myString = "tel:$phoneR"
                val myActivity = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse(myString)
                )
                startActivity(myActivity)
            }

            findViewById<ImageView>(R.id.iconSMS).setOnClickListener {
                val myString = "smsto:$phoneR"
                val myActivity = Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse(myString)
                )
                startActivity(myActivity)
            }

            findViewById<ImageView>(R.id.iconGmail).setOnClickListener {
                val emailSubject = "Department Meeting"
                val emailText = "We’ll discuss the new project " + "on Tue. at 9:00am @ room BU344"
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "vnd.android.cursor.dir/email"
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                intent.putExtra(Intent.EXTRA_TEXT, emailText)
                intent.putExtra(Intent.EXTRA_EMAIL, emailR)
                startActivity(intent)
            }
            setResult(Activity.RESULT_OK, intent)
        } catch (ex: Exception) {
            setResult(Activity.RESULT_CANCELED)
        }


    }
}