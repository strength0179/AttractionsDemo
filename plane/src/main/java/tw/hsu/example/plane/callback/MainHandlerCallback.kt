package tw.hsu.example.plane.callback

import android.os.Handler
import android.os.Message

class MainHandlerCallback : Handler.Callback {
    override fun handleMessage(msg: Message): Boolean {
        (msg.obj as Runnable).run();
        return true;
    }
}