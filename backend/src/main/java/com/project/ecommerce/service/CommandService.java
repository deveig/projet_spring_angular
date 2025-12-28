package com.project.ecommerce.service;

import com.project.ecommerce.dto.CommandDTO;
import com.project.ecommerce.entity.Command;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.mapper.CommandMapper;
import com.project.ecommerce.repository.CommandRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommandMapper commandMapper;

    @Transactional(readOnly = true)
    public List<CommandDTO> getAll(){
        return commandRepository.findAll().stream().map((u) -> commandMapper.toDto(u)).toList();
    }

    @Transactional(readOnly = true)
    public CommandDTO getById(Long id){
        return commandMapper.toDto(commandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Command", "id", id)));
    }

    public CommandDTO saveCommand(CommandDTO commandDTO){
        return commandMapper.toDto(commandRepository.save(commandMapper.toEntity(commandDTO)));
    }

    public CommandDTO update(Long id, CommandDTO commandDTO){
        Command command = commandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Command", "id", id));
        return commandMapper.toDto(commandRepository.save(commandMapper.updateEntity(commandDTO, command)));
    }

    public void delete(Long id){
        Command command = commandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Command", "id", id));
        commandRepository.delete(command);
    }

    @Transactional(readOnly = true)
    public List<CommandDTO> getByUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return commandRepository.findByUser(user).stream().map((c) -> commandMapper.toDto(c)).toList();
    }
}
