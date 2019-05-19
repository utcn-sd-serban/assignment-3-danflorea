package ro.utcn.danf.a1.command;

import ro.utcn.danf.a1.dto.PostDTO;

public interface Command {

    PostDTO execute(PostDTO dto);

}
