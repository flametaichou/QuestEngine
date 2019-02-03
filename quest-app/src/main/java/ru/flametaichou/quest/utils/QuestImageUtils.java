package ru.flametaichou.quest.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import ru.flametaichou.quest.core.dto.ImageDto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QuestImageUtils {

    private static Logger logger = LoggerFactory.getLogger(QuestImageUtils.class);

    private static final int[] RGB_MASKS = {0xFF0000, 0xFF00, 0xFF};
    private static final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
    private static final String JPEG = "image/jpeg";
    private static final String PNG = "image/png";

    public static BufferedImage readMultipart(MultipartFile multipartFile) {
        try {
            Image img = Toolkit.getDefaultToolkit().createImage(multipartFile.getBytes());

            PixelGrabber pg = new PixelGrabber(img, 0, 0, -1, -1, true);
            try {
                pg.grabPixels();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int width = pg.getWidth(), height = pg.getHeight();

            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);

            return new BufferedImage(RGB_OPAQUE, raster, false, null);
        } catch (Exception e) {
            logger.error("Error on reading image: {}", ExceptionUtils.getRootCauseMessage(e));
            return null;
        }
    }

    public static byte[] toJpeg(BufferedImage image) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Could not save to JPEG.", e);
            throw new RuntimeException(e);
        }
    }

    public static byte[] toPng(BufferedImage image) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Could not save to PNG.", e);
            throw new RuntimeException(e);
        }
    }

    public static ImageDto getJpegImageDto(BufferedImage bufferedImage) {
        byte[] jpeg = toJpeg(bufferedImage);
        String base64String = Base64.encodeBase64String(jpeg);
        ImageDto result = new ImageDto();
        result.setData(base64String);
        result.setFormat(JPEG);
        return result;
    }
}
