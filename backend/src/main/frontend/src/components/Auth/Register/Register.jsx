import React from 'react';
import '../style.scss'
import { Link } from 'react-router-dom';
import axios from 'axios';

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);

        document.addEventListener('keyup', (e) => {
            if (e.key === 'Enter') {
                this.handleSubmit();
            }
        })
    }

    state = {
        email: '',
        username: '',
        password: '',
        firstname: '',
        lastname: '',
        loading: false
    }

    onInputChangeHandler(updatedKeyValuePair) {
        this.setState(updatedKeyValuePair);
    }

    handleSubmit(e) {
        this.setState({ loading: true });
        axios.post('/users/register', {
            email: this.state.email,
            username: this.state.username,
            password: this.state.password,
            firstname: this.state.firstname,
            lastname: this.state.lastname
        }).then(res => {
            this.props.history.push(`/login`);
            this.setState({ loading: false });
        }).catch(err => {
            alert(err.response ? err.response.data.message : 'Fail');
            this.setState({loading: false});
        })
    }

    render() {

        return <div className="base-container" ref={this.props.containerRef}>
            <div className="header">
                <Link to="/login" className="btn">Login</Link>
                <span className="btn disabled-btn">Register</span>
            </div>
            <div className="content">
                <div className="form">
                    <div className="formgroup">
                        <label htmlFor="email">Email</label>
                        <input type="text" name="email" placeholder="email"
                            value={this.state.email} onChange={(event) => this.onInputChangeHandler({ email: event.target.value })} />
                    </div>
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
                    <div className="formgroup">
                        <label htmlFor="firstname">First name</label>
                        <input type="text" name="firstname" placeholder="first name"
                            value={this.state.firstname} onChange={(event) => this.onInputChangeHandler({ firstname: event.target.value })} />
                    </div>
                    <div className="formgroup">
                        <label htmlFor="lastname">Last name</label>
                        <input type="text" name="lastname" placeholder="last name"
                            value={this.state.lastname} onChange={(event) => this.onInputChangeHandler({ lastname: event.target.value })} />
                    </div>
                </div>
            </div>
            <div className="footer">
                {
                    this.state.loading
                        ?
                        <div className="loader" />
                        :
                        <button name="register_submit" type="button" className="btn" onClick={this.handleSubmit}>Register</button>
                }
            </div>
        </div>
    }

}

export default Register;