import React from "react";

const PostList = ({ posts, onCreatePost, onViewDetails, onSearchTitleClicked, onSearchTagClicked, onChange }) => (

    <div className="col-lg-10">
        <br/>
        <input data-cy="search_input" onChange={ e => onChange("toSearch", e.target.value)}/>
        <button data-cy="search_title_button" onClick={ onSearchTitleClicked}> Search by title </button>
        <button data-cy="search_tag_button" onClick={ onSearchTagClicked}> Search by tag </button>
        <br/>
        <br/>

        <h2> Current questions </h2>
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
                    <th> Tags </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            {
                posts.map((post, index) => (
                    <tr key={index} data-cy="posts_list">
                        <td> { post.postid }</td>
                        <td> { post.userid }</td>
                        <td> { post.parentid }</td>
                        <td> { post.title }</td>
                        <td> { post.text }</td>
                        <td> { post.posttype }</td>
                        <td> { post.creationdate }</td>
                        <td> { post.tags.length>0?post.tags.reduce((a,x)=>a+","+x):""}</td>
                        <td><button onClick={ () => onViewDetails(index)}> Read more </button></td>
                    </tr>
                ))
            }
            </tbody>
        </table>
        <br/>

        <button onClick={onCreatePost} data-cy="ask_question_button">Ask me a fucking question</button>

    </div>
);


export default PostList;

