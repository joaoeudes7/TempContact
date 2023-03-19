@file:OptIn(ExperimentalMaterial3Api::class)

package com.jedev.tempnumber.ui.screens.numberRedirect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jedev.tempnumber.R
import com.jedev.tempnumber.Routers
import com.jedev.tempnumber.domains.historic.model.TempContactModel
import com.jedev.tempnumber.ui.components.AdvertView
import org.koin.androidx.compose.getViewModel


@Composable
fun NumberRedirectScreen(
    navController: NavController,
    vm: NumberRedirectViewModel = getViewModel(),
) {
    LaunchedEffect(true) {
        vm.fetchData()
    }

    LaunchedEffect(Unit) {
        vm.checkClipboard()
    }

    NumberRedirectInteractions(
        state = vm.uiState,
        actions = NumberRedirectActions(
            onSetNumber = vm::onSetNumber,
            onSendMessageWpp = vm::onSendMessageOnWhatsapp,
            onSendMessageOther = vm::onSendMessageOnOther,
            onSendMessageTg = vm::onSendMessageOnTelegram,
            onRemoveHistoric = vm::onRemoveHistoric,
            onRemoveAllHistoric = vm::onRemoveAllHistoric,
            onPasteNumber = vm::onPasteNumber,
            goToSettings = { navController.navigate(Routers.numberTemp) }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NumberRedirectInteractions(
    state: UiStateNumberRedirect = UiStateNumberRedirect().copy(
        historicNumbers = listOf(
            TempContactModel(number = "5584999106028"),
        )
    ),
    actions: NumberRedirectActions = NumberRedirectActions(
        onSetNumber = {},
        onSendMessageWpp = {},
        onSendMessageTg = {},
        onRemoveHistoric = {},
        onRemoveAllHistoric = {},
        onSendMessageOther = {},
        onPasteNumber = {},
        goToSettings = {}
    )
) = Column(
    Modifier
        .fillMaxSize()
) {
    AdvertView()

    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            value = state.tempContact.numberFormatted,
            label = { Text(text = stringResource(R.string.number)) },
            onValueChange = actions.onSetNumber
        )

        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .weight(0.5f),
            onClick = actions.goToSettings
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.settings)
            )
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            enabled = state.tempContact.isValid,
            onClick = actions.onSendMessageWpp
        ) {
            Text(text = stringResource(R.string.whatsapp))
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            enabled = state.tempContact.isValid,
            onClick = actions.onSendMessageTg
        ) {
            Text(text = stringResource(R.string.telegram))
        }

        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .weight(0.5f),
            onClick = actions.onSendMessageOther
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.AddCircle,
                contentDescription = stringResource(R.string.other)
            )
        }
    }

    ListHistoric(
        modifier = Modifier.padding(horizontal = 16.dp),
        historicNumbers = state.historicNumbers,
        onRemoveHistoric = actions.onRemoveHistoric,
        onRemoveAllHistoric = actions.onRemoveAllHistoric
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (state.snackBarVisible) {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                action = {
                    Button(
                        onClick = { actions.onPasteNumber() }
                    ) {
                        Text("Paste")
                    }
                },
            ) { Text(text = "Number on clipboard detected") }
        }
    }
}

@Composable
fun ListHistoric(
    modifier: Modifier,
    historicNumbers: List<TempContactModel>,
    onRemoveHistoric: (Int) -> Unit,
    onRemoveAllHistoric: () -> Unit = {}
) = LazyColumn(modifier = modifier.fillMaxWidth()) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.historic),
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                modifier = Modifier.padding(16.dp),
                onClick = { onRemoveAllHistoric() }
            ) {
                Text(text = stringResource(R.string.clear_all))
            }
        }

        if (historicNumbers.isEmpty()) {
            EmptyInfo(
                modifier.padding(top = 32.dp)
            )
        }
    }

    items(historicNumbers.size) { index ->
        HistoricItem(
            historicNumber = historicNumbers[index],
            onRemoveHistoric = onRemoveHistoric
        )
    }

}

@Composable
fun EmptyInfo(
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Icon(
        modifier = Modifier
            .padding(16.dp)
            .size(48.dp),
        imageVector = Icons.Default.Info,
        contentDescription = stringResource(R.string.no_historic_found)
    )

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(R.string.no_historic_found),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun HistoricItem(
    historicNumber: TempContactModel,
    onRemoveHistoric: (Int) -> Unit
) = Column(Modifier.padding(8.dp)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = historicNumber.numberFormatted,
            style = MaterialTheme.typography.bodyMedium
        )

        IconButton(onClick = { onRemoveHistoric(historicNumber.id!!) }) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.remove)
            )
        }
    }

    Divider()
}
