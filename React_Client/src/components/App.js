import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Switch, Route, Link } from "react-router-dom";

import Pokemon from "./Pokemon";

function App() {
  return (
    <div className="App">

      <nav className="navbar navbar-expand navbar-dark bg-dark sticky-top">
        <a href="/" className="navbar-brand">
          MichaelJ
        </a>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/MC"} className="nav-link">
              MC
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/立体图鉴"} className="nav-link">
              立体图鉴
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/AG地台"} className="nav-link">
              AG地台
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/mypoke/api/baseinfos"} className="nav-link">
              Api
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add"} className="nav-link">
              Add
            </Link>
          </li>
        </div>
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/projects"]} component={Pokemon} />
          {/*<Route path="/projects/:formid/:datatype" component={DataTable} />*/}
          {/* <Route path="/add" element={<AddTutorial />} /> wzj */}
          {/* <Route path="/projects/:id" element={<Project />} />*/}
        </Switch>
      </div>

    </div>
  );
}

export default App;
