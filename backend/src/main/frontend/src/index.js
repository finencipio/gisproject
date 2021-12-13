import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import axios from 'axios';
import staticConfig from './config/static.config';

import { Provider } from 'react-redux';
import { createStore, combineReducers } from 'redux';

import userReducer from './store/reducers/userReducer';

//redux tutorial https://redux.js.org/introduction/getting-started
//you can look at every reducer as a little store of data depending on resource and that data is available everywhere within frontend components

const rootReducer = combineReducers({
    userReducer: userReducer
});

const store = createStore(rootReducer);

axios.defaults.withCredentials = true;
axios.defaults.headers['Content-Type'] = 'application/json;charset=UTF-8';
axios.defaults.headers['Access-Control-Expose-Headers'] = 'Content-Disposition';
ReactDOM.render(<Provider store={store}><App /></Provider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
