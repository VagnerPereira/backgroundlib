package com.example.backgroundlib;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class PluginInstance extends Application {

    protected static Activity unityActivity;

    public static void receivedUnityActivity(Activity tActivity) {
        unityActivity = tActivity;
        NotificacaoService.criarCanalNotificacao(unityActivity);
    }

    public void Toast(String msg) {
        if (unityActivity == null) {
            Log.e("PluginInstance", "UnityActivity is null. Cannot show Toast.");
            return;
        }
        NotificacaoService.gerarNotificacao(unityActivity);
    }



    public void StartBgService() {
        Log.e("PluginInstance", "Iniciando task de background");
        if (unityActivity == null) {
            Log.e("PluginInstance", "UnityActivity is null. Cannot start JobService.");
            return;
        }

        // Em uma Activity ou Service
        PeriodicWorkRequest myWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES).build();
        WorkManager.getInstance(this).enqueue(myWorkRequest);
    }
}
