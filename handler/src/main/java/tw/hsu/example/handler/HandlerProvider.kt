package tw.hsu.example.handler

import android.os.Handler

interface HandlerProvider {

    fun handler() : Handler;

}