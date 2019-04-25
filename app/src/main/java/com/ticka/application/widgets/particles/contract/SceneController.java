package com.ticka.application.widgets.particles.contract;

import android.support.annotation.Keep;

@Keep
public interface SceneController {

    void nextFrame();

    void makeFreshFrame();

    void makeFreshFrameWithParticlesOffscreen();

}
