package com.esi.sba.powersh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esi.sba.powersh.ui.theme.PowerSHTheme


@Composable
fun RoundedSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
  //  onValueChanged: (String) -> Unit
) {



   TextField(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(1f)
            .onFocusChanged { onFocusChanged(it)}

       .background(shape = CircleShape, color = Color.Transparent),
       colors = TextFieldDefaults.textFieldColors(
           textColor = Color.Gray,
           disabledTextColor = Color.Transparent,
           focusedIndicatorColor = Color.Transparent,
           unfocusedIndicatorColor = Color.Transparent,
           disabledIndicatorColor = Color.Transparent
       ),
       shape = CircleShape,
       value = value,
        onValueChange = { query ->
         //   onValueChanged(query)
        },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = { onClearClick() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Clear")
            }
        },
        keyboardActions = KeyboardActions(onDone = { onDoneActionClick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        )
    )


}


@Preview
@Composable
fun SearchPreview(){

    PowerSHTheme() {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){

            RoundedSearchBar(
                modifier= Modifier,
            value= "Text",
            label=  "label",
            onDoneActionClick=  {},
            onClearClick=  {},
            onFocusChanged = {},
           // onValueChanged= (String) -> Unit
            )

        }


    }

}