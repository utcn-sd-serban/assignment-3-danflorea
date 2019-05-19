package ro.utcn.danf.a1.event;

import lombok.Data;
import ro.utcn.danf.a1.dto.PostDTO;

@Data
public class PostCreatedEvent extends BaseEvent {

    private final PostDTO post;

    public PostCreatedEvent (PostDTO post){
        super(EventType.POST_CREATED);
        this.post = post;
    }
}
