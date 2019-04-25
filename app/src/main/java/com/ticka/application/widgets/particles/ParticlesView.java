package com.ticka.application.widgets.particles;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import com.ticka.application.widgets.particles.contract.SceneConfiguration;
import com.ticka.application.widgets.particles.contract.SceneController;
import com.ticka.application.widgets.particles.contract.SceneRenderer;
import com.ticka.application.widgets.particles.contract.SceneScheduler;
import com.ticka.application.widgets.particles.engine.Engine;
import com.ticka.application.widgets.particles.engine.SceneConfigurator;
import com.ticka.application.widgets.particles.model.Scene;
import com.ticka.application.widgets.particles.renderer.CanvasSceneRenderer;
import com.ticka.application.widgets.particles.renderer.DefaultSceneRenderer;

@Keep
public class ParticlesView extends View implements Animatable, SceneConfiguration, SceneController, SceneScheduler {

    private final CanvasSceneRenderer canvasSceneRenderer = new CanvasSceneRenderer();
    private final Scene scene = new Scene();
    private final SceneConfigurator sceneConfigurator = new SceneConfigurator();
    private final SceneRenderer renderer = new DefaultSceneRenderer(canvasSceneRenderer);
    private final Engine engine = new Engine(scene, this, renderer);

    private boolean mExplicitlyStopped;
    private boolean mAttachedToWindow;
    private boolean mEmulateOnAttachToWindow;

    public ParticlesView (Context context) {
        super(context);
        init(context, null);
    }

    public ParticlesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ParticlesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParticlesView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_HARDWARE, canvasSceneRenderer.getPaint());
        }
        if (attrs != null) {
            sceneConfigurator.configureSceneFromAttributes(scene, context.getResources(), attrs);
        }
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

    public void setParticleRadiusRange(@FloatRange(from = 0.5f) final float minRadius,
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

    public void setLineThickness(@FloatRange(from = 1) final float lineThickness) {
        scene.setLineThickness(lineThickness);
    }

    @Override
    public float getLineThickness() {
        return scene.getLineThickness();
    }

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

    public void setLineColor(@ColorInt final int lineColor) {
        scene.setLineColor(lineColor);
    }

    @Override
    public int getLineColor() {
        return scene.getLineColor();
    }

    @Override
    public void requestRender() {
        invalidate();
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        engine.setDimensions(w, h);
    }

    @Override
    protected void onDraw(@NonNull final Canvas canvas) {
        super.onDraw(canvas);
        canvasSceneRenderer.setCanvas(canvas);
        engine.draw();
        canvasSceneRenderer.setCanvas(null);
        engine.run();
    }

    @Override
    public void scheduleNextFrame(final long delay) {
        if (delay == 0) {
            requestRender();
        } else {
            postInvalidateDelayed(delay);
        }
    }

    @Override
    public void unscheduleNextFrame() {

    }

    @Override
    protected void onVisibilityChanged(@NonNull final View changedView, final int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility != View.VISIBLE) {
            stopInternal();
        } else {
            startInternal();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        startInternal();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttachedToWindow = false;
        stopInternal();
    }

    @Override
    public void start() {
        mExplicitlyStopped = false;
        startInternal();
    }

    @Override
    public void stop() {
        mExplicitlyStopped = true;
        stopInternal();
    }

    @Override
    public boolean isRunning() {
        return engine.isRunning();
    }

    private void startInternal() {
        if (!mExplicitlyStopped && isVisibleWithAllParents(this) && isAttachedToWindowCompat()) {
            engine.start();
        }
    }

    private void stopInternal() {
        engine.stop();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    void setEmulateOnAttachToWindow(final boolean emulateOnAttachToWindow) {
        mEmulateOnAttachToWindow = emulateOnAttachToWindow;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    private boolean isAttachedToWindowCompat() {
        if (mEmulateOnAttachToWindow) {
            return mAttachedToWindow;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return isAttachedToWindow();
        }
        return mAttachedToWindow;
    }

    private boolean isVisibleWithAllParents(@NonNull final View view) {
        if (view.getVisibility() != VISIBLE) {
            return false;
        }

        final ViewParent parent = view.getParent();
        if (parent instanceof View) {
            return isVisibleWithAllParents((View) parent);
        }

        return true;
    }
}
