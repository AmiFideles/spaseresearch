import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react"
import {selectToken} from "../redux/login/loginSelectors";
import {createLogoutThunk} from "../redux/thunks/logoutThunk";

const baseQuery = fetchBaseQuery({
    baseUrl: "http://localhost:8080/api/point",
    prepareHeaders: (headers, {getState}) => {
        const token = selectToken(getState())
        if (token) {
            headers.set("authorization", `Bearer ${token}`)
        }
        return headers
    },
})
const baseQueryWithRedirect = async (args, api, extraOptions) => {
    let result = await baseQuery(args, api, extraOptions)
    if (result.error) {
        if (result.error.status === 401) {
            api.dispatch(createLogoutThunk())
        }
        console.log(result.error)
    }
    return result
}

export const resourceApi = createApi({
    reducerPath: "api",
    baseQuery: baseQueryWithRedirect,
    // tagTypes: ['points'],
    endpoints: (build) => ({
        getProfessions: build.query({
            query: () => ({
                url: `professions`,
                method: "GET"
            }),
            transformResponse: (response) => response.data
        }),
    })
})

export const {useGetProfessionsQuery} = resourceApi
