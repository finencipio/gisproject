import React from 'react';
import axios from 'axios';


class LoginCheck extends React.Component {

    state = {
        message: 'Not logged'
    }

    handleSubmit(e) {
        axios.get('/welcome').then(res => {
            this.setState({message : res.data + " => Logged"})
        }).catch(err => {
            this.setState({message : "Not logged"})
        })
    }

    render() {
        
        return <div className="LoginCheck" ref={this.props.containerRef}>
            <span onClick={() => this.handleSubmit()} className="btn">Check login status</span>
            <span>{this.state.message}</span>
        </div>
    }

}

export default LoginCheck;
