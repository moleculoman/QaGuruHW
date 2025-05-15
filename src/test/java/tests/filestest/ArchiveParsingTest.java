package tests.filestest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveParsingTest {
    private final ClassLoader classLoader = ArchiveParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка pdf-файла из архива")
    public void checkPDFFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("testArchive.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        )
        {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zipInputStream);
                    assertEquals("TestingFile", pdf.title);
                    assertEquals("MaxisTest", pdf.author);
                    assertThat(pdf.text)
                            .containsAnyOf("Тестовый PDF-файл")
                            .contains("пройти тест")
                            .doesNotContain("секретные данные");
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка структуры xls-файла из архива")
    public void checkXLSStructureFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("testArchive.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zipInputStream);
                    String externalIdentifier = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    String secondName = xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
                    String firstName = xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue();
                    String surName = xls.excel.getSheetAt(0).getRow(0).getCell(3).getStringCellValue();
                    String department = xls.excel.getSheetAt(0).getRow(0).getCell(4).getStringCellValue();
                    String position = xls.excel.getSheetAt(0).getRow(0).getCell(5).getStringCellValue();

                    assertEquals("Внешний идентификатор для импорта", externalIdentifier);
                    assertEquals("Фамилия", secondName);
                    assertEquals("Имя", firstName);
                    assertEquals("Отчество", surName);
                    assertEquals("Отдел", department);
                    assertEquals("Должность", position);
                }
            }
        }
    }
    @Test
    @DisplayName("Проверка корректности наполнения xls-файла из архива")
    public void checkXLSFillmentFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("testArchive.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zipInputStream);
                    String externalIdentifier = xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue();
                    String secondName = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
                    String firstName = xls.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();
                    String surName = xls.excel.getSheetAt(0).getRow(1).getCell(3).getStringCellValue();
                    String department = xls.excel.getSheetAt(0).getRow(1).getCell(4).getStringCellValue();
                    String position = xls.excel.getSheetAt(0).getRow(1).getCell(5).getStringCellValue();

                    assertEquals("CN001", externalIdentifier);
                    assertEquals("Иванова", secondName);
                    assertEquals("Ирина", firstName);
                    assertEquals("Витальевна", surName);
                    assertEquals("OU001", department);
                    assertEquals("Руководитель департамента", position);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка csv-файла из архива")
    public void checkCSVFromZipTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("testArchive.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)
        ) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream,StandardCharsets.UTF_8));
                    List<String[]> content = csvReader.readAll();
                    //убираем BOM-маркеры с файла
                    if (!content.isEmpty() && content.get(0).length > 0) {
                        content.get(0)[0] = content.get(0)[0].replace("\uFEFF", "");
                    }
                    assertThat(content).contains(
                            new String[] {"Внешний идентификатор для импорта", "Фамилия", "Имя", "Отчество", "Отдел", "Должность"},
                            new String[] {"CN001", "Иванова", "Ирина", "Витальевна", "OU001", "Руководитель департамента"},
                            new String[] {"CN002", "Сергеев", "Константин", "Игоревич", "OU002", "Маркетолог"},
                            new String[] {"CN003", "Бучельников", "Николай", "Иванович", "OU003", "Рекрутер"},
                            new String[] {"CN004", "Гулина", "Алена", "Евгеньевна", "OU005", "Специалист"},
                            new String[] {"CN005", "Калашников", "Евгений", "Валентинович", "OU005", "Руководитель департамента"}
                    );
                }
            }
        }
    }


}
