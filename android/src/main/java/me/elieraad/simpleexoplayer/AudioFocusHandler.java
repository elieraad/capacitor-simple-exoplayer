package me.elieraad.simpleexoplayer;

import android.media.AudioManager;
import android.util.Log;

public class AudioFocusHandler implements AudioManager.OnAudioFocusChangeListener {

    private static final String TAG = "AudioFocusHandler";
    private final CapacitorExoPlayer player;
    private final SimpleExoPlayerPlugin playerManager;
    private boolean skipAudioFocusLoss;

    public AudioFocusHandler(SimpleExoPlayerPlugin playerManager, CapacitorExoPlayer player) {
        this.playerManager = playerManager;
        this.player = player;
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {

            case AudioManager.AUDIOFOCUS_GAIN:
                Log.i(TAG, "AUDIOFOCUS_GAIN");
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT");
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:");
                playerManager.emitEvent(PlaybackState.onAudioFocusGain);
                player.play();
                break;

            case AudioManager.AUDIOFOCUS_LOSS:
                Log.e(TAG, "AUDIOFOCUS_LOSS");
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                if (!skipAudioFocusLoss) playerManager.emitEvent(PlaybackState.onAudioFocusLoss);
                player.pause();
                break;

        }
    }

    public void setSkipAudioFocusLoss(boolean skipAudioFocusLoss) {
        this.skipAudioFocusLoss = skipAudioFocusLoss;
    }
}
