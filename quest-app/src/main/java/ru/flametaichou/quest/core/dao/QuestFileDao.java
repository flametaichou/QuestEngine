package ru.flametaichou.quest.core.dao;

import java.util.List;
import ru.flametaichou.quest.core.domain.QuestFile;

public interface QuestFileDao {

    void saveOrUpdate(QuestFile questFile);
    QuestFile findById(long id);
    void delete(QuestFile questFile);
    List<QuestFile> listQuestFiles();

}
