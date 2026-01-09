package com.calvinnordstrom.minefront.util;

import net.minecraft.util.RandomSource;

public class SoundUtil {
    private SoundUtil() {
    }

    public static float getPitch(RandomSource random) {
        return 0.9F + random.nextFloat() * 0.2F;
    }
}
