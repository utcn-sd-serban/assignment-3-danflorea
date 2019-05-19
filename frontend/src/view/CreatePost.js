import React from "react";

const CreatePost = ({title, text, tags, onCreate, onChange }) => (
    <div className="col-6 form-group">

        <div className="col-2"> </div>

        <div className="col-8">
            <h2 className="display-6"> Add a question </h2>

            <table className="table">
                <thead>
                    <tr>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> <label> Title </label> </td>
                        <td> <input value={title} data-cy="input_title" onChange={e => onChange("title",e.target.value)}/> </td>
                    </tr>
                    <tr>
                        <td> <label> Text </label> </td>
                        <td> <input value={text} data-cy="input_text" onChange={e => onChange("text",e.target.value)}/> </td>
                    </tr>
                    <tr>
                        <td> <label> Tags </label> </td>
                        <td> <input value={tags} data-cy="input_tags" onChange={e => onChange("tags",e.target.value)}/> </td>
                    </tr>
                </tbody>
            </table>

            <br/>

            <button data-cy="post_question_button" onClick={onCreate}>Ask question</button>
        </div>

        <div className="col-2"> </div>

    </div>
);


export default CreatePost;

