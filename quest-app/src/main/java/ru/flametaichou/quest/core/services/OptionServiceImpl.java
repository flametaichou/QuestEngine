package ru.flametaichou.quest.core.services;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.flametaichou.quest.core.dao.OptionDao;
import ru.flametaichou.quest.core.domain.Option;
import ru.flametaichou.quest.core.dto.OptionDto;

public class OptionServiceImpl implements OptionService {

    private final Logger logger = LoggerFactory.getLogger(OptionServiceImpl.class);

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Autowired
    private OptionDao optionDao;

    @Override
    public void saveOrUpdate(Option option) {
        optionDao.saveOrUpdate(option);
    }

    @Override
    public void delete(Option option) {
        optionDao.delete(option);
    }

    @Override
    public void create(OptionDto optionDto) {
        Option option = new Option();
        option.setName(optionDto.getName());
        optionDao.saveOrUpdate(option);
    }
}
