package com.example.backgroundlib;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.backgroundlib.modelsBanco.GameData;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("MyWorker", "Executando o COISO");
        NotificacaoService.gerarNotificacao(PluginInstance.unityActivity);
        tickDiminuiStatus();
        agendaProximo();
        return Result.success();
    }

    private void agendaProximo() {
        OneTimeWorkRequest nextWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(2, TimeUnit.MINUTES)
                .build();

        WorkManager.getInstance(getApplicationContext()).enqueue(nextWorkRequest);
    }

    private void tickDiminuiStatus() {
        File file = new File(PluginInstance.unityActivity.getExternalFilesDir(null), "data.json");
        GameData gameData = ArquivoService.buscarArquivoSaves(file);
        if (Objects.nonNull(gameData)) {
            Log.e("GameData", "Level: " + gameData.level);
            Log.e("GameData", "Barra Fome: " + gameData.barraFome);

            // adicionando valor e salvando
            gameData.barraFome -= 20;
            gameData.barraBanheiro -= 20;
            gameData.barraSede -= 20;
            gameData.barraSono -= 20;
            ArquivoService.salvarArquivo(gameData, file);
        }
    }
}

