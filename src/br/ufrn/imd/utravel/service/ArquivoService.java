package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Stateless
public class ArquivoService {
    public void salvar(InputStream inputStream, Path pathFile) throws IOException {
        Files.copy(inputStream, pathFile, StandardCopyOption.REPLACE_EXISTING);
    }
}
