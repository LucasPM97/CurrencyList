package com.lucas.currencylist.ui.screens.favExchangeList.components

import android.content.Context
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.lucas.currencylist.ui.theme.CurrencyListTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.lucas.currencylist.R

class TopBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {

        composeRule.setContent {
            CurrencyListTheme {
                TopBar()
            }
        }
    }

    @Test
    fun Check_TopBarTitle() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val topBarTitle = context.getString(R.string.favorites_tabBarTitle)
        composeRule.onNode(
            hasText(
                topBarTitle
            )
        ).assertExists()
    }
}