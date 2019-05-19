package ro.utcn.danf.a1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ro.utcn.danf.a1.dto.SiteUserDTO;
import ro.utcn.danf.a1.service.UserManagementService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SiteUsersController {

    private final UserManagementService siteUserService;



    @GetMapping("/users")
    public List<SiteUserDTO> readAll(){
        return siteUserService.listAll();
    }

//    @PostMapping("/users")
//    public SiteUserDTO create(@RequestBody SiteUserDTO dto){
//        return siteUserService.create(dto);
//    }

}
