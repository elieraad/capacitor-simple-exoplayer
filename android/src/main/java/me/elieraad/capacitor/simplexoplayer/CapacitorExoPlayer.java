package me.elieraad.capacitor.simplexoplayer;

import android.content.Context;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

public class CapacitorExoPlayer {

    private com.google.android.exoplayer2.SimpleExoPlayer player;

    public CapacitorExoPlayer(Context context, String src) {
        player = new com.google.android.exoplayer2.SimpleExoPlayer.Builder(context).build();

        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(src);

        // Set the media item to be played.
        player.setMediaItem(mediaItem);

        // Prepare the player.
        player.prepare();
    }

    public void play() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public long duration() {
        return player.getDuration();
    }

    public long seek() {
        return player.getCurrentPosition() / 1000;
    }

    public void seek(long positionS) {
        player.seekTo(positionS * 1000);
    }

    public void release() {
        player.release();
    }
}
