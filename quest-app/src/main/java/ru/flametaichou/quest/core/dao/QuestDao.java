package ru.flametaichou.quest.core.dao;

import java.util.List;
import ru.flametaichou.quest.core.domain.Quest;

public interface QuestDao {

    void saveOrUpdate(Quest quest);
    Quest findById(long id);
    Quest findByUniqueCode(String uniqueCode);
    void delete(Quest quest);
    List<Quest> listQuests();
}
