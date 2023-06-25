import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import './index.css';

import "@fortawesome/fontawesome-free/css/all.min.css";
import "assets/styles/tailwind.css";

const root = ReactDOM.createRoot(document.getElementById('root'));
ReactDOM.render(
    <BrowserRouter>
        <Switch>
            {/* add routes with layouts */}
            <Route path="/admin" component={Admin} />
            <Route path="/auth" component={Auth} />
            {/* add routes without layouts */}
            <Route path="/landing" exact component={Landing} />
            <Route path="/profile" exact component={Profile} />
            <Route path="/" exact component={Index} />
            {/* add redirect for first page */}
            <Redirect from="*" to="/" />
        </Switch>
    </BrowserRouter>,
    document.getElementById("root")
);
