package com.example.backgroundlib;

import android.util.Log;

import com.example.backgroundlib.modelsBanco.GameData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ArquivoService {

    public static GameData buscarArquivoSaves(File arquivo) {
        if (arquivo.exists()) {
            Log.e("mexendoEmArquivos", "Arquivo existe!");
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                StringBuilder encodedData = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    encodedData.append(line);
                }

                // Decodifica os dados
                byte[] jsonBytes = Base64.getDecoder().decode(encodedData.toString());
                String jsonString = new String(jsonBytes, StandardCharsets.UTF_8);

                // Desserializa o JSON para o objeto GameData
                return new Gson().fromJson(jsonString, GameData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void salvarArquivo(GameData gameData, File arquivo) {
        Gson gson = new Gson();
        String json = gson.toJson(gameData);
        byte[] encodedBytes = Base64.getEncoder().encode(json.getBytes());

        try (FileOutputStream fos = new FileOutputStream(arquivo)) {
            fos.write(encodedBytes);
            Log.e("mexendoEmArquivos", "Dados salvos com sucesso!");
        } catch (IOException e) {
            Log.e("mexendoEmArquivos", "Erro ao salvar os dados: " + e.getMessage());
        }
    }

}
