package com.diiser.player

import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaSleepInteractions

class PlayerActivityRobot(block: PlayerActivityRobot.() -> Unit) {

    init {
        block.invoke(this)
    }

    fun isLoadingVisible() {
        assertDisplayed(R.id.stateview_Img)
    }

    fun isListVisible() {
        //TODO remover o sleep
        BaristaSleepInteractions.sleep(1000)
        assertDisplayed(R.id.list_others_musics)
    }

    fun isTitleMetal(){
        assertDisplayed(R.id.txt_music_name, "Metal")
    }

    fun isSubTitlePorDivij(){
        assertDisplayed(R.id.txt_artist_name, "por Divij Mosalpuri")
    }

    fun isSecondItemMetalTitle() {
        assertDisplayedAtPosition(R.id.list_others_musics, 0, R.id.txt_music_name, "Metal");
    }

    fun isSecondItemSubTitleMetal() {
        assertDisplayedAtPosition(R.id.list_others_musics, 0, R.id.txt_disc_name, "Metal");
    }

}