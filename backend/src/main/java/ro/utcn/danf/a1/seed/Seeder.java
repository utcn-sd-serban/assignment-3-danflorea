package ro.utcn.danf.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.danf.a1.model.Post;
import ro.utcn.danf.a1.model.PostTag;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.model.Tag;
import ro.utcn.danf.a1.persistence.api.*;

import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Seeder implements CommandLineRunner {

    private final RepositoryFactory factory;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {

        UserRepository userRepository = factory.createUserRepository();
        if(userRepository.findAll().isEmpty()) {
            userRepository.save(new SiteUser(null, "u1", passwordEncoder.encode("p1"), "A@example.com", false, "yes"));
            userRepository.save(new SiteUser(null, "u2", passwordEncoder.encode("p1"), "B@example.com", false, "yes"));
            userRepository.save(new SiteUser(null, "u3", passwordEncoder.encode("p1"), "C@example.com", false, "yes"));
        }

        TagRepository tagRepository = factory.createTagRepository();
        if (tagRepository.findAll().isEmpty())
        {
            tagRepository.save(new Tag(null,"java"));
            tagRepository.save(new Tag(null,"misc"));
        }

        PostRepository postRepository = factory.createPostRepository();
        if (postRepository.findAll().isEmpty())
        {
            int id1 = userRepository.findAll().get(0).getUserid();
            int id2 = userRepository.findAll().get(1).getUserid();
            postRepository.save(new Post(null, id1 ,null,"Java", "Island in the kingdom of Majapahit?","QUESTION", Calendar.getInstance().getTime()));
            postRepository.save(new Post(null, id1,null,"Zela", "Veni vidi vici","QUESTION", Calendar.getInstance().getTime()));
            postRepository.save(new Post(null, id2,null,"Question 3", "Did you ever hear the tragedy of Darth Plagueis the Wise?","QUESTION", Calendar.getInstance().getTime()));
            postRepository.save(new Post(null, id2, postRepository.findAll().get(2).getPostid(),"Nop", "Y O U N G L I N G S","ANSWER", Calendar.getInstance().getTime()));
        }

        PostTagRepository postTagRepository = factory.createPostTagRepository();
        if (postTagRepository.findAll().isEmpty())
        {
            int idPost1 = postRepository.findAll().get(0).getPostid();
            int idPost2 = postRepository.findAll().get(1).getPostid();
            int idTag1 = tagRepository.findAll().get(0).getTagid();
            postTagRepository.save(new PostTag(null,idPost1, idTag1));
            postTagRepository.save(new PostTag(null,idPost2, idTag1));
        }
    }

    @Transactional
    public void clearPosts(){
        factory.createPostTagRepository().findAll().stream()
                .forEach(p -> factory.createPostTagRepository().remove(p));
        List<PostTag> postTags = factory.createPostTagRepository().findAll();
        factory.createPostRepository().findAll().stream()
                .forEach(p -> factory.createPostRepository().remove(p));
        List<Post> posts = factory.createPostRepository().findAll();
        int x = 5;
    }

}
