package com.test.alorferi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.alorferi.datastore.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val datastoreManager: DatastoreManager
) : ViewModel() {

    val isLoggedIn = datastoreManager.isLoggedIn()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
}