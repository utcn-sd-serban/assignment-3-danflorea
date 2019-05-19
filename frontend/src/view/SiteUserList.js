import React from "react";

const SiteUserList = ({ siteUsers, title, onCreateSiteUser, onViewDetails }) => (
    <div className="col-lg-10">
        <h2> {title || "Users"} </h2>
        <hr/>
        <table border="1">
            <thead>
                <tr>
                    <th> User ID </th>
                    <th> Username </th>
                    <th> Password </th>
                    <th> Email </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            {
                siteUsers.map((siteUser, index) => (
                    <tr key={index}>
                        <td> { siteUser.userid }</td>
                        <td> { siteUser.username }</td>
                        <td> { siteUser.password }</td>
                        <td> { siteUser.email }</td>
                        <td><button onClick={ () => onViewDetails(index)}> show me </button></td>
                    </tr>
                ))
            }
            </tbody>
        </table>
        <br/>

        <button onClick={onCreateSiteUser}>Add new SiteUSer</button>

    </div>
);


export default SiteUserList;

