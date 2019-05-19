import React, {Component} from "react";
import Model from "../model/Model";
import SiteUserList from "./SiteUserList";
import SiteUserListPresenter from "../presenter/SiteUserListPresenter"

const mapModelStateToComponentState = modelState => ({
    siteUsers : modelState.siteUsers
});

export default class SmartSiteUsersList extends Component{
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
            <SiteUserList
                onViewDetails={SiteUserListPresenter.onViewDetails}
                onCreateSiteUser={SiteUserListPresenter.onCreateSiteUser}
                siteUsers={this.state.siteUsers}/>
        );
    }
}