package com.diiser.mockwebserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueue200Response(body: String, serverDelay: Long = 5000) {
    enqueue(MockResponse().setBody(body).setResponseCode(200))
}

fun MockWebServer.enqueueNoConnectivity() {
    enqueue(MockResponse().setResponseCode(999))
}