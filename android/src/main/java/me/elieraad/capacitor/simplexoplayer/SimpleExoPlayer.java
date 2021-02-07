package me.elieraad.capacitor.simplexoplayer;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import org.json.JSONException;
import org.json.JSONObject;

@NativePlugin
public class SimpleExoPlayer extends Plugin {

    private String TAG = "SimpleExoPlayer";
    private CapacitorExoPlayer player;

    @PluginMethod
    public void load(PluginCall call) {
        String src = call.getString("src");
        Log.i(TAG, String.format("loading src: %s", src));
        player = new CapacitorExoPlayer(getContext(), src);
    }

    @PluginMethod
    public void play(PluginCall call) {
        Log.i(TAG, "playing sound now");
        player.play();
    }

    @PluginMethod
    public void pause(PluginCall call) {
        Log.i(TAG, "pausing sound now");
        player.pause();
    }

    @PluginMethod
    public void seek(PluginCall call) {
        String value = call.getString("value");

        if(value == null || value.trim().length() == 0) {
            JSObject data = new JSObject();
            long currTime = player.seek();
            Log.i(TAG, String.format("currentTime: %d seconds", currTime));
            data.put("value", currTime);
            call.resolve(data);
            return;
        }

        long seekTo = Long.parseLong(value);
        Log.i(TAG, String.format("Seeking to: %d seconds", seekTo));
        player.seek(seekTo);
    }

    @PluginMethod
    public void duration(PluginCall call) {
        JSObject data = new JSObject();

        long duration = player.duration();
        Log.i(TAG, String.format("duration: %d seconds", duration));

        data.put("value", duration);
        call.resolve(data);
    }

    @PluginMethod
    public void release(PluginCall call) {
        Log.i(TAG, "releasing sound now");
        player.release();
    }
}
