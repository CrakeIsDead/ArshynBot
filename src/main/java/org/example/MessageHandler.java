package org.example;

import org.example.models.ItemElement;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.example.resources.Constants.GREETING_TEXT;
import static org.example.resources.Constants.NO_SEARCH_RESULT;

public class MessageHandler {

    private Update update;
    private long chat_id;
    private int itemListSize;
    private int currentPage;

    public int getCurrentPage () {
        return currentPage;
    }

    public void setCurrentPage (int currentPage) {
        this.currentPage = currentPage;
    }

    public MessageHandler (Update update) {

        this.update = update;

    }

    public SendMessage processCommand(String command) {

        SendMessage message = new SendMessage();
        chat_id = update.getMessage().getChatId();

        if (command.equals("/start")) {
            message.setChatId(chat_id);
            message.setText(GREETING_TEXT);
        }

        return message;

    }

    public SendMessage processResponse() {

        SendMessage message = new SendMessage();
        chat_id = update.getMessage().getChatId();

        Transcript transcript = new FgisApiService().vriGetRequest(update.getMessage().getText());
        itemListSize = transcript.getResult().getItems().size();

        if (itemListSize == 0) {
            message.setText(NO_SEARCH_RESULT);
        } else if (itemListSize == 1) {
            ItemElement itemElement = transcript.getResult().getItems().get(0);
            message.enableHtml(true);
            message.setText(itemElement.getInfo());
        } else if (itemListSize > 1) {
            ItemElement itemElement = transcript.getResult().getItems().get(currentPage);
            message.enableHtml(true);
            message.setText(itemElement.getInfo());
        }


        return message;

    }

    public void responseNextPage() {

    }

    public void responsePreviousPage() {

    }

}
