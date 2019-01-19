package ru.flametaichou.quest.utils;

import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.core.dto.EnumeratedDto;

import java.util.Objects;

public class EnumUtils {

    public static EnumeratedDto getDtoFromEnum(Enum rawEnum) {

        if (Objects.nonNull(rawEnum)) {
            String caption = null;
            if (rawEnum instanceof Scene.SceneType) {
                switch ((Scene.SceneType) rawEnum) {
                    case DIALOG:
                        caption = "Диалог";
                        break;
                    case PICTURE:
                        caption = "Картина";
                        break;
                    case END:
                        caption = "Конец";
                        break;
                }
            } else if (rawEnum instanceof QuestFile.QuestFileType) {
                switch ((QuestFile.QuestFileType) rawEnum) {
                    case PICTURE:
                        caption = "Изображение";
                        break;
                }
            }

            return new EnumeratedDto(rawEnum.name(), Objects.isNull(caption) ? rawEnum.name() : caption);
        } else {
            return null;
        }
    }
}
