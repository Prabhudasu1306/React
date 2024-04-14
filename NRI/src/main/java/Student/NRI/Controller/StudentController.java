package Student.NRI.Controller;

import Student.NRI.ExcelValidator;
import Student.NRI.Service.StudentService;
import Student.NRI.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("students")
public class StudentController {
    private StudentService studentService;

    @PostMapping(value = "/upload-students-data", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> uploadStudentsData(@RequestParam("file") MultipartFile file){
        // Log file information for debugging
        System.out.println("Received file: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize() + " bytes");
        System.out.println("Content type: " + file.getContentType());

        if (!file.isEmpty() && Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            studentService.saveStudentsToDatabase(file);
            return ResponseEntity.ok(Map.of("Message", "Student data uploaded and saved to database successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("Error", "Invalid file type or empty file"));
        }
    }


    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }
}