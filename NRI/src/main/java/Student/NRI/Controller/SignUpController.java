package Student.NRI.Controller;

import Student.NRI.Exception.ResourceNotFoundException;
import Student.NRI.Repository.SignUpRepository;
import Student.NRI.model.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private final SignUpRepository signUpRepository;

    @Autowired
    public SignUpController(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSignUp(@RequestBody SignUp signUp) {
        try {
            SignUp createdSignup = signUpRepository.save(signUp);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSignup);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SignUp>> getAllSignUp() {
        List<SignUp> signUps = signUpRepository.findAll();
        return ResponseEntity.ok(signUps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignUp> getSignUpById(@PathVariable("id") Long id) {
        SignUp signUp = signUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));
        return ResponseEntity.ok(signUp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SignUp> updateSignUp(@PathVariable Long id, @RequestBody SignUp signUp) {
        SignUp existingSignUp = signUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));

        existingSignUp.setFirstName(signUp.getFirstName());
        existingSignUp.setLastName(signUp.getLastName());
        existingSignUp.setEmail(signUp.getEmail());
        existingSignUp.setPassword(signUp.getPassword());

        SignUp updatedSignUp = signUpRepository.save(existingSignUp);
        return ResponseEntity.ok(updatedSignUp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSignUp(@PathVariable Long id) {
        SignUp signUp = signUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));

        signUpRepository.delete(signUp);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Data integrity violation: " + e.getMessage());
    }
}
