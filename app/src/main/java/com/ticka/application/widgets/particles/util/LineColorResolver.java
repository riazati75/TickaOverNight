package com.ticka.application.widgets.particles.util;

import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

import com.ticka.application.widgets.particles.KeepAsApi;

@KeepAsApi
public final class LineColorResolver {

    @IntRange(from = 0, to = 255)
    private static int resolveLineAlpha(
            @IntRange(from = 0, to = 255) final int sceneAlpha,
            final float maxDistance,
            final float distance) {
        final float alphaPercent = 1f - distance / maxDistance;
        final int alpha = (int) (255f * alphaPercent);
        return alpha * sceneAlpha / 255;
    }

    @ColorInt
    public static int resolveLineColorWithAlpha(
            @IntRange(from = 0, to = 255) final int sceneAlpha,
            @ColorInt final int lineColor,
            final float maxDistance,
            final float distance) {
        final int alpha = resolveLineAlpha(sceneAlpha, maxDistance, distance);
        return (lineColor & 0x00FFFFFF) | (alpha << 24);
    }
}
