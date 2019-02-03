package ru.flametaichou.quest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class QuestFileUtils {

    private static Logger logger = LoggerFactory.getLogger(QuestFileUtils.class);

    private static String rootDirectoryName = "quest-files";
    private static String DIVIDER = "/";

    // Создает директорию если ее нет
    public static void createDirIfNotExist(final File dir) {
        if (!dir.exists()) {
            logger.info("Creating directory: " + dir.getName());
            boolean result = false;

            try {
                dir.mkdir();
                result = true;
            } catch (SecurityException se) {
                logger.error("Directory not created: ", se);
            }
            if (result) {
                logger.info("Directory created");
            }
        }
    }

    public static String getFilesDirectoryPath() throws IOException {
        return new File(".").getCanonicalPath() + DIVIDER + rootDirectoryName;
    }
}
