package com.esi.sba.powersh.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esi.sba.powersh.R
import com.esi.sba.powersh.components.LoginState
import com.esi.sba.powersh.components.loginTabs
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.PowerSHRed
import kotlinx.coroutines.launch


@Composable
fun mainCard(
             tabState: MutableState<Int>) {
        Column(
            modifier = Modifier
                .padding(start = 0.dp, end = 0.dp)
                .clip(
                    RoundedCornerShape(48.dp).copy(
                        topStart = ZeroCornerSize,
                        topEnd = ZeroCornerSize
                    )
                )
                .background(
                    color = PowerSHRed,
                )
            ,



        ) {
            Image(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 2.dp)
                    .size(80.dp)
                    .align(CenterHorizontally)
                ,
                painter = painterResource(id = R.drawable.powersh_without_background),
                contentDescription = "PowerSH icon",
                contentScale = ContentScale.Crop,


                )
            loginTabs(
                modifier = Modifier.align(CenterHorizontally),
                screenState = LoginState(),
                selectedScreen = tabState,
            )



        }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier,
    visibility: Boolean,
    bottomSheetScaffoldState: BottomSheetScaffoldState
){


    AnimatedVisibility(
        modifier = Modifier,
        visible = visibility,
        enter = fadeIn(initialAlpha = 0F, animationSpec = tween(300)),
        exit = fadeOut(targetAlpha = 0F, animationSpec = tween(300))
    ) {


        var emailState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        var passwordState = remember {
            mutableStateOf(TextFieldValue(""))
        }


        var isForgetButtonPressed by remember {
            mutableStateOf(false)
        }


        val view = LocalView.current

        val mEmailRequester = remember { FocusRequester() }

        val mPasswordFocusRequester = remember { FocusRequester() }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())

        ) {

            customTextField(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxWidth(),
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                title = "Email Address",
                icon = Icons.Outlined.Email,
                fieldState = emailState,
                focusRequester = mEmailRequester,
                autofillType = AutofillType.EmailAddress,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                onNext = {
                    mPasswordFocusRequester.requestFocus()
                },
                onDone = {}
            )

            passwordCustomTextField(
                modifier = Modifier.fillMaxWidth(),
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                title = "Password",
                fieldState = passwordState,
                focusRequester = mPasswordFocusRequester,
                autofillType = AutofillType.Password,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                onDone = {
                    view.clearFocus()
                }
            )

            ClickableText(
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 8.dp)
                    .align(CenterHorizontally),
                text = AnnotatedString("Forget Password?"),
                onClick = {
                    isForgetButtonPressed = true
                },
                style = TextStyle(
                    color = if (isForgetButtonPressed) PowerSHRed else Color.LightGray,

                    ),
            )


            if (isForgetButtonPressed) {
                coroutineScope.launch {

                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }


                    isForgetButtonPressed = false
                }

            }


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )


            Button(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = PowerSHRed,
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .background(color = PowerSHRed, shape = CircleShape),
                onClick = {

                }) {
                Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )


        }

    }
}






@ExperimentalAnimationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun signUpScreen(
    modifier: Modifier,
    visibility: Boolean
){

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibility,
        enter = fadeIn(initialAlpha = 0F, animationSpec = tween(300)),
        exit = fadeOut(targetAlpha = 0F, animationSpec = tween(300))
    ) {


        var emailState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        var passwordState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        var firstNameState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        var lastNameState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        var repeatPasswordState = remember {
            mutableStateOf(TextFieldValue(""))
        }


        val mlastNameRequester = remember { FocusRequester() }

        val mEmailRequester = remember { FocusRequester() }

        val mfirstNameRequester = remember { FocusRequester() }

        val view = LocalView.current

        val mPasswordFocusRequester = remember { FocusRequester() }
        val mRepeatPasswordFocusRequester = remember { FocusRequester() }


        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {

            Row {
                customTextField(
                    modifier = Modifier.weight(1f),
                    textFieldModifier = Modifier.padding(start = 16.dp, end = 8.dp),
                    title = "First Name",
                    icon = Icons.Outlined.AccountCircle,
                    fieldState = firstNameState,
                    focusRequester = mfirstNameRequester,
                    autofillType = AutofillType.PersonFirstName,
                    keyboardType = KeyboardType.Text,
                    onNext = {
                        mlastNameRequester.requestFocus()
                    },
                    onDone = {}
                )

                customTextField(
                    modifier = Modifier.weight(1f),
                    textFieldModifier = Modifier.padding(start = 8.dp, end = 16.dp),
                    title = "First Name",
                    icon = Icons.Outlined.AccountCircle,
                    fieldState = lastNameState,
                    focusRequester = mlastNameRequester,
                    autofillType = AutofillType.PersonLastName,
                    keyboardType = KeyboardType.Text,
                    onNext = {
                        mEmailRequester.requestFocus()
                    },
                    onDone = {}
                )


            }


            customTextField(
                modifier = Modifier.fillMaxWidth(),
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                title = "Email Address",
                icon = Icons.Outlined.Email,
                fieldState = emailState,
                focusRequester = mEmailRequester,
                autofillType = AutofillType.EmailAddress,
                keyboardType = KeyboardType.Email,
                onNext = {
                    mPasswordFocusRequester.requestFocus()
                },
                onDone = {}
            )






            passwordCustomTextField(
                modifier = Modifier.fillMaxWidth(),
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                title = "Password",
                fieldState = passwordState,
                focusRequester = mPasswordFocusRequester,
                autofillType = AutofillType.Password,
                keyboardType = KeyboardType.Password,
                onNext = {
                    mRepeatPasswordFocusRequester.requestFocus()
                }
            )



            passwordCustomTextField(
                modifier = Modifier.fillMaxWidth(),
                textFieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                title = "Repeat Password",
                fieldState = repeatPasswordState,
                focusRequester = mRepeatPasswordFocusRequester,
                autofillType = AutofillType.Password,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                onDone = {
                    view.clearFocus()
                }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )


            Button(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = PowerSHRed,
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .background(color = PowerSHRed, shape = CircleShape),
                onClick = {

                }) {
                Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )


        }

    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun customTextField(
    modifier: Modifier,
    textFieldModifier : Modifier = Modifier,
    title:  String,
    icon: ImageVector,
    fieldState: MutableState<TextFieldValue>,
    focusRequester: FocusRequester,
    autofillType: AutofillType,
    keyboardType: KeyboardType,
    imeAction : ImeAction = ImeAction.Next,
    onNext: () -> Unit = {},
    onDone: () -> Unit = {}
){

    var isFieldFocus by remember {
        mutableStateOf(false)
    }

    var isIconButtonPressed by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = if (isFieldFocus) PowerSHRed else Color.LightGray,
            modifier = Modifier.padding(top = 32.dp, start = 24.dp, bottom = 8.dp)
        )
        TextField(
            value = fieldState.value,
            onValueChange = {
                fieldState.value = it
            },
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = CardCoverPink,
                textColor = Color.Gray,
                cursorColor = PowerSHRed,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            label = {},
            leadingIcon = {
                IconButton(onClick = {
                    isIconButtonPressed = !isIconButtonPressed
                }) {
                    Icon(imageVector = icon, contentDescription = title)
                }

            },
            modifier = textFieldModifier
                .focusRequester(focusRequester)
                .align(CenterHorizontally)
                .onFocusChanged {
                    isFieldFocus = it.isFocused
                }
                .autofill(
                    autofillTypes = listOf(autofillType),
                    onFill = { fieldState.value = TextFieldValue(it) },
                ),
            singleLine = true,
            keyboardActions = KeyboardActions(onNext = {
                onNext()
            },
                onDone = {
                    onDone()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
        )
    }

}








@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun passwordCustomTextField(
    modifier: Modifier,
    textFieldModifier : Modifier = Modifier,
    title:  String,
    firstIcon: ImageVector= Icons.Outlined.VisibilityOff,
    secondIcon: ImageVector= Icons.Outlined.Visibility,
    fieldState: MutableState<TextFieldValue>,
    focusRequester: FocusRequester,
    autofillType: AutofillType,
    keyboardType: KeyboardType,
    imeAction : ImeAction = ImeAction.Next,
    onNext: () -> Unit = {},
    onDone: () -> Unit = {}
){

    var isFieldFocus by remember {
        mutableStateOf(false)
    }

    var isIconButtonPressed by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = if (isFieldFocus) PowerSHRed else Color.LightGray,
            modifier = Modifier.padding(top = 32.dp, start = 24.dp, bottom = 8.dp)
        )
        TextField(
            value = fieldState.value,
            onValueChange = {
                fieldState.value = it
            },
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = CardCoverPink,
                textColor = Color.Gray,
                cursorColor = PowerSHRed,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            label = {},
            leadingIcon = {

                IconButton(onClick = {
                    isIconButtonPressed = !isIconButtonPressed
                }) {
                    Icon(
                        imageVector =if (!isIconButtonPressed) firstIcon else secondIcon,
                        contentDescription = "Password")
                }

            },
            modifier = textFieldModifier
                .focusRequester(focusRequester)
                .align(CenterHorizontally)
                .onFocusChanged {
                    isFieldFocus = it.isFocused
                }
                .autofill(
                    autofillTypes = listOf(autofillType),
                    onFill = { fieldState.value = TextFieldValue(it) },
                ),
            singleLine = true,
            visualTransformation = if (isIconButtonPressed) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(onNext = {
                onNext()
            },
                onDone = {
                    onDone()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
        )
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun forgetPasswordBottomSheet(){

    var emailState = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val mEmailRequester = remember { FocusRequester() }


    val view = LocalView.current

Column(
    modifier =Modifier
) {


    Text(
        text = "Reset Password",
        fontWeight = FontWeight.Bold,
        fontSize =20.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 16.dp, top = 36.dp, bottom = 16.dp)
    )

    customTextField(
        modifier = Modifier.fillMaxWidth(),
        textFieldModifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        title = "Email Address",
        icon = Icons.Outlined.Email,
        fieldState = emailState,
        focusRequester = mEmailRequester,
        autofillType = AutofillType.EmailAddress,
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Done,
        onDone = {
            view.clearFocus()
        }
    )



    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = PowerSHRed,
        ),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 64.dp, top= 16.dp)
            .fillMaxWidth()
            .align(CenterHorizontally)
            .background(color = PowerSHRed, shape = RoundedCornerShape(18.dp)),
        onClick = {

        }) {
        Text(
            text = "Login",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                top = 6.dp,
                bottom = 6.dp
            )
        )
    }


}

}




@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun authentificationScreen(
    tabState : MutableState<Int>
){


    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent ={
            forgetPasswordBottomSheet()
        } ,
        sheetPeekHeight = 0.dp,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            mainCard(
                tabState  = tabState,
            )
        },

    ) {

            LoginScreen(
                bottomSheetScaffoldState =bottomSheetScaffoldState,
                visibility = (tabState.value == 0),
                modifier =  Modifier
            )

            signUpScreen(
                visibility = (tabState.value == 1),
                        modifier =  Modifier
            )




    }


















}




@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autofill(
    autofillTypes: List<AutofillType>,
    onFill: ((String) -> Unit),
) = composed {
    val autofill = LocalAutofill.current
    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = autofillTypes)
    LocalAutofillTree.current += autofillNode

    this
        .onGloballyPositioned {
            autofillNode.boundingBox = it.boundsInWindow()
        }
        .onFocusChanged { focusState ->
            autofill?.run {
                if (focusState.isFocused) {
                    requestAutofillForNode(autofillNode)
                } else {
                    cancelAutofillForNode(autofillNode)
                }
            }
        }
}




@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun LoginPage1() {
    var tabState = remember {
        mutableStateOf(0)
    }

    authentificationScreen(tabState = tabState)

}