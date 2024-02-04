package com.example.demo.utils;

import com.example.demo.application.services.S3Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class Utils {

    public static String validarImagensParaAtualizacao(String urlImage, Long id, S3Service s3Service) {
        if (Objects.nonNull(urlImage) && Utils.validaBase64(urlImage) && !urlImage.trim().equals("")) {
            return Utils.retornaUrlImagemBucketS3(urlImage, id, s3Service);
        }
        return urlImage;
    }

    @SneakyThrows
    private static String retornaUrlImagemBucketS3(String urlImage, Long id, S3Service s3Service) {
        File file = new File(Objects.requireNonNull(TempFileUtils.getFilePath(TempFileUtils.createNewFileName(".jpeg"))));
        String formatFile = "";
        if (urlImage.contains("data:image/jpeg;base64,")) {
            urlImage = urlImage.replace("data:image/jpeg;base64,", "");
            formatFile = "jpeg";
        } else if (urlImage.contains("data:image/png;base64,")) {
            urlImage = urlImage.replace("data:image/png;base64,", "");
            formatFile = "png";
        }
        byte[] imagem = Base64.decodeBase64(urlImage);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imagem);
        BufferedImage image = ImageIO.read(byteArrayInputStream);
        ImageIO.write(image, formatFile, file);
        return s3Service.saveBucket(id.toString(), file, "VERZEL");
    }

    public static boolean validaBase64(Object url) {
        return String.valueOf(url).startsWith("data:image/");
    }
}
