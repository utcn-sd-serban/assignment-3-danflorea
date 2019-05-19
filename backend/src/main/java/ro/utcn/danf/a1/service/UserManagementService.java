package ro.utcn.danf.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.danf.a1.dto.SiteUserDTO;
import ro.utcn.danf.a1.exception.InvalidLoginCredentialException;
import ro.utcn.danf.a1.exception.UserNotFoundException;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.persistence.api.RepositoryFactory;
import ro.utcn.danf.a1.persistence.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final RepositoryFactory repositoryFactory;


    @Transactional
    public List<SiteUserDTO> listAll(){
        return  repositoryFactory.createUserRepository().findAll().stream()
                .map(SiteUserDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public SiteUserDTO create (SiteUserDTO dto){
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(dto.getUsername());
        siteUser.setPassword(dto.getPassword());
        siteUser.setEmail(dto.getEmail());
        siteUser.setBanned(dto.isBanned());
        siteUser.setPermission(dto.getPermission());

        return SiteUserDTO.ofEntity( repositoryFactory.createUserRepository().save(siteUser));
    }

    @Transactional
    public SiteUser userLogin(String username, String password)
            throws UserNotFoundException, InvalidLoginCredentialException {
        Optional<SiteUser> user = repositoryFactory.createUserRepository().findByUsername(username);
        if (!user.isPresent())
            throw new UserNotFoundException();

        SiteUser siteUser = user.get();
        if(!siteUser.getPassword().equals(password))
            throw new InvalidLoginCredentialException();

        return siteUser;
    }

}
