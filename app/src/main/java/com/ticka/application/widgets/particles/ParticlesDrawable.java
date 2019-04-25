package com.ticka.application.widgets.particles;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ticka.application.widgets.particles.contract.SceneConfiguration;
import com.ticka.application.widgets.particles.contract.SceneController;
import com.ticka.application.widgets.particles.contract.SceneRenderer;
import com.ticka.application.widgets.particles.contract.SceneScheduler;
import com.ticka.application.widgets.particles.engine.Engine;
import com.ticka.application.widgets.particles.engine.SceneConfigurator;
import com.ticka.application.widgets.particles.model.Scene;
import com.ticka.application.widgets.particles.renderer.CanvasSceneRenderer;
import com.ticka.application.widgets.particles.renderer.DefaultSceneRenderer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

@Keep
public class ParticlesDrawable extends Drawable implements
        Animatable, SceneConfiguration, SceneController, SceneScheduler {

    private final CanvasSceneRenderer canvasRenderer = new CanvasSceneRenderer();
    private final Scene scene = new Scene();
    private final SceneConfigurator sceneConfigurator = new SceneConfigurator();
    private final SceneRenderer renderer = new DefaultSceneRenderer(canvasRenderer);
    private final Engine engine = new Engine(scene, this, renderer);

    @Override
    public void inflate(
            @NonNull final Resources r,
            @NonNull final XmlPullParser parser,
            @NonNull final AttributeSet attrs,
            @Nullable final Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(r, parser, attrs, theme);
        sceneConfigurator.configureSceneFromAttributes(scene, r, attrs);
    }

    @Override
    public void setBounds(final int left, final int top, final int right, final int bottom) {
        super.setBounds(left, top, right, bottom);
        engine.setDimensions(right - left, bottom - top);
    }

    @Override
    public void draw(@NonNull final Canvas canvas) {
        canvasRenderer.setCanvas(canvas);
        engine.draw();
        canvasRenderer.setCanvas(null);
        engine.run();
    }

    @Override
    public void scheduleNextFrame(final long delay) {
        if (delay == 0L) {
            requestRender();
        } else {
            scheduleSelf(invalidateSelfRunnable, SystemClock.uptimeMillis() + delay);
        }
    }

    @Override
    public void unscheduleNextFrame() {
        unscheduleSelf(invalidateSelfRunnable);
    }

    @Override
    public void requestRender() {
        invalidateSelf();
    }

    @Override
    public void setAlpha(final int alpha) {
        engine.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return engine.getAlpha();
    }

    @Override
    public void setColorFilter(@Nullable final ColorFilter colorFilter) {
        canvasRenderer.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        engine.start();
    }

    @Override
    public void stop() {
        engine.stop();
    }

    @Override
    public boolean isRunning() {
        return engine.isRunning();
    }

    @Override
    public void nextFrame() {
        engine.nextFrame();
    }

    @Override
    public void makeFreshFrame() {
        engine.makeFreshFrame();
    }

    @Override
    public void makeFreshFrameWithParticlesOffscreen() {
        engine.makeFreshFrameWithParticlesOffscreen();
    }

    @Override
    public void setFrameDelay(@IntRange(from = 0) final int delay) {
        scene.setFrameDelay(delay);
    }

    @Override
    public int getFrameDelay() {
        return scene.getFrameDelay();
    }

    @Override
    public void setSpeedFactor(@FloatRange(from = 0) final float speedFactor) {
        scene.setSpeedFactor(speedFactor);
    }

    @Override
    public float getSpeedFactor() {
        return scene.getSpeedFactor();
    }

    @Override
    public void setParticleRadiusRange(
            @FloatRange(from = 0.5f) final float minRadius,
            @FloatRange(from = 0.5f) final float maxRadius) {
        scene.setParticleRadiusRange(minRadius, maxRadius);
    }

    @Override
    public float getParticleRadiusMin() {
        return scene.getParticleRadiusMin();
    }

    @Override
    public float getParticleRadiusMax() {
        return scene.getParticleRadiusMax();
    }

    @Override
    public void setLineThickness(@FloatRange(from = 1) final float lineThickness) {
        scene.setLineThickness(lineThickness);
    }

    @Override
    public float getLineThickness() {
        return scene.getLineThickness();
    }

    @Override
    public void setLineLength(@FloatRange(from = 0) final float lineLength) {
        scene.setLineLength(lineLength);
    }

    @Override
    public float getLineLength() {
        return scene.getLineLength();
    }

    public void setDensity(@IntRange(from = 0) final int newNum) {
        scene.setDensity(newNum);
    }

    @Override
    public int getDensity() {
        return scene.getDensity();
    }

    public void setParticleColor(@ColorInt final int color) {
        scene.setParticleColor(color);
    }

    @Override
    public int getParticleColor() {
        return scene.getParticleColor();
    }

    @Override
    public void setLineColor(@ColorInt final int lineColor) {
        scene.setLineColor(lineColor);
    }

    @Override
    public int getLineColor() {
        return scene.getLineColor();
    }

    private final Runnable invalidateSelfRunnable = new Runnable() {
        @Override
        public void run() {
            invalidateSelf();
        }
    };
}
