package com.esi.sba.powersh.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esi.sba.powersh.R
import com.esi.sba.powersh.model.AutoCompleteBox
import com.esi.sba.powersh.model.asAutoCompleteEntities
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.MainCardList
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import java.util.*


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@OptIn(ExperimentalComposeApi::class)
@Composable
fun RoundedSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    label: String = "Search",
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit
) {

    var queryState by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }


    val names = listOf(
        "Basket",
        "Running",
        "Swazilla",
        "Versac",
        "Weird"
    )


    AutoCompleteValueSample(items = names)

}


@ExperimentalAnimationApi
@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun SearchPreview() {

    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            RoundedSearchBar(
                modifier = Modifier,
                value = "Text",
                label = "Search",
                onDoneActionClick = {},
                onClearClick = {},
                onFocusChanged = {},
                onValueChanged = {}
            )

        }


    }

}


@ExperimentalAnimationApi
@Composable
fun AutoCompleteValueSample(items: List<String>) {

    val autoCompleteEntities = items.asAutoCompleteEntities(
        filter = { item, query ->
            item.toLowerCase(Locale.getDefault())
                .startsWith(query.toLowerCase(Locale.getDefault()))
        }
    )
    AutoCompleteBox(
        items = autoCompleteEntities,
        itemContent = { item ->
            ValueAutoCompleteItem(item.value)
        }
    ) {

        var queryState by remember {
            mutableStateOf(
                TextFieldValue(
                    text = ""
                )
            )
        }

        val view = LocalView.current

        onItemSelected { item ->
            queryState = TextFieldValue(item.value)
            filter(queryState.text)
            view.clearFocus()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = queryState,
                onValueChange = {
                    queryState = it
                    filter(queryState.text)

                },

                label = {
                    Text(
                        text = queryState.text,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Transparent,
                    cursorColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = CircleShape,
                textStyle = TextStyle(
                    fontSize = 11.sp,
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { view.clearFocus() }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                leadingIcon = {
                    IconButton(onClick = {
                        queryState = TextFieldValue("")
                        filter(queryState.text)
                        view.clearFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Clear"
                        )
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged {
                        isSearching = it.isFocused

                        //  isSearching = it == FocusState.Active
                    }
                    .background(shape = CircleShape, color = MainCardList)
                    .height(42.dp),
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
                    .size(36.dp)
                    .background(PowerSHRed, shape = RoundedCornerShape(8.dp)),

                onClick = {}
            ) {
                Icon(
                    modifier =
                    Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Settings button",
                )
            }
        }
    }
}

@Composable
fun ValueAutoCompleteItem(item: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.subtitle2,
            color = Color.Black
        )
    }
}