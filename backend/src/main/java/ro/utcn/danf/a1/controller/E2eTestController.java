package ro.utcn.danf.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.danf.a1.seed.Seeder;

@Profile("e2e")
@RestController
@RequiredArgsConstructor
public class E2eTestController {

    private final Seeder seeder;

    @RequestMapping("/test/reseed")
    public void reseed(){
        seeder.clearPosts();
        seeder.run();
    }

}
