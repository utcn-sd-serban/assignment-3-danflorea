import React, {Component} from "react";
import Model from "../model/Model";
import SiteUserDetails from "./SiteUserDetails";

const mapModelStateToComponentState = (modelState, props) => (
    modelState.siteUsers[props.match.params.index]
);

export default class SmartSiteUserDetails extends Component{
    constructor(props){
        super(props);
        this.state = mapModelStateToComponentState(Model.state, props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        Model.addListener("change", this.listener);
    }

    componentDidUpdate(prev) {
        if(prev.match.params.index !== this.props.match.params.index){
            this.setState(mapModelStateToComponentState(Model.state, this.props));
        }
    }

    componentWillUnmount() {
        Model.removeListener("change", this.listener);
    }

    render(){
        return (
            <SiteUserDetails
                userid={this.state.userid}
                username={this.state.username}
                password={this.state.password}
                email={this.state.email}/>
        );
    }
}