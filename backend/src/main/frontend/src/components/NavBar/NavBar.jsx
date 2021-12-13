import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import './NavBar.scss';
import staticConfig from '../../config/static.config';

class NavBar extends React.Component {
    constructor(props) {
        super(props);
    }

    formatUserName = () => {
        if (this.props.loggedUser === null) {
            throw new Error("User is not logged");
        }
        let result;

        if (this.props.loggedUser.username.length > 10) {
            result = this.props.loggedUser.username.slice(0, 8) + "...";
        } else {
            result = this.props.loggedUser.username;
        }

        return result;
    }

    generateClassName = (route) => {
        return "btn" + (document.URL.endsWith(route) ? " disabled-btn" : "");
    }

    render() {

        return (
            <div className="NavBar">
                <nav className="nav">
                    {
                        this.props.loggedUser !== null ?
                            <span className="endOfNavBar">
                                <span className="nav-item"><Link to="/userProfile" disabled className="btn dark" name="currentUser">{this.formatUserName()}</Link></span>
                                <span className="nav-item"><a className="logout btn" href='/'>Logout</a></span>
                            </span>
                            :
                            <span className="endOfNavBar">
                                <span id="account" className="nav-item"><Link to="/login" className="btn">Login/Register</Link></span>
                            </span>
                    }
                </nav>

            </div>
        );
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

export default connect(mapStateToProps, mapDispatchToProps)(NavBar);