import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    token: localStorage.getItem("token"),
    isCapitan: localStorage.getItem("isCapitan")
}

export const loginSlice = createSlice({
    name: "login",
    initialState,
    reducers: {
        loginResult: (state, action) => {
            if (!action.payload) {
                state.token = null;
                state.isCapitan = null;
                localStorage.setItem("token", null);
                localStorage.setItem("isCapitan", null);
            } else {
                state.token = action.payload.credentials;
                state.isCapitan = action.payload.isCapitan;
                localStorage.setItem("token", action.payload.credentials);
                localStorage.setItem("isCapitan", action.payload.isCapitan);
            }
        }
    }
})

export const {loginResult} = loginSlice.actions

export default loginSlice.reducer
