package ru.itlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.models.Templates;
import ru.itlab.models.User;
import ru.itlab.repositories.TemplatesRepository;

import java.util.List;

@Service
public class TempService {
    @Autowired
    TemplatesRepository templatesRepository;

    public boolean saveTemp(Templates templates){
        templatesRepository.save(templates);
        return true;
    }

    public List<Templates> findAll(){
        return templatesRepository.findAll();
    }

    public List<Templates> findAllByUserList(User user) {return templatesRepository.findAllByUserList(user);}

}
