package it.marcodemartino.nsfwbot;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.LongPollingBot;

import java.io.IOException;

public class NSFWBot extends LongPollingBot {

    public NSFWBot(Bot bot) {
        super(bot);
        events.registerUpdateHandler(new MessageReceived(bot));
        events.registerUpdateHandler(new UpdatePrinter());
    }

    public static void main(String[] args) throws IOException {
        Bot bot = Bot.fromToken("****:****");
        NSFWBot nsfwBot = new NSFWBot(bot);
        nsfwBot.run();
    }
}
