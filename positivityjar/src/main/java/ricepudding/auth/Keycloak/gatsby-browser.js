import React from 'react'
import { ReactKeycloakProvider } from '@react-keycloak/web'

import keycloak from './src/keycloak'

const Loading = () => <div>Loading...</div>

export const wrapRootElement = ({ element }) => {

    return (
        <ReactKeycloakProvider
            authClient={keycloak}
            initOptions={{
                onLoad: "login-required",
            }}
            LoadingComponent={<Loading />}
        >
            {element}
        </ReactKeycloakProvider>
    )
}
