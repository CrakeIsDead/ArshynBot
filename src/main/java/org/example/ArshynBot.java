package org.example;

import lombok.SneakyThrows;
import org.example.models.ItemElement;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;
import static org.example.resources.Constants.*;

//А18037123 - рабочий, 1 позиция
//642849 - рабочий, 8 позиций

public class ArshynBot extends TelegramLongPollingBot {

    int itemListSize;

    @Override
    @SneakyThrows
    public void onUpdateReceived (Update update) {

        //Greetings on /start command.
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            long message_id = update.getMessage().getMessageId();
            String message_user = update.getMessage().getFrom().getUserName();
            System.out.println("Message ID: " + message_id);
            System.out.println("User: " + message_user);
            if (update.getMessage().getText().equals("/start")) {

                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                message.setText(GREETING_TEXT);

                //Keyboard
                InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                InlineKeyboardButton searchButton = new InlineKeyboardButton();
                searchButton.setText(SEARCH_BUTTON_TEXT);
                searchButton.setCallbackData(SEARCH_BUTTON_DATA);
                rowInline.add(searchButton);

                rowsInline.add(rowInline);
                markupInLine.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInLine);

                execute(message);

            }else {

                // Searching and result delivery

                SendMessage message = new SendMessage();
                Transcript transcript = new FgisApiService()
                        .vriGetRequest(update.getMessage()
                                               .getText());
                itemListSize = transcript.getResult().getItems().size();
                Thread.sleep(1000); // To avoid HTTP Error 429

                message.setChatId(update.getMessage().getChatId().toString());
                if (itemListSize == 1) {

                    ItemElement itemElement = transcript.getResult().getItems().get(0);

                    message.enableHtml(true);
                    message.setText(itemElement.getInfo());
                    execute(message);

                } else if (itemListSize > 1) {

                    for (int i = 0; i < itemListSize; i++) {

                        ItemElement itemElement = transcript.getResult().getItems().get(i);

                        message.enableHtml(true);
                        message.setText(itemElement.getInfo());
                        execute(message);

                    }

                } else{

                    //Failed search message response
                    message.setText(NO_SEARCH_RESULT);
                    execute(message);
                    
                }

            }

            //Search button handling, editing start message
        }else if (update.hasCallbackQuery()){
                String call_data = update.getCallbackQuery().getData();
                long message_id = update.getCallbackQuery().getMessage().getMessageId();
                long chat_id = update.getCallbackQuery().getMessage().getChatId();
            
            switch (call_data) {
                case SEARCH_BUTTON_DATA: {
                    EditMessageText new_message = new EditMessageText();
                    new_message.setChatId(chat_id);
                    new_message.setMessageId(toIntExact(message_id));
                    new_message.setText(SEARCH_TEXT_HINT);
                    execute(new_message);
                    break;
                }

                }
            }
        }

    @Override
    public String getBotUsername () {
        return System.getenv("BOT_USERNAME");
    }

    @Override
    public String getBotToken () {
        return System.getenv("BOT_TOKEN");
    }

}
