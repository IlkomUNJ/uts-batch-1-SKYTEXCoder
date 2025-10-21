package com.skytexcoder.contactmanagementapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOREditContactScreen(navigationController: NavController, ContactID: Long = -1) {
    var contactID by remember { mutableStateOf("") }
    var contactName by remember { mutableStateOf("") }
    var contactAddress by remember { mutableStateOf("") }
    var contactPhone by remember { mutableStateOf("") }
    var contactEmail by remember { mutableStateOf("") }
    var isAddressLessThanFiveWords by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add New Contact",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = contactName,
            onValueChange = { contactName = it },
            label = { Text("Contact Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contactAddress,
            onValueChange = { contactAddress = it },
            label = { Text("Contact Address") },
            modifier = Modifier.fillMaxWidth(),
            isError = isAddressLessThanFiveWords,
            supportingText = {
                if (isAddressLessThanFiveWords) {
                    Text (
                        text = "Detected address string contains less than 5 words. Please specify an address string with AT LEAST 5 words.",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contactPhone,
            onValueChange = { contactPhone = it },
            label = { Text("Contact Phone") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = contactEmail,
            onValueChange = { contactEmail = it },
            label = { Text("Contact Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                if (contactAddress.split(" ").size < 5) {
                    isAddressLessThanFiveWords = true
                    return@Button
                }

                val newContact = Contact(
                    id = ContactRepository.generateContactID(),
                    name = contactName,
                    phone = contactPhone,
                    address = contactAddress,
                    email = contactEmail,
                )
                ContactRepository.addContact(newContact)
                navigationController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp),
            enabled = contactName.isNotBlank() && contactPhone.isNotBlank() && contactAddress.isNotBlank() && contactEmail.isNotBlank(),
        ) {
            Text("Add A New Contact", style = MaterialTheme.typography.titleMedium)
        }
    }
}
