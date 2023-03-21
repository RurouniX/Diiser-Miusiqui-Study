package com.diiser.home

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun shouldShowRockList_whenSuccess_ValidTitleAndSubTitle() {
        HomeActivityRobot {
            isLoadingVisible()
            isListVisible()
            isFirstItemSoRockTitle()
            isFirstItemSubTitleRockDanger()
        }
    }

    @Test
    fun shouldError_whenTryEmptySearch() {
        HomeActivityRobot {
            isLoadingVisible()
            isListVisible()
            searchWithEmptyParameter()
            showErrorView()
        }
    }

    @Test
    fun shouldShowRockList_whenTryAgain_ValidTitleAndSubTitle() {
        HomeActivityRobot {
            isLoadingVisible()
            isListVisible()
            searchWithEmptyParameter()
            showErrorView()
            searchWithRockParameter()
            isLoadingVisible()
            isListVisible()
            isFirstItemSoRockTitle()
            isFirstItemSubTitleRockDanger()
        }
    }
}