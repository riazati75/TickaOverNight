package com.ticka.application.widgets.particles.util;

import com.ticka.application.widgets.particles.KeepAsApi;

@KeepAsApi
public final class DistanceResolver {

    public static float distance(final float ax, final float ay,
                                 final float bx, final float by) {
        return (float) Math.sqrt(
                (ax - bx) * (ax - bx) +
                        (ay - by) * (ay - by)
        );
    }
}
