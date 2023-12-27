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
    tagTypes: ['Expeditions', 'Expedition'],
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
            }),
            providesTags: ['Expeditions']
        }),
        getExpedition: build.query({
            query: (id) => ({
                url: `/expeditions/${id}`,
                method: "GET"
            }),
            providesTags: ['Expedition']
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
            invalidatesTags: ['Expeditions', 'Expedition']
        }),
        finishExpedition: build.mutation({
            query: (id) => ({
                url: `/expeditions/${id}`,
                method: "POST",
                body: {}
            }),
            invalidatesTags: ['Expeditions', 'Expedition']
        }),
        createExpedition: build.mutation({
            query: (data) => ({
                url: `/expeditions`,
                method: "POST",
                body: data
            }),
            invalidatesTags: ['Expeditions']
        }),
        getStations: build.query({
            query: () => ({
                url: `/stations`,
                method: "GET"
            })
        }),
        getSuitableSpaceship: build.query({
            query: ({sourceStationId, destinationStationId}) => ({
                url: `/spaceships?sourceStationId=${sourceStationId}&destinationStationId=${destinationStationId}`,
                method: "GET"
            }),
            providesTags: ['Expedition']
        }),
        getFreeResearchers: build.query({
            query: () => ({
                url: `/researchers/free`,
                method: "GET"
            }),
            providesTags: ['Expedition']
        }),
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
    useFinishExpeditionMutation,
    useCreateExpeditionMutation,
    useGetStationsQuery,
    useGetSuitableSpaceshipQuery,
    useGetFreeResearchersQuery,
} = resourceApi
