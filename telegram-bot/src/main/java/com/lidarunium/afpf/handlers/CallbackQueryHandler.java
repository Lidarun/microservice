package com.lidarunium.afpf.handlers;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.enums.ButtonType;
import com.lidarunium.afpf.enums.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Objects;

import static com.lidarunium.afpf.enums.Command.SALARY;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {
    private final BotCommandHandler commandHandler;
    private final BotStateCache botStateCache;

    public SendMessage replyMessage(CallbackQuery callbackQuery) {
        ButtonType buttonType = ButtonType.valueOf(callbackQuery.getData());
        long chatID = callbackQuery.getMessage().getChatId();
        Command command = botStateCache.getBotState(chatID);

        if (Objects.isNull(command))
            command = switch (buttonType) {
                case INCOME -> Command.INCOME;
                case EXPENSE -> Command.EXPENSE;
                case CANCEL -> Command.CANCEL;
                case SALARY ,EDIT -> SALARY;
                case SAVE -> Command.INCOME_SAVER;
                default -> null;
            };
        else {
            if (Objects.equals(command, SALARY))
                command = switch (buttonType) {
                    case CANCEL -> Command.CANCEL;
                    case BACK -> Command.BACK;
                    default -> null;
                };
        }

        return commandHandler.getMessageByBotState(command, callbackQuery.getMessage());
    }
}
