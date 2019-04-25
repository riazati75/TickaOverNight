package com.ticka.application.widgets.particles.contract;

import android.support.annotation.NonNull;

import com.ticka.application.widgets.particles.KeepAsApi;
import com.ticka.application.widgets.particles.model.Scene;

@KeepAsApi
public interface SceneRenderer {

    void drawScene(@NonNull Scene scene);
}
