package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.dto.response.UserResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Secured({"ROLE_ADMIN"})
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/customer-list")
    public List<UserResponseDto> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("/admin/seller-list")
    public List<SellerListResponseDto> getAllSellers() {
        return adminService.getAllSellers();
    }

    @GetMapping("/admin/permission")
    public List<PermissionResponseDto> getPermissionRequestForms(){
        return adminService.getPermissionRequestForms();
    }

    @PutMapping("/admin/users/{userId}/permission")
    public ResponseEntity<StatusResponseDto> permitAuth(@PathVariable String userId){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 승인 완료");
        adminService.permitAuth(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/admin/sellers/{userId}/permission")
    public ResponseEntity<StatusResponseDto> removeAuth(@PathVariable String userId){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 회수 완료");
        adminService.removeAuth(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
