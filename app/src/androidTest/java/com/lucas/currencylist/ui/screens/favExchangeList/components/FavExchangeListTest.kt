package com.lucas.currencylist.ui.screens.favExchangeList.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.domain.useCases.PlatformState
import com.lucas.currencylist.R
import com.lucas.currencylist.ui.theme.CurrencyListTheme
import com.lucas.currencylist.ui.utils.TestTagConsts
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import java.util.*

class FavExchangeListTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun notEmptyStateList_showOnePlatformCard() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        with(composeRule) {

            setContent {
                CurrencyListTheme {
                    RenderNotEmptyList()
                }
            }

            val poweredByText = context.getString(R.string.webcard_poweredBy)

            // Check if "powered by" Text exists.
            // This component is rendered on each Platform Card
            onNode(
                hasText(poweredByText)
            ).assertExists()
        }
    }

    @Test
    fun emptyStateList_showEmptyListMessage() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        with(composeRule) {
            setContent {
                CurrencyListTheme {
                    RenderEmptyList()
                }
            }

            val emptyListMessageText = context.getString(R.string.favScreen_emptyList_message)
            onNode(
                hasText(emptyListMessageText)
            ).assertExists()
        }
    }

    @Test
    fun notEmptyStateList_hideEmptyListMessage() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        with(composeRule) {
            setContent {
                CurrencyListTheme {
                    RenderNotEmptyList()
                }
            }

            val emptyListMessageText = context.getString(R.string.favScreen_emptyList_message)
            onNode(
                hasText(emptyListMessageText)
            ).assertDoesNotExist()
        }
    }

    @Test
    fun click_exchangeValueFavButton__hideEmptyListMessage() {

        val onFavButtonPressed = mockk<(ExchangeValue) -> Unit>(relaxed = true)

        with(composeRule) {

            setContent {
                CurrencyListTheme {
                    RenderNotEmptyList()
                }
            }

            onNodeWithTag(TestTagConsts.Components.FavButton.FAV_BUTTON_TAG)
                .performClick()

            verify { onFavButtonPressed(any()) }
        }
    }
}

@Composable
private fun RenderNotEmptyList() {
    FavExchangeList(
        listOf(
            PlatformState(
                exchangeValues = listOf(
                    ExchangeValue(
                        exchangeFrom = CurrencyType.ARS,
                        exchangeTo = CurrencyType.ARS,
                        platform = ExchangePlatformType.Buenbit,
                        exchangeValue = 1.0
                    )
                ),
                platformType = ExchangePlatformType.None,
                lastUpdate = Date()
            )
        ),
        onFavExchangeValue = { }
    )
}

@Composable
private fun RenderEmptyList() {
    FavExchangeList(
        emptyList(),
        onFavExchangeValue = { }
    )
}