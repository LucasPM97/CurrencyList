package com.lucas.currencylist.ui.screens.favExchangeList

import androidx.compose.ui.test.junit4.createComposeRule
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavExchangeListScreenTest {

    @get:Rule
    val rule = createComposeRule()

    private lateinit var viewModel: FavExchangeListViewModel

    @Before
    fun setup() {
        viewModel = mockk(relaxed = true)
    }

    @Test
    fun Show_screen() {
        rule.setContent {
            FavExchangeListScreen(
                null,
                viewModel
            )
        }
    }

}