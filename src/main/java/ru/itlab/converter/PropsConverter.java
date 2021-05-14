package ru.itlab.converter;

import org.springframework.core.convert.converter.Converter;
import ru.itlab.models.Properties;

public class PropsConverter implements Converter<Properties, Properties> {
    @Override
    public Properties convert(Properties filter) {
        Properties props = new Properties();
        if(!filter.getBusyness().equals("_")) props.setBusyness(filter.getBusyness());
        if(!filter.getEducation().equals("_")) props.setEducation(filter.getEducation());
        if(!filter.getExperience().equals("_")) props.setExperience(filter.getExperience());
        if(!filter.getLevelOfEnglish().equals("_")) props.setLevelOfEnglish(filter.getLevelOfEnglish());
        if(!filter.getSalaryWork().equals("_")) props.setSalaryWork(filter.getSalaryWork());
        if(!filter.getSphereOfWork().equals("_")) props.setSphereOfWork(filter.getSphereOfWork());
        return props;
    }
}
