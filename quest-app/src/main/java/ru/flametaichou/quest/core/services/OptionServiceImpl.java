package ru.flametaichou.quest.core.services;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.flametaichou.quest.core.dao.OptionDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Option;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.core.dto.OptionDto;
import ru.flametaichou.quest.core.dto.SceneDto;

import java.util.Objects;

public class OptionServiceImpl implements OptionService {

    private final Logger logger = LoggerFactory.getLogger(OptionServiceImpl.class);

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private QuestService questService;

    @Autowired
    private AccountService accountService;

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

    @Override
    @Transactional
    public void createOption(Account account, OptionDto dto) {
        Option option = new Option();
        updateOption(account, option, dto);
    }

    @Override
    @Transactional
    public void updateOption(Account account, OptionDto dto) {
        Option option = optionDao.findById(dto.getId());
        if (Objects.isNull(option)) {
            logger.error("Could not find option id:{}!", dto.getId());
            return;
        }
        updateOption(account, option, dto);
    }

    private void updateOption(Account account, Option option, OptionDto dto) {
        Scene scene = sceneService.findById(dto.getScene().getId());
        if (accountService.hasPermissionsTo(account, scene.getQuest())) {
            option.setName(dto.getName());
            option.setScene(scene);
            if (Objects.nonNull(dto.getTargetScene())) {
                Scene targetScene = sceneService.findById(dto.getTargetScene().getId());
                option.setTargetScene(targetScene);
            }
            optionDao.saveOrUpdate(option);
        }
    }
}
