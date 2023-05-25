package restaurant.ProjectApahida.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.ProjectApahida.model.Admin;
import restaurant.ProjectApahida.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins(){
        List<Admin> adminList = adminService.findAllAdmins();
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable("id") Long id){
        Admin admin = adminService.findAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin newAdmin){
        Admin admin = adminService.addAdmin(newAdmin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin newAdmin){
        Admin admin = adminService.updateAdmin(newAdmin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id){
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateAdmin(@RequestBody Admin newAdmin){
        Boolean admin = adminService.authenticateAdmin(newAdmin);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }
}
