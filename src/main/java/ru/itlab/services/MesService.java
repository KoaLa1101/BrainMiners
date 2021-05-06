package ru.itlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.models.Message;
import ru.itlab.repositories.MesRepository;

@Service
public class MesService {
    @Autowired
    MesRepository mesRepository;

    public boolean saveMes(Message message){
        mesRepository.save(message);
        return true;
    }

    public boolean removeMes(Message message){
        mesRepository.delete(message);
        return true;
    }

    public Message findById(int mesId){
        return mesRepository.findById(mesId);
    }


}
