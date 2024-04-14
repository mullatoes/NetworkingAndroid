package com.kyeiiih.networking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kyeiiih.networking.network.AllUsersUiState
import com.kyeiiih.networking.network.ApiClient.userService
import com.kyeiiih.networking.network.UserUiState
import com.kyeiiih.networking.paging.userFlow
import com.kyeiiih.networking.ui.components.UserScreen
import com.kyeiiih.networking.ui.theme.NetworkingTheme
import com.kyeiiih.networking.viewmodel.UserViewModel
import com.kyeiiih.networking.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkingTheme {
                // UserByIdScreen()
                UserListScreen()
            }
        }
    }
}

@Composable
fun UserByIdScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val viewModel: UserViewModel = viewModel(
            factory = UserViewModelFactory(userService)
        )

        LaunchedEffect(Unit) {
            viewModel.getUserById(1)
        }

        val userState by viewModel.userUiState.observeAsState(UserUiState.Loading)

        when (userState) {
            is UserUiState.Success -> {
                val user = (userState as UserUiState.Success).user
                UserScreen(user = user)
            }

            UserUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            UserUiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error fetching user")
                }
            }
        }
    }
}


@Composable
fun UserListScreen() {
    val lazyPagingItems = userFlow.collectAsLazyPagingItems()

    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(userService)
    )

    LaunchedEffect(Unit) {
        viewModel.getUsers(1)
    }

    val userState by viewModel.allUsersUiState.observeAsState(AllUsersUiState.Loading)

    when (userState) {
        is AllUsersUiState.Success -> {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                items(lazyPagingItems.itemCount) { index ->
                    val user = lazyPagingItems[index]
                    UserScreen(user = user!!)
                    Divider(
                    )
                }
            }

        }

        AllUsersUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        AllUsersUiState.Error -> {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error fetching user")
            }
        }
    }
}

