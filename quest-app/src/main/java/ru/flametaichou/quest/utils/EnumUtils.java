package ru.flametaichou.quest.utils;

import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.core.dto.EnumEntityDto;

import java.util.Objects;

public class EnumUtils {

    public static EnumEntityDto getDtoFromEnum(Enum rawEnum) {

        if (Objects.nonNull(rawEnum)) {
            String displayName = null;
            if (rawEnum instanceof Scene.SceneType) {
                switch ((Scene.SceneType) rawEnum) {
                    case DIALOG:
                        displayName = "Диалог";
                        break;
                    case PICTURE:
                        displayName = "Картина";
                        break;
                    case END:
                        displayName = "Конец";
                        break;
                }
            } else if (rawEnum instanceof QuestFile.QuestFileType) {
                switch ((QuestFile.QuestFileType) rawEnum) {
                    case PICTURE:
                        displayName = "Изображение";
                        break;
                }
            }

            return new EnumEntityDto(rawEnum.name(), Objects.isNull(displayName) ? rawEnum.name() : displayName);
        } else {
            return null;
        }
    }
}
