package controllers;

import models.Staff;
import repositories.IStaffRepository;

import java.util.List;

public class StaffController implements IStaffController {
   private final IStaffRepository staffRepository;
    public StaffController(IStaffRepository repo) {
        this.staffRepository = repo;
    }
    @Override
    public Staff addMember(Staff staff) {
        return staffRepository.addMember(staff);
    }
    @Override
    public Staff findMemberByIdPassword(int id, String password) {
        Staff staff = staffRepository.findMemberByIdPassword(id, password);
        return staff;
    }
    @Override
    public void showAllMembers() {
        List<Staff> staffList = staffRepository.showAllMembers();
        staffList.forEach(System.out::println);
    }
    @Override
    public void deleteMemberById(int id) {
        staffRepository.deleteMemberById(id);
    }
}
