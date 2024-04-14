package Student.NRI.Service;//package Student.NRI.Service;

import Student.NRI.Repository.StudentRepository;
import Student.NRI.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//
//import Student.NRI.Repository.StudentRepository;
//import Student.NRI.Service.ExcelUploadService;
//import Student.NRI.model.Student;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.util.List;
//
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



//import Student.NRI.Exception.UploadException;
//import Student.NRI.Repository.StudentRepository;
//import Student.NRI.model.Student;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//@Component
//
//@AllArgsConstructor
//public class StudentService {
//    private StudentRepository studentRepository;
//    private ExcelUploadService excelUploadService;
//
//    public void saveStudentsToDatabase(MultipartFile file){
//        try (InputStream inputStream = file.getInputStream()) {
//            List<Student> students = excelUploadService.getStudentsDataFromExcel(inputStream);
//            studentRepository.saveAll(students);
//        } catch (IOException e) {
//            throw new UploadException("Error uploading file: " + e.getMessage(), e);
//        }
//    }
//
//    public List<Student> getStudents(){
//        return studentRepository.findAll();
//    }
//}