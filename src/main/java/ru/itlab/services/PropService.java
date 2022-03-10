package ru.itlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.models.Properties;
import ru.itlab.repositories.PropRepository;

import java.util.List;
import java.util.Optional;

@Service
public class    PropService {

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

    public List<Properties> allBy(Properties properties){
        List<Properties> allProps = propRepository.findAll();
        for (Properties p: allProps) {
            if (!p.equals(properties)) allProps.remove(p);
        }
        return allProps;
    }
}
