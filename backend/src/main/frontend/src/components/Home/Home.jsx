import React from 'react';
import axios from 'axios';
import {connect} from "react-redux";
import * as actionTypes from "../../store/actions";
import {urlToBackendServer} from '../../config/static.config';

class Home extends React.Component {
    state = {
        data: undefined
    }

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        axios.post(`${urlToBackendServer}/temp`, {
            timestamp: "2020-09-12 06:11:00"
        }).then(res => {
            console.log(res);
            this.setState({data: res.data});
        }).catch(err => {
            console.log(err);
            alert(err.response ? err.response.data.message : 'Error in temp post method');
        })
    }

    render() {

        return (
            <div>
                <br></br><br></br>
                <div >
                    <span>
                        <p style={{ color: "white", marginTop: "30px", fontFamily: "Poppins", fontSize: '100px' }}>Weather APP</p>
                    </span>
                </div>
                <div>
                    {
                        this.props.loggedUser ?
                            <span className="btn" onClick={this.handleSubmit}>Submit</span>
                            :
                            undefined
                    }
                </div>
                <div>
                    {
                        JSON.stringify(this.state.data)
                    }
                </div>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        loggedUser: state.userReducer.user
    }
}

const mapDispatchToProps = dispatch => {
    return {
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);