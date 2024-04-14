package Student.NRI.Service;//package Student.NRI.Service;

import Student.NRI.Repository.StudentRepository;
import Student.NRI.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
   public void saveStudentsToDatabase(MultipartFile file){
       if(ExcelUploadService.isValidExcelFile(file)){
          try {
               List<Student> students = ExcelUploadService.getStudentsDataFromExcel(file.getInputStream());
                this.studentRepository.saveAll(students);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
}



