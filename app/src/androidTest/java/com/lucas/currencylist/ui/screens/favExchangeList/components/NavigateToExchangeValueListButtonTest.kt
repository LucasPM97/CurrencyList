package com.lucas.currencylist.ui.screens.favExchangeList.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import com.lucas.currencylist.NavigationConsts
import com.lucas.currencylist.ui.theme.CurrencyListTheme
import com.lucas.currencylist.ui.utils.TestTagConsts
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigateToExchangeValueListButtonTest {
    @get:Rule
    val composeRule = createComposeRule()

    lateinit var navController: NavController

    @Before
    fun setup() {
        navController = mockk(relaxed = true)
        composeRule.setContent {
            CurrencyListTheme {
                NavigateToExchangeValueListButton(
                    Modifier.testTag(
                        TestTagConsts.FavExchangeListScreen.FLOATING_BUTTON_TAG
                    ),
                    navController = navController
                )
            }
        }
    }

    @Test
    fun check_floatingButton_exists_isClickable() {

        val floatingButton = composeRule
            .onNodeWithTag(
                TestTagConsts.FavExchangeListScreen.FLOATING_BUTTON_TAG
            )

        floatingButton
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun check_navigationController_wasCalled() {
        val floatingButton = composeRule
            .onNodeWithTag(
                TestTagConsts.FavExchangeListScreen.FLOATING_BUTTON_TAG
            )

        every { navController.navigate(NavigationConsts.EXCHANGE_LIST_SCREEN) } returns Unit

        floatingButton.performClick()

        verify { navController.navigate(NavigationConsts.EXCHANGE_LIST_SCREEN) }
    }
}