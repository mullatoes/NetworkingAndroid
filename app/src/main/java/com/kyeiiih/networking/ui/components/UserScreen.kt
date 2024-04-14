package com.kyeiiih.networking.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kyeiiih.networking.model.User

@Composable
fun UserScreen(user: User) {
    Column {
        Text(text = "Name: ${user.name}")
        Text(text = "Username: ${user.username}")
        Text(text = "Email: ${user.email}")
        Text(text = "Address: ${user.address.street}, ${user.address.city}, ${user.address.zipCode}")
        Text(text = "Phone: ${user.phone}")
        Text(text = "Website: ${user.website}")
        Text(text = "Company: ${user.company.name}")
    }
}