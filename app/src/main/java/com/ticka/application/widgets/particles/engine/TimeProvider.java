package com.ticka.application.widgets.particles.engine;

import android.os.SystemClock;

final class TimeProvider {

    public long uptimeMillis() {
        return SystemClock.uptimeMillis();
    }
}
