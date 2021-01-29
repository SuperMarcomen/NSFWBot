package it.marcodemartino.nsfwbot;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.inline.InlineKeyboardButton;
import io.github.ageofwar.telejam.inline.UrlInlineKeyboardButton;
import io.github.ageofwar.telejam.messages.Message;
import io.github.ageofwar.telejam.messages.MessageHandler;
import io.github.ageofwar.telejam.messages.TextMessage;
import io.github.ageofwar.telejam.methods.DeleteMessage;
import io.github.ageofwar.telejam.methods.ForwardMessage;
import io.github.ageofwar.telejam.methods.SendSticker;
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup;

public class MessageReceived implements MessageHandler {

    private final Bot bot;
    private boolean waitingForMessage;

    public MessageReceived(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) throws Throwable {
        System.out.println("Messaggio qualunque ricevuto");
        if (waitingForMessage) {
            System.out.println("Stavo aspettando un messaggio");
            waitingForMessage = false;

            ForwardMessage forwardMessage = new ForwardMessage()
                    .message(message)
                    .chat(-****);
            long messageId = bot.execute(forwardMessage).getId();
            System.out.println("Ho inoltrato il messaggio");

            DeleteMessage deleteMessage = new DeleteMessage()
                    .message(message);
            bot.execute(deleteMessage);
            System.out.println("Ho cancellato il messaggio");

            InlineKeyboardButton[] buttons = {
                    new UrlInlineKeyboardButton("Apri a tuo rischio e pericolo \uD83C\uDF1A", "https://t.me/c/****/" + messageId),
            };
            System.out.println("Ho creato la tastiera");

            SendSticker sendSticker = new SendSticker()
                    .sticker("CAACAgQAAxkBAAMCYASp14aABjkIEGEJ_oow67dTH1sAAl4CAALNnDMAAWYa9VrPf3K_HgQ")
                    .replyMarkup(new InlineKeyboardMarkup(buttons))
                    .chat(message.getChat());
            bot.execute(sendSticker);
            System.out.println("Ho inviato lo sticker, passo e chiudo");
            return;
        }

        if (!(message instanceof TextMessage)) return;
        System.out.println("Il messaggio è un testo");

        TextMessage textMessage = (TextMessage) message;
        if (textMessage.getText().toString().equalsIgnoreCase("nsfw")) {
            System.out.println("Il messaggio è \"nsfw\"");
            waitingForMessage = true;
            DeleteMessage deleteMessage = new DeleteMessage()
                    .message(message);
            bot.execute(deleteMessage);
            System.out.println("Cancello il messaggio");
        }
    }

}