package Student.NRI.Service;

import Student.NRI.Exception.UploadException;
import Student.NRI.Repository.StudentRepository;
import Student.NRI.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ExcelUploadService excelUploadService;

    public void saveStudentsToDatabase(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            if (excelUploadService.isValidExcelFile(file)) {
                List<Student> students = excelUploadService.getStudentsDataFromExcel(inputStream);
                studentRepository.saveAll(students);
            } else {
                throw new IllegalArgumentException("The file is not a valid Excel file");
            }
        } catch (IOException e) {
            throw new UploadException("Error uploading file: " + e.getMessage(), e);
        }
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
