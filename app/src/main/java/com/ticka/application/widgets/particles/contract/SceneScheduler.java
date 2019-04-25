package com.ticka.application.widgets.particles.contract;

import android.support.annotation.Keep;

@Keep
public interface SceneScheduler {

    void scheduleNextFrame(long delay);

    void unscheduleNextFrame();

    void requestRender();
}
