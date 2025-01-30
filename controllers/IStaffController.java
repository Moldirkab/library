package controllers;

import models.Staff;

public interface IStaffController {


    Staff addMember(Staff staff);

    Staff findMemberByIdPassword(int id, String password);

    void showAllMembers();

    void deleteMemberById(int id);
}
