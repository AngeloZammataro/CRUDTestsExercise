package co.develhope.crud.controllers;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import co.develhope.crud.services.StudentService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    // create a new Student
    @PostMapping("")
    public @ResponseBody
    Student create(@RequestBody Student student){
        return studentRepository.save(student);
    }

    // getting a list of all the Students
    @GetMapping("/")
    public @ResponseBody
    List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // getting a specific Student by:
    @GetMapping("/{id}")
    public @ResponseBody  Student getAStudent(@PathVariable long id){
        Optional<Student> student =  studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else{
            return null;
        }
    }

    // updating the primary key of a Student by:
    @PutMapping("/{id}")
    // passing the primary key as path variable
    // passing a Student in the request body
    public @ResponseBody Student update(@PathVariable long id, @RequestBody  @NotNull Student student){
        student.setId(id);
        return studentRepository.save(student);
    }

    // updating the isWorking value by:
    @PutMapping("/{id}/work")
    // passing the primary key as path variable
    // passing a request param called working
    public @ResponseBody Student setStudentIsWorking(@PathVariable long id, @RequestParam("working") boolean working){
        return studentService.setStudentIsWorkingStatus(id, working);
    }

    // deleting a Student
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        studentRepository.deleteById(id);
    }
}
