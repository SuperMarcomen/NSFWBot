package it.marcodemartino.nsfwbot;

import io.github.ageofwar.telejam.json.Json;
import io.github.ageofwar.telejam.updates.Update;
import io.github.ageofwar.telejam.updates.UpdateHandler;

public class UpdatePrinter implements UpdateHandler {

    @Override
    public void onUpdate(Update update) {
        System.out.println(Json.toJson(update));
    }
}
