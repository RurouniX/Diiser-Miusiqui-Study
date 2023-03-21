package com.diiser.home

import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaKeyboardInteractions.pressImeActionButton
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions
import com.adevinta.android.barista.interaction.BaristaSleepInteractions

class HomeActivityRobot(block: HomeActivityRobot.() -> Unit) {

    init {
        block.invoke(this)
    }

    fun isLoadingVisible() {
        assertDisplayed(R.id.stateview_Img)
    }

    fun isListVisible() {
        //TODO remover o sleep
        BaristaSleepInteractions.sleep(1000)
        assertDisplayed(R.id.list_home)
    }

    fun isFirstItemSoRockTitle() {
        assertDisplayedAtPosition(R.id.list_home, 0, R.id.itemArtistTitle, "Só Rock 2");
    }

    fun isFirstItemSubTitleRockDanger() {
        assertDisplayedAtPosition(R.id.list_home, 0, R.id.itemArtistSubTitle, "Rock Danger");
    }

    fun searchWithEmptyParameter() {
        clickOn(R.id.txt_search)
        pressImeActionButton()
        //TODO remover o sleep
        BaristaSleepInteractions.sleep(1000)
    }

    fun searchWithRockParameter() {
        BaristaEditTextInteractions.typeTo(R.id.txt_search, "Rock")
        pressImeActionButton()
        //TODO remover o sleep
    }

    fun showErrorView() {
        assertDisplayed(R.id.stateview)
        assertDisplayed(R.string.error_message_default)
    }
}