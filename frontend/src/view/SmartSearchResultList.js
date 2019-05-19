import React, {Component} from "react";
import Post from "../model/Post";
import PostList from "./PostList";
import PostListPresenter from "../presenter/PostListPresenter"

const mapModelStateToComponentState = modelState => ({
    posts : modelState.posts
});

export default class SmartSearchResultList extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(Post.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        Post.addListener("change", this.listener);
    }

    componentWillUnmount() {
        Post.removeListener("change", this.listener);
    }

    render(){
        return (
            <PostList
                posts={this.state.posts}
                onViewDetails={PostListPresenter.onViewDetails}
                onCreatePost={PostListPresenter.onCreatePost}
                onSearchQuestions={PostListPresenter.onSearchQuestions}
            />
        );
    }
}