package ru.flametaichou.quest.core.services;

import java.util.List;

import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.dto.QuestDto;

public interface QuestService {

    Quest findQuestById(Long id);
    QuestDto findQuestDtoByUniqueCode(Account account, String uniqueCode);
    List<QuestDto> listQuestDtos(Account account);
    void createQuest(Account account, QuestDto dto);
    void deleteQuest(Account account, Long id);
    void updateQuest(Account account, QuestDto dto);
    void updateQuest(Account account, Quest quest);

}
