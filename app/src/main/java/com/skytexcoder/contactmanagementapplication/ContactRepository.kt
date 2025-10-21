package com.skytexcoder.contactmanagementapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Contact(
    val id: Long,
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
)

object ContactRepository {
    val contactsList: SnapshotStateList<Contact> = mutableStateListOf()

    fun getContacts(): List<Contact> {
        return contactsList
    }

    fun addContact(contact: Contact) {
        contactsList.add(contact)
    }

    fun updateContact(contact: Contact) {
        val index = contactsList.indexOfFirst { it.id == contact.id }
        if (index != -1) {
            contactsList[index] = contact
        }
    }

    fun generateContactID(): Long {
        val lastContact = contactsList.lastOrNull()
        val newContactID = lastContact?.id?.plus(1) ?: 1
        println("new contact id: ${newContactID}")
        return newContactID
    }
}