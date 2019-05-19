package ro.utcn.danf.a1.dto;

import lombok.Data;
import ro.utcn.danf.a1.model.SiteUser;

@Data
public class SiteUserDTO {

    private Integer userid;
    private String username;
    private String password;
    private String email;
    private boolean banned;
    private String permission;

    public static SiteUserDTO ofEntity(SiteUser siteUser){
        SiteUserDTO siteUserDTO = new SiteUserDTO();

        siteUserDTO.setUserid(siteUser.getUserid());
        siteUserDTO.setUsername(siteUser.getUsername());
        siteUserDTO.setPassword(siteUser.getPassword());
        siteUserDTO.setEmail(siteUser.getEmail());
        siteUserDTO.setBanned(siteUser.isBanned());
        siteUserDTO.setPermission(siteUser.getPermission());



        return siteUserDTO;
    }

}
