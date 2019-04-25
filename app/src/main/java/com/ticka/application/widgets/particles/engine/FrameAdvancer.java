package com.ticka.application.widgets.particles.engine;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.ticka.application.widgets.particles.model.Scene;

final class FrameAdvancer {

    @NonNull
    private final ParticleGenerator particleGenerator;

    FrameAdvancer(@NonNull final ParticleGenerator particleGenerator) {
        this.particleGenerator = particleGenerator;
    }

    void advanceToNextFrame(
            @NonNull final Scene scene,
            final float step
    ) {
        final int particlesCount = scene.getDensity();
        for (int i = 0; i < particlesCount; i++) {
            float x = scene.getParticleX(i);
            float y = scene.getParticleY(i);

            final float speedFactor = scene.getParticleSpeedFactor(i);
            final float dCos = scene.getParticleDirectionCos(i);
            final float dSin = scene.getParticleDirectionSin(i);

            x += step * scene.getSpeedFactor() * speedFactor * dCos;
            y += step * scene.getSpeedFactor() * speedFactor * dSin;

            if (particleOutOfBounds(scene, x, y)) {
                particleGenerator.applyFreshParticleOffScreen(scene, i);
            } else {
                scene.setParticleX(i, x);
                scene.setParticleY(i, y);
            }
        }
    }

    /**
     * Used for checking if the particle is off-screen and farther than line length and it's
     * radius.
     *
     * @param x the particle x
     * @param y the particle y
     * @return true if the particle is off-screen and guaranteed not to be used to draw a line to
     * the closest particle on screen.
     */
    @VisibleForTesting
    boolean particleOutOfBounds(
            @NonNull final Scene scene,
            final float x,
            final float y) {
        final float offset = scene.getParticleRadiusMin() + scene.getLineLength();
        return x + offset < 0 || x - offset > scene.getWidth()
                || y + offset < 0 || y - offset > scene.getHeight();
    }
}
