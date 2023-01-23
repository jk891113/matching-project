package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.response.*;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 페이징
    @GetMapping("/admin/customer-list")
    public ResponseEntity<StatusAndDataResponseDto> getAllCustomers(@RequestParam int page) {
        List<UserResponseDto> data = adminService.getAllCustomers(page);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "고객 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    // 페이징
    @GetMapping("/admin/seller-list")
    public ResponseEntity<StatusAndDataResponseDto> getAllSellers(@RequestParam int page) {
        List<SellerListResponseDto> data = adminService.getAllSellers(page);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "판매자 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/admin/permission")
    public ResponseEntity<StatusAndDataResponseDto> getPermissionRequestForms() {
        List<PermissionResponseDto> data = adminService.getPermissionRequestForms();
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "판매자 등록 요청 목록 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
    @PutMapping("/admin/users/{userId}/permission")
    public ResponseEntity<StatusResponseDto> permitAuth(@PathVariable String userId){
        adminService.permitAuth(userId);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 승인 완료");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/admin/sellers/{userId}/permission")
    public ResponseEntity<StatusResponseDto> removeAuth(@PathVariable String userId){
        adminService.removeAuth(userId);
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 회수 완료");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
