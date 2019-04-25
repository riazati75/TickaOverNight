package com.ticka.application.widgets.particles.engine;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.ticka.application.R;
import com.ticka.application.widgets.particles.Defaults;
import com.ticka.application.widgets.particles.model.Scene;

@Keep
public final class SceneConfigurator {

    public void configureSceneFromAttributes(
            @NonNull final Scene scene,
            @NonNull final Resources r,
            @NonNull final AttributeSet attrs) {
        final TypedArray a = r.obtainAttributes(attrs, R.styleable.ParticlesView);
        try {
            final int count = a.getIndexCount();
            float particleRadiusMax = Defaults.PARTICLE_RADIUS_MAX;
            float particleRadiusMin = Defaults.PARTICLE_RADIUS_MIN;
            for (int i = 0; i < count; i++) {
                final int attr = a.getIndex(i);
                if (attr == R.styleable.ParticlesView_density) {
                    scene.setDensity(a.getInteger(attr, Defaults.DENSITY));

                } else if (attr == R.styleable.ParticlesView_frameDelayMillis) {
                    scene.setFrameDelay(a.getInteger(attr, Defaults.FRAME_DELAY));

                } else if (attr == R.styleable.ParticlesView_lineColor) {
                    scene.setLineColor(a.getColor(attr, Defaults.LINE_COLOR));

                } else if (attr == R.styleable.ParticlesView_lineLength) {
                    scene.setLineLength(a.getDimension(attr, Defaults.LINE_LENGTH));

                } else if (attr == R.styleable.ParticlesView_lineThickness) {
                    scene.setLineThickness(a.getDimension(attr, Defaults.LINE_THICKNESS));

                } else if (attr == R.styleable.ParticlesView_particleColor) {
                    scene.setParticleColor(a.getColor(attr, Defaults.PARTICLE_COLOR));

                } else if (attr == R.styleable.ParticlesView_particleRadiusMax) {
                    particleRadiusMax = a.getDimension(attr, Defaults.PARTICLE_RADIUS_MAX);

                } else if (attr == R.styleable.ParticlesView_particleRadiusMin) {
                    particleRadiusMin = a.getDimension(attr, Defaults.PARTICLE_RADIUS_MIN);

                } else if (attr == R.styleable.ParticlesView_speedFactor) {
                    scene.setSpeedFactor(a.getFloat(attr, Defaults.SPEED_FACTOR));
                }
            }
            scene.setParticleRadiusRange(particleRadiusMin, particleRadiusMax);
        } finally {
            a.recycle();
        }
    }
}
