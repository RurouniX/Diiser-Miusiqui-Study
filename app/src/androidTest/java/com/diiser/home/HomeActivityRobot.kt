package com.diiser.home

class HomeActivityRobot(block: HomeActivityRobot.() -> Unit) {

    init {
        block.invoke(this)
    }
}