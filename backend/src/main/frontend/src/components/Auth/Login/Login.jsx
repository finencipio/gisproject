import React from 'react';
import '../style.scss';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { connect } from 'react-redux';
import {urlToBackendServer} from '../../../config/static.config';

import * as actionTypes from '../../../store/actions';


class Login extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.axios = axios;

        document.addEventListener('keyup', (e) => {
            if (e.key === 'Enter') {
                this.handleSubmit();
            }
        })
    }

    state = {
        username: '',
        password: '',
        loading: false
    }

    handleSubmit(e) {
        this.setState({ loading: true })
        axios.post(`${urlToBackendServer}/login`, {
            username: this.state.username,
            password: this.state.password
        }).then(res => {
            this.axios.defaults.headers['Authorization'] = "Bearer " + res.data.token;
            this.props.history.push(`/`);
            this.props.onUserFetched(res.data);
            this.setState({loading: false})
        }).catch(err => {
            alert(err.response ? err.response.data.message : 'Wrong credentials');
            this.setState({loading: false})
        })
    }

    onInputChangeHandler(updatedKeyValuePair) {
        this.setState(updatedKeyValuePair);
    }

    render() {

        return <div className="base-container" ref={this.props.containerRef}>
            <div className="header">
                <span className="btn disabled-btn">Login</span>
                <Link to="/register" id="register" className="btn">Register</Link>
            </div>
            <div className="content">
                <div className="form">
                    <div className="formgroup">
                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" placeholder="username"
                            value={this.state.username} onChange={(event) => this.onInputChangeHandler({ username: event.target.value })} />
                    </div>
                    <div className="formgroup">
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="password"
                            value={this.state.password} onChange={(event) => this.onInputChangeHandler({ password: event.target.value })} />
                    </div>
                </div>
            </div>
            <div className="footer">
                {this.state.loading ?
                    <div className="loader" />
                    :
                    <span className="btn" name="login_submit" onClick={this.handleSubmit}>Submit</span>
                }
            </div>
        </div>
    }

}

const mapStateToProps = state => {
    return {
        loggedUser: state.userReducer.user
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onUserFetched: (user) => dispatch({
            type: actionTypes.USER_FETCHED,
            payload: {
                user: user
            }
        })
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);