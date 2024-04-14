package Student.NRI.Service;

import Student.NRI.model.Student;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
@Service
@AllArgsConstructor
public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        // Check if the file content type is for Excel files
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Student> getStudentsDataFromExcel(InputStream inputStream) throws IOException {
        List<Student> students = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            // Get the first sheet named "students"
            XSSFSheet sheet = workbook.getSheet("students");
            if (sheet != null) {
                // Iterate over each row in the sheet
                for (Row row : sheet) {
                    // Skip the first row (header)
                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    Iterator<Cell> cellIterator = row.cellIterator();
                    Student student = new Student();
                    // Iterate over each cell in the row
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        int columnIndex = cell.getColumnIndex();
                        // Set values based on cell index
                        switch (columnIndex) {
                            case 0:
                                student.setStudentId((int) cell.getNumericCellValue());
                                break;
                            case 1:
                                student.setFirstName(cell.getStringCellValue());
                                break;
                            case 2:
                                student.setLastName(cell.getStringCellValue());
                                break;
                            case 3:
                                student.setCountry(cell.getStringCellValue());
                                break;
                            case 4:
                                student.setTelephone((int) cell.getNumericCellValue());
                                break;
                            default:
                                // Handle additional columns if needed
                        }
                    }
                    students.add(student);
                }
            } else {
                throw new IOException("Sheet named 'students' not found in the Excel file.");
            }
        }
        return students;
    }
}
