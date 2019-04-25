package com.ticka.application.widgets.particles.util;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

public final class ParticleColorResolver {

    private ParticleColorResolver() {

    }

    @ColorInt
    public static int resolveParticleColorWithSceneAlpha(
            @ColorInt final int particleColor,
            @IntRange(from = 0, to = 255) final int sceneAlpha
    ) {
        final int alpha = Color.alpha(particleColor) * sceneAlpha / 255;
        return (particleColor & 0x00FFFFFF) | (alpha << 24);
    }
}
