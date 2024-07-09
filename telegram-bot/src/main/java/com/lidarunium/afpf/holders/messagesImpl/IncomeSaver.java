package com.lidarunium.afpf.holders.messagesImpl;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.enums.IncomeType;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.models.IncomeDTO;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class IncomeSaver implements MessageHolder {
    private final MessageGenerator messageGenerator;
    private final BotStateCache botStateCache;

    @Override
    public Command getCommand() {
        return Command.INCOME_SAVER;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();
        String userMsg = message.getText();

        String msg = String.format("Your salary with size %s was saved", userMsg);
        IncomeDTO incomeDTO = IncomeDTO.builder()
                .userId(message.getChatId().toString())
                .incomeType(IncomeType.SALARY)
                .count(BigDecimal.valueOf(Double.parseDouble(userMsg)))
                .build();

        //TODO send by Rabbit to save

        return messageGenerator.generateMessage(chatID, msg);
    }
}
