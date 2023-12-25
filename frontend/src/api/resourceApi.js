import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react"
import {selectToken} from "../redux/login/loginSelectors";
import {createLogoutThunk} from "../redux/thunks/logoutThunk";

const baseQuery = fetchBaseQuery({
    baseUrl: "http://localhost:8080/api",
    prepareHeaders: (headers, {getState}) => {
        const token = selectToken(getState())
        if (token) {
            headers.set("authorization", `Basic ${token}`)
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
    }
    return result
}

export const resourceApi = createApi({
    reducerPath: "api/resource",
    baseQuery: baseQueryWithRedirect,
    endpoints: (build) => ({
        getProfessions: build.query({
            query: () => ({
                url: "/professions",
                method: "GET"
            })
        }),
        getExpeditions: build.query({
            query: () => ({
                url: "/expeditions",
                method: "GET"
            })
        }),
        getExpedition: build.query({
            query: (id) => ({
                url: `/expeditions/${id}`,
                method: "GET"
            })
        }),
        getStation: build.query({
            query: (id) => ({
                url: `/stations/${id}`,
                method: "GET"
            })
        }),
        getSpaceship: build.query({
            query: (id) => ({
                url: `/spaceships/${id}`,
                method: "GET"
            })
        }),
        getBreakdowns: build.query({
            query: () => ({
                url: `/breakdowns`,
                method: "GET"
            })
        }),
        createReport: build.mutation({
            query: (data) => ({
                url: "/reports",
                method: "POST",
                body: data
            }),
        })
    })
})

export const {
    useGetProfessionsQuery,
    useGetExpeditionsQuery,
    useGetExpeditionQuery,
    useGetStationQuery,
    useGetSpaceshipQuery,
    useGetBreakdownsQuery,
    useCreateReportMutation,
} = resourceApi
