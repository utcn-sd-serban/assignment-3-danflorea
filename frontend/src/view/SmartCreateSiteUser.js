import React, {Component} from "react";
import Model from "../model/Model";
import CreateSiteUser from "./CreateSiteUser";
import CreateSiteUserPresenter from "../presenter/CreateSiteUserPresenter";

const mapModelStateToComponentState = modelState => ({
    userid: modelState.newSiteUser.userid,
    username: modelState.newSiteUser.username,
    password: modelState.newSiteUser.password,
    email: modelState.newSiteUser.email
});

export default class SmartCreateSiteUser extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(Model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        Model.addListener("change", this.listener);
    }

    componentWillUnmount() {
        Model.removeListener("change", this.listener);
    }

    render(){
        return (
            <CreateSiteUser
                onCreate={CreateSiteUserPresenter.onCreate}
                onChange={CreateSiteUserPresenter.onChange}
                userid={this.state.userid}
                username={this.state.username}
                password={this.state.password}
                email={this.state.email}/>
        );
    }
}