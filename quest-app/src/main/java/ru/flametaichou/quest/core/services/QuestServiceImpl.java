package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.flametaichou.quest.core.dao.QuestDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.dto.QuestDto;

/**
 * @date 24.08.18
 */
public class QuestServiceImpl implements QuestService {
    
    private final Logger logger = LoggerFactory.getLogger(QuestServiceImpl.class);
    
    @Autowired
    private QuestDao questDao;

    @Autowired
    private AccountService accountService;

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Override
    @Transactional(readOnly = true)
    public Quest findQuestById(Long id) {
        return questDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestDto findQuestDtoByUniqueCode(Account account, String uniqueCode) {
        Quest quest = questDao.findByUniqueCode(uniqueCode);
        if (Objects.nonNull(quest)) {
            if (accountService.hasPermissionsTo(account, quest)) {
                QuestDto questDto = mapper.map(quest, QuestDto.class);
                return questDto;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestDto> listQuestDtos(Account account) {
        logger.info("ACCOUNT: {}", account);
        Account currentAccount = accountService.getCurrentUser();

        List<Quest> rawQuests = questDao.listQuests();
        List<Quest> quests = new ArrayList<Quest>();

        // Права. Выбираем только тех квесты аккаунта если это не админ
        if (accountService.isSuperuser(currentAccount)) {
            quests = rawQuests;
        } else {
            for (Quest q : rawQuests) {
                if (accountService.hasPermissionsTo(account, q)) {
                    quests.add(q);
                }
            }
        }

        List<QuestDto> dtos = new ArrayList<QuestDto>();
        for (Quest quest : quests) {
            QuestDto dto = mapper.map(quest, QuestDto.class);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional
    public void createQuest(Account account, QuestDto dto) {
        Quest quest = new Quest();
        quest.setName(dto.getName());
        quest.setAccount(account);
        quest.setDescription(dto.getDescription());
        questDao.saveOrUpdate(quest);
    }

    @Override
    @Transactional
    public void deleteQuest(Account account, Long id) {
        Quest quest = questDao.findById(id);
        if (Objects.isNull(quest)) {
            logger.error("Could not find quest id:{}!", id);
            return;
        }
        if (accountService.hasPermissionsTo(account, quest)) {
            questDao.delete(quest);
        }
    }

    @Override
    @Transactional
    public void updateQuest(Account account, QuestDto dto) {
        Quest quest = questDao.findById(dto.getId());
        if (Objects.isNull(quest)) {
            logger.error("Could not find quest id:{}!", dto.getId());
            return;
        }
        if (accountService.hasPermissionsTo(account, quest)) {
            quest.setName(dto.getName());
            quest.setAccount(account);
            quest.setDescription(dto.getDescription());
            questDao.saveOrUpdate(quest);
        }
    }

    @Override
    public void updateQuest(Account account, Quest quest) {
        questDao.saveOrUpdate(quest);
    }
}
