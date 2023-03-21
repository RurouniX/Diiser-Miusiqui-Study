package com.diiser.home

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diiser.mockwebserver.enqueueNoConnectivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

//    @get:Rule
//    val intentsTestRule = ActivityScenarioRule(HomeActivity::class.java)

    private lateinit var scenario: ActivityScenario<HomeActivity>

    @Before
    fun setUp() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)

        scenario = ActivityScenario.launch(intent)
    }

    private val server = MockWebServer()

    @Test
    fun useAppContext() {

        server.enqueueNoConnectivity()
    }
}