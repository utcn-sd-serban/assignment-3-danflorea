import React, {Component} from "react";
import Model from "../model/Model";
import Post from "../model/Post";
import CreatePost from "./CreatePost";
import CreatePostPresenter from "../presenter/CreatePostPresenter";

const mapModelStateToComponentState = modelState => ({
    title: modelState.newPost.title,
    text: modelState.newPost.text,
    tags: modelState.newPost.tags
});

export default class SmartCreatePost extends Component{
    constructor(){
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
            <CreatePost
                title={this.state.title}
                text={this.state.text}
                tags={this.state.tags}
                onCreate={CreatePostPresenter.onCreate}
                onChange={CreatePostPresenter.onChange}/>
        );
    }
}