package restaurant.ProjectApahida.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant.ProjectApahida.exception.UserNotFoundException;
import restaurant.ProjectApahida.model.Admin;
import restaurant.ProjectApahida.repository.AdminRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
    public boolean authenticateAdmin(Admin admin){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        Optional<Admin> optionalAdmin = adminRepo.findAdminByUsername(admin.getUsername());
        if(optionalAdmin.isPresent()){
            Admin newAdmin = optionalAdmin.get();
            return bCrypt.matches(admin.getPassword(), newAdmin.getPassword());
        }
        return false;
    }
    public Admin addAdmin(Admin admin){
        admin.setAdminCode(String.valueOf(UUID.randomUUID()));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //System.out.println(admin.getPassword());
        return adminRepo.save(admin);
    }
    public List<Admin> findAllAdmins(){
        return adminRepo.findAll();
    }
    public Admin updateAdmin(Admin admin){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }
    public Admin findAdminById(Long id){
        return adminRepo.findAdminById(id).orElseThrow(() ->
            new UserNotFoundException("Admin by id " + id + " was not found"));
    }

    public void deleteAdmin(Long id){
        adminRepo.deleteAdminById(id);
    }
}
