package com.diaryapp.controller;

import com.diaryapp.model.User;
import com.diaryapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (user.getId() == null || user.getId().isBlank() || user.getName() == null || user.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Tên và ID người dùng không được để trống");
        }
        boolean success = userService.addUser(user);
        return success ? ResponseEntity.ok("Đã thêm người dùng") :
                ResponseEntity.status(409).body("Người dùng đã tồn tại");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        boolean success = userService.deleteUser(id);
        return success ? ResponseEntity.ok("Đã xóa người dùng") :
                ResponseEntity.status(404).body("Không tìm thấy người dùng");
    }
}
