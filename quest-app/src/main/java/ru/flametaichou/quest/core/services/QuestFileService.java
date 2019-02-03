package ru.flametaichou.quest.core.services;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.dto.ImageDto;
import ru.flametaichou.quest.core.dto.QuestFileDto;

public interface QuestFileService {

    List<QuestFileDto> listAllQuestFileDtos();
    List<QuestFileDto> listQuestFileDtos(Account account, Quest quest);
    List<QuestFileDto> listQuestFileDtos(Account account, Long questId);
    void createQuestFile(QuestFileDto dto);
    void deleteQuestFile(Long id);
    void saveQuestFile(Account account, MultipartFile file, Long questId);
    ImageDto getImage(UUID questFileCode);
    QuestFile findByCode(UUID questFileCode);
    QuestFile findById(Long id);
}
