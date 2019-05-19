import React, {Component} from "react";
import post from "../model/Post";
import PostDetails from "./PostDetails";

const mapModelStateToComponentState = (modelState, props) => (
    modelState.posts[parseInt(props.match.params.index)]
);

export default class SmartPostDetails extends Component{
    constructor(props){
        super(props);
        this.state = mapModelStateToComponentState(post.state, props);

        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        post.addListener("change", this.listener);
    }

    componentDidUpdate(prev) {
        if(prev.match.params.index !== this.props.match.params.index){
            this.setState(mapModelStateToComponentState(post.state, this.props));
        }
    }

    componentWillUnmount() {
        post.removeListener("change", this.listener);
    }

    render(){
        return (
            <PostDetails
                postid={this.state.postid}
                userid={this.state.userid}
                parentid={this.state.parentid}
                title={this.state.title}
                text={this.state.text}
                posttype={this.state.posttype}
                tags={this.state.tags}
                creationdate={this.state.creationdate}
            />
        );
    }
}