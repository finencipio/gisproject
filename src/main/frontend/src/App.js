import React, { useState } from "react";
import "./App.scss";
import Login from "./components/Auth/Login/Login";
import Register from "./components/Auth/Register/Register";
import { CookiesProvider } from 'react-cookie';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import NavBar from './components/NavBar/NavBar';
import Home from './components/Home/Home';
import Map from './components/Map/Map';




function App() {
  return (
    <CookiesProvider>
      <Router>
        <div className="App">
          <NavBar />
          <div className="Content">
            <Switch>
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={Register} />
              <Route exact path="/" component={Home} />
            </Switch>
          </div>
        </div>
      </Router>
    </CookiesProvider>
  );
}

export default App;

/*
you can find explanation about routing on this site: https://reacttraining.com/react-router/web/example/basic
*/
