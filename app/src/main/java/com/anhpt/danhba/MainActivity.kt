package com.anhpt.danhba

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var items: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = findViewById(R.id.contactListView)
        val contacts = listOf(
            Contact("1", "Anh Phan 1", "123456789", "anh.phan@email.com"),
            Contact("2", "Anh Phan 2", "123456789", "anh.phan@email.com"),
            Contact("3", "Anh Phan 3", "123456789", "anh.phan@email.com"),
            Contact("4", "Anh Phan 4", "123456789", "anh.phan@email.com"),
            Contact("5", "Anh Phan 5", "123456789", "anh.phan@email.com"),
            Contact("6", "Anh Phan 6", "123456789", "anh.phan@email.com"),
            Contact("7", "Anh Phan 7", "123456789", "anh.phan@email.com"),
            Contact("8", "Anh Phan 8", "123456789", "anh.phan@email.com"),
            Contact("9", "Anh Phan 9", "123456789", "anh.phan@email.com"),
            Contact("10", "Anh Phan 10", "123456789", "anh.phan@email.com"),
            Contact("11", "Anh Phan 11", "123456789", "anh.phan@email.com"),
            Contact("12", "Anh Phan 12", "123456789", "anh.phan@email.com"),
            Contact("13", "Anh Phan 13", "123456789", "anh.phan@email.com"),
            Contact("14", "Anh Phan 14", "123456789", "anh.phan@email.com"),
            Contact("15", "Anh Phan 15", "123456789", "anh.phan@email.com"),
            Contact("16", "Anh Phan 16", "123456789", "anh.phan@email.com"),
            Contact("17", "Anh Phan 17", "123456789", "anh.phan@email.com"),
            Contact("18", "Anh Phan 18", "123456789", "anh.phan@email.com"),
            Contact("19", "Anh Phan 19", "123456789", "anh.phan@email.com"),
            Contact("20", "Anh Phan 20", "123456789", "anh.phan@email.com"),
        )
        val adapter = ContactAdapter(this, contacts)
        items.adapter = adapter

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {

            } else {
            }
        }

        items.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("name", selectedContact.name)
            intent.putExtra("phone", selectedContact.phoneNumber)
            intent.putExtra("email", selectedContact.email)
            launcher.launch(intent)
        }

        registerForContextMenu(items)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedContact = items.adapter.getItem(info.position) as Contact

        if (item.itemId == R.id.menuCall) {
            val myString = "tel:$selectedContact.phoneNumber"
            val myActivity = Intent(
                Intent.ACTION_DIAL,
                Uri.parse(myString)
            )
            startActivity(myActivity)
        } else if (item.itemId == R.id.menuSms) {
            val myString = "smsto:$selectedContact.phoneNumber"
            val myActivity = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse(myString)
            )
            startActivity(myActivity)
        } else if (item.itemId == R.id.menuEmail) {
            val emailSubject = "Department Meeting"
            val emailText = "We’ll discuss the new project " + "on Tue. at 9:00am @ room BU344"
            val emailReceiverList = selectedContact.email
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "vnd.android.cursor.dir/email"
            intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            intent.putExtra(Intent.EXTRA_TEXT, emailText)
            intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList)
            startActivity(intent)
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.context_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuCall) {
        } else if (item.itemId == R.id.menuSms) {
        } else if (item.itemId == R.id.menuEmail) {
        }

        return super.onOptionsItemSelected(item)
    }
}