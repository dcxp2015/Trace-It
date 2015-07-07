package com.dcxp.traceit;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Daniel on 7/7/2015.
 */
public class SoundManager {
    public static final int SOUND_POP = 0;
    private SoundPool soundPool;

    public SoundManager(Context context) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool.load(context, R.raw.pop, 1);
    }

    public void play(int sound) {
        soundPool.play(sound, 1, 1, 1, 0, 1f);
    }

    public void unload() {
        soundPool.unload(SOUND_POP);
    }
}
