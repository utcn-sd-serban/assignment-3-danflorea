package ro.utcn.danf.a1.dto;

import lombok.Data;
import ro.utcn.danf.a1.model.Post;

import java.util.Date;
import java.util.List;

@Data
public class PostDTO {

    private Integer postid;
    private Integer userid;
    private Integer parentid;
    private String title;
    private String text;
    private String posttype;
    private List<String> tags;
    private Date creationdate;


    public static PostDTO ofEntity(Post post, List<String> tags){
        PostDTO postDTO = new PostDTO();

        postDTO.setPostid(post.getPostid());
        postDTO.setUserid(post.getUserid());
        postDTO.setCreationdate(post.getCreationdate());
        postDTO.setParentid(post.getParentid());
        postDTO.setPosttype(post.getPosttype());
        postDTO.setText(post.getText());
        postDTO.setTitle(post.getTitle());
        postDTO.setTags(tags);

        return postDTO;
    }

}
