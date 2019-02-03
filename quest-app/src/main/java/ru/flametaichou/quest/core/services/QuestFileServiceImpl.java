package ru.flametaichou.quest.core.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tiles.web.util.ServletContextAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.flametaichou.quest.core.dao.QuestFileDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.dto.ImageDto;
import ru.flametaichou.quest.core.dto.QuestFileDto;
import ru.flametaichou.quest.utils.QuestFileUtils;
import ru.flametaichou.quest.utils.QuestImageUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

public class QuestFileServiceImpl implements QuestFileService {

    private final Logger logger = LoggerFactory.getLogger(QuestFileServiceImpl.class);

    private static String DIVIDER = "/";
    private static String FORMAT = "jpeg";
    private static Long maxImageSize = 5000000L; //5mb

    @Autowired
    private QuestFileDao questFileDao;

    @Autowired
    private QuestService questService;

    @Autowired
    private AccountService accountService;

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Override
    @Transactional(readOnly = true)
    public List<QuestFileDto> listAllQuestFileDtos() {
        List<QuestFile> questFiles = questFileDao.listAllQuestFiles();
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
    @Transactional(readOnly = true)
    public List<QuestFileDto> listQuestFileDtos(Account account, Quest quest) {

        List<QuestFile> questFiles = questFileDao.listQuestFiles(quest);
        List<QuestFileDto> dtos = new ArrayList<QuestFileDto>();

        // Права. Выбираем только файлы юзера если это не админ
        if (accountService.hasPermissionsTo(account, quest)) {
            for (QuestFile questFile : questFiles) {
                QuestFileDto dto = mapper.map(questFile, QuestFileDto.class);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestFileDto> listQuestFileDtos(Account account, Long questId) {
        Quest quest = questService.findQuestById(questId);
        if (Objects.nonNull(quest)) {
            return listQuestFileDtos(account, quest);
        } else {
            logger.error("Quest is null!");
            return null;
        }
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

    @Override
    @Transactional
    public void saveQuestFile(Account account, MultipartFile file, Long questId) {

        if (file.getSize() > maxImageSize) {
            throw new IllegalArgumentException("Image size > 5mb!");
        }

        Quest quest = questService.findQuestById(questId);
        if (Objects.nonNull(quest)) {
            try {
                QuestFile questFile = new QuestFile();
                questFile.setAccount(account);
                questFile.setName(file.getOriginalFilename());
                questFile.setQuest(quest);
                questFile.setType(QuestFile.QuestFileType.PICTURE);

                Long userId = account.getId();
                String imageFileName = questFile.getUniqueCode().toString();
                String rootDirectoryPath = QuestFileUtils.getFilesDirectoryPath();
                String path = userId + DIVIDER + questId + DIVIDER + imageFileName + "." + FORMAT;

                questFile.setPath(path);

                File rootDirectory = new File(rootDirectoryPath);
                QuestFileUtils.createDirIfNotExist(rootDirectory);
                QuestFileUtils.createDirIfNotExist(new File(rootDirectoryPath + DIVIDER + userId));
                QuestFileUtils.createDirIfNotExist(new File(rootDirectoryPath + DIVIDER + userId + DIVIDER + questId));

                File imageFile = new File(rootDirectoryPath + DIVIDER + path);
                BufferedImage bufferedImage = QuestImageUtils.readMultipart(file);

                if (Objects.nonNull(bufferedImage)) {
                    ImageIO.write(bufferedImage, FORMAT, imageFile);
                } else {
                    throw new IllegalArgumentException("Readed MultipartFile is null!");
                }

                questFileDao.saveOrUpdate(questFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException(String.format("Could not save questFile: %s", ExceptionUtils.getRootCauseMessage(e)));
            }
        } else {
            throw new IllegalArgumentException("Quest is null!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDto getImage(UUID questFileCode) {
        ImageDto image = null;
        QuestFile questFile = questFileDao.findByUniqueCode(questFileCode);
        if (Objects.nonNull(questFile)) {
            try {
                String rootDirectoryPath = QuestFileUtils.getFilesDirectoryPath();
                File imageFile = new File(rootDirectoryPath + DIVIDER + questFile.getPath());
                if (Objects.isNull(imageFile) || !imageFile.exists()) {
                    logger.error("Readed image is null!");
                    return image;
                }
                BufferedImage bufferedImage = null;
                bufferedImage = ImageIO.read(imageFile);
                image = QuestImageUtils.getJpegImageDto(bufferedImage);
            } catch (IOException e) {
                logger.error("Could not read image: {}", ExceptionUtils.getRootCauseMessage(e));
            }
        }
        return image;
    }

    @Override
    @Transactional(readOnly = true)
    public QuestFile findByCode(UUID questFileCode) {
        return questFileDao.findByUniqueCode(questFileCode);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestFile findById(Long id) {
        return questFileDao.findById(id);
    }
}
