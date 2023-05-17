import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class PleaseJustRemindMeBot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "6192371010:AAE4byJn5Fh8WC4i3y83f2Cr6e4iq3m4L5M";
    private static final long CHAT_ID = 721273601;
    private static final LocalTime SEND_TIME = LocalTime.of(9, 0); // Specify the time to send the message (24-hour format)

    public static void main(String[] args) {
        DailyMessageBot bot = new DailyMessageBot();
        bot.startBot();
    }

    private void startBot() {
        Timer timer = new Timer();
        timer.schedule(new DailyMessageTask(), getDelay(), 24 * 60 * 60 * 1000); // Schedule the task to run every 24 hours

        // Start the bot
        DailyMessageBot bot = new DailyMessageBot();
        bot.start();
    }

    private long getDelay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextSendTime = LocalDateTime.of(now.toLocalDate().plusDays(1), SEND_TIME);
        return java.time.Duration.between(now, nextSendTime).toMillis();
    }

    private class DailyMessageTask extends TimerTask {
        @Override
        public void run() {
            SendMessage message = new SendMessage()
                    .setChatId(CHAT_ID)
                    .setText("Don't forget to write your code today!");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Handle incoming updates
    }

    @Override
    public String getBotUsername() {
        return "YourBotUsername";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
