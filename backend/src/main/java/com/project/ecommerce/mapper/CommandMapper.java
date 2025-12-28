package com.project.ecommerce.mapper;

import com.project.ecommerce.dto.CommandDTO;
import com.project.ecommerce.entity.Command;
import org.springframework.stereotype.Component;

@Component
public class CommandMapper {

    public Command toEntity(CommandDTO dto){
        if(dto == null){
            return null;
        }
        Command command = new Command();
        command.setDate(dto.getDate());
        command.setPaymentMode(dto.getPaymentMode());
        command.setUser(dto.getUser());
        return command;
    }

    public CommandDTO toDto(Command command){
        if(command == null){
            return null;
        }
        CommandDTO dto = new CommandDTO();
        dto.setId(command.getId());
        dto.setDate(command.getDate());
        dto.setPaymentMode(command.getPaymentMode());
        dto.setUser(command.getUser());
        return dto;
    }

    public Command updateEntity(CommandDTO dto, Command command){
        if(dto == null || command == null){
            return null;
        }
        command.setDate(dto.getDate());
        command.setPaymentMode(dto.getPaymentMode());
        command.setUser(dto.getUser());
        return command;
    }
}
