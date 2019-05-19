import React from "react";

const SearcbResultList = ({ posts, onViewDetails }) => (

    <div className="col-lg-10">

        <br/>

        <h2> Haha yes </h2>
        <hr/>
        <table border="1">
            <thead>
            <tr>
                <th> Post ID </th>
                <th> User ID </th>
                <th> Parent ID </th>
                <th> Title </th>
                <th> Text </th>
                <th> Post Type </th>
                <th> Creation Date </th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {
                posts.map((post, index) => (
                    <tr key={index}>
                        <td> { post.postid }</td>
                        <td> { post.userid }</td>
                        <td> { post.parentid }</td>
                        <td> { post.title }</td>
                        <td> { post.text }</td>
                        <td> { post.posttype }</td>
                        <td> { post.creationdate }</td>
                        <td><button onClick={ () => onViewDetails(index)}> Read more </button></td>
                    </tr>
                ))
            }
            </tbody>
        </table>
        <br/>

    </div>
);


export default SearcbResultList;

