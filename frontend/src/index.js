import React from 'react';
import App from './App';
import {createRouterMiddleware, createRouterReducerMapObject, ReduxRouter} from "@lagunovsky/redux-react-router";
import {Provider} from "react-redux";
import {createRoot} from "react-dom/client";
import {createBrowserHistory} from "history";
import {configureStore} from "@reduxjs/toolkit";

import loginReducer from './redux/login/loginSlice'
import {authApi} from "./api/authApi";
import {resourceApi} from "./api/resourceApi";

const container = document.getElementById('root')
const root = createRoot(container)
export const history = createBrowserHistory()
const routerMiddleware = createRouterMiddleware(history)

export const store = configureStore({
    reducer: {
        login: loginReducer,
        ...createRouterReducerMapObject(history),
        [authApi.reducerPath]: authApi.reducer,
        [resourceApi.reducerPath]: resourceApi.reducer,
    },
    middleware: (getDefaultMiddleware) => (
        getDefaultMiddleware()
            .prepend(routerMiddleware)
            .concat(authApi.middleware)
            .concat(resourceApi.middleware)
    )
})

root.render(
    <React.StrictMode>
        <Provider store={store}>
            <ReduxRouter history={history}>
                <App/>
            </ReduxRouter>
        </Provider>
    </React.StrictMode>
)
