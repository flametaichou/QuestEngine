package ru.flametaichou.quest.core.services;

import java.util.List;
import ru.flametaichou.quest.core.dto.QuestFileDto;

public interface QuestFileService {

    List<QuestFileDto> listQuestFileDtos();
    void createQuestFile(QuestFileDto dto);
    void deleteQuestFile(Long id);
}
