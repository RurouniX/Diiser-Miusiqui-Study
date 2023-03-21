package com.diiser.player

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.adevinta.android.barista.interaction.BaristaSleepInteractions
import com.diiser.navigation.player.PlayerNavigation
import com.diiser.response200Home
import com.diiser.response200HomeJson
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PlayerActivityTest {

    private lateinit var scenario: ActivityScenario<PlayerActivity>

    @Before
    fun setUp() {
        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            PlayerActivity::class.java
        ).putExtra(
            PlayerNavigation.EXTRA_DATAMODEL, response200Home().data[0]
        )
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun shouldShowRockList_whenSuccess_ValidTitleAndSubTitle() {
        PlayerActivityRobot {
            isLoadingVisible()
            BaristaSleepInteractions.sleep(2000)
            isListVisible()
            isTitleMetal()
            isSubTitlePorDivij()
            isSecondItemSubTitleMetal()
            isSecondItemMetalTitle()
        }
    }

}