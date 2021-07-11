package me.elieraad.simpleexoplayer;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "SimpleExoPlayer")
public class SimpleExoPlayerPlugin extends Plugin {

    private static final String EVENT_EXOPLAYER_STATE_CHANGE = "onPlaybackStateChange";

    private String TAG = "SimpleExoPlayer";
    private CapacitorExoPlayer player;
    private AudioFocusHandler audioFocusHandler;

    @PluginMethod
    public void load(PluginCall call) {
        String src = call.getString("src");
        Log.i(TAG, String.format("loading src: %s", src));
        player = new CapacitorExoPlayer(this, src);
        audioFocusHandler = new AudioFocusHandler(this, player);
    }

    @PluginMethod
    public void play(PluginCall call) {
        AudioManager am = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        // Request audio focus for play back
        int result = am.requestAudioFocus(audioFocusHandler,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Log.i(TAG, "playing sound now");
            player.play();
        } else if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
            // take appropriate action
        }

    }

    @PluginMethod
    public void pause(PluginCall call) {
        Log.i(TAG, "pausing sound now");
        audioFocusHandler.setSkipAudioFocusLoss(true);
        player.pause();
    }

    @PluginMethod
    public void seek(PluginCall call) {
        String value = call.getString("value");

        if (value == null || value.trim().length() == 0) {
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

    public void emitEvent(PlaybackState state) {
        JSObject ret = new JSObject();
        ret.put("state", state);
        Log.i(TAG, String.format("Event %s detected, State: %s", EVENT_EXOPLAYER_STATE_CHANGE, state));
        notifyListeners(EVENT_EXOPLAYER_STATE_CHANGE, ret, true);
    }
}
