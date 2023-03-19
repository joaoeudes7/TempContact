package com.jedev.whatsnumber.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jedev.whatsnumber.R
import org.koin.androidx.compose.get

@Composable
fun SettingsScreen(
    navController: NavController,
    vm: SettingsViewModel = get()
) {
    LaunchedEffect(true) {
        vm.fetchData()
    }

    BackHandler {
        navController.popBackStack()
    }

    SettingsInteractions(
        state = vm.uiState,
        actions = SettingsUiActions(
            onSave = vm::saveData,
            onChangePrefix = vm::onChangePrefix
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsInteractions(
    state: SettingsUiState,
    actions: SettingsUiActions
) = Box(modifier = Modifier.fillMaxWidth()) {
    Column(Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            value = state.prefix,
            label = { Text(text = stringResource(R.string.default_prefix_number)) },
            onValueChange = actions.onChangePrefix
        )
    }
}
