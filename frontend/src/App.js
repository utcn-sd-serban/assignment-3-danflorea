import React, { Component } from 'react';
import SmartPostList from './view/SmartPostList'
import {HashRouter, Switch, Route} from "react-router-dom";
import SmartCreatePost from "./view/SmartCreatePost";
import SmartPostDetails from "./view/SmartPostDetails";
import SmartSiteUserList from "./view/SmartSiteUserList";
import SmartCreateSiteUser from "./view/SmartCreateSiteUser";
import SmartSiteUserDetails from "./view/SmartSiteUserDetails";
import SmartFilteredPosts from "./view/SmartFilteredPosts";

const App = () => (
  <div className="App">
    <HashRouter>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <a className="navbar-brand" href="#">BDbSO</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <a className="nav-link" href="#">Browse Questions</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Your Questions</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <Switch>
            <Route exact={true} component={SmartPostList} path="/"/>
            <Route exact={true} component={SmartCreatePost} path="/create-Post"/>
            <Route exact={true} component={SmartPostDetails} path="/post-details/:index"/>
            <Route exact={true} component={SmartFilteredPosts} path="/search-title-post/"/>
            <Route exact={true} component={SmartFilteredPosts} path="/search-tag-post/"/>
        </Switch>
    </HashRouter>
  </div>
);


export default App;
