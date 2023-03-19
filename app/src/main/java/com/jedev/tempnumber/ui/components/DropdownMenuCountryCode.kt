package com.jedev.tempnumber.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.jedev.tempnumber.R
import com.jedev.tempnumber.ui.screens.numberRedirect.OptionCountryCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuCountryCode(
    modifier: Modifier = Modifier,
    countryCode: String,
    onSetCountryCode: (String) -> Unit,
    options: List<OptionCountryCode>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        TextField(
            modifier = modifier
                .onFocusChanged {
                    if (it.isFocused) expanded = true
                }
                .background(Color.Gray),
            value = countryCode,
            onValueChange = {},
            label = { Text(text = stringResource(R.string.country_code)) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.country_code)
                    )
                }
            }
        )

        DropdownMenu(
            modifier = Modifier,
            onDismissRequest = { expanded = false },
            expanded = expanded,
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSetCountryCode(option.idd)
                    },
                    text = { Text(text = option.optionFormatted) },
                )
            }
        }
    }

}
