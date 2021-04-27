package ru.itlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.models.Properties;
import ru.itlab.repositories.PropRepository;

import java.util.Optional;

@Service
public class PropService {

    @Autowired
    PropRepository propRepository;

    public boolean saveProp(Properties properties){
        propRepository.save(properties);
        return true;
    }

    public boolean deleteProp(Properties properties){
        propRepository.delete(properties);
        return true;
    }

    public Optional<Properties> propByUser(int userId){
        return propRepository.findById(userId);
    }
}
