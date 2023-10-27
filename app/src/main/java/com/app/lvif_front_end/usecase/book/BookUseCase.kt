package com.app.lvif_front_end.usecase.book

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class BookUseCase {
    private val BASE_WS_URL = "wss://lvif-api.lldevll.xyz/api/v1/"

    fun onInit(
        uri: String,
        token: String,
        onOpenWs: (WebSocket, Response) -> Unit,
        onMessageWs: (WebSocket, String) -> Unit,
        onErrorWs: (WebSocket, Throwable, Response?) -> Unit,
        //onCloseWs: (WebSocket, Int, String) -> Unit
    ): WebSocket {
        Log.e("ws-connection-uri", "$BASE_WS_URL$uri")
        return OkHttpClient().newWebSocket(Request.Builder().header(
            "Authorization", "Bearer $token"
        ).url("$BASE_WS_URL$uri").build(), object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onOpenWs(webSocket, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                onMessageWs(webSocket, text)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                //onCloseWs(webSocket, code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onErrorWs(webSocket, t, response)
            }
        })
    }


}