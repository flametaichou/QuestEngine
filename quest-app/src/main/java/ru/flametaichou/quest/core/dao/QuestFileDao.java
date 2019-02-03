package ru.flametaichou.quest.core.dao;

import java.util.List;
import java.util.UUID;

import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.domain.Scene;

public interface QuestFileDao {

    void saveOrUpdate(QuestFile questFile);
    QuestFile findById(long id);
    void delete(QuestFile questFile);
    List<QuestFile> listAllQuestFiles();
    List<QuestFile> listQuestFiles(Quest quest);
    QuestFile findByUniqueCode(UUID uniqueCode);

}
