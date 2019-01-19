package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.flametaichou.quest.core.dao.QuestFileDao;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.dto.QuestFileDto;

public class QuestFileServiceImpl implements QuestFileService {

    private final Logger logger = LoggerFactory.getLogger(QuestFileServiceImpl.class);

    @Autowired
    private QuestFileDao questFileDao;

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Override
    @Transactional(readOnly = true)
    public List<QuestFileDto> listQuestFileDtos() {
        List<QuestFile> questFiles = questFileDao.listQuestFiles();
        List<QuestFileDto> dtos = new ArrayList<QuestFileDto>();
        for (QuestFile questFile : questFiles) {
            QuestFileDto dto = new QuestFileDto();
            dto.setId(questFile.getId());
            dto.setName(questFile.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    @Transactional
    public void createQuestFile(QuestFileDto dto) {
        QuestFile questFile = new QuestFile();
        questFile.setName(dto.getName());
        questFileDao.saveOrUpdate(questFile);
    }

    @Override
    @Transactional
    public void deleteQuestFile(Long id) {
        QuestFile questFile = questFileDao.findById(id);
        if (Objects.isNull(questFile)) {
            logger.error("Could not find questFile id:{}!", id);
            return;
        }
        questFileDao.delete(questFile);
    }
}
