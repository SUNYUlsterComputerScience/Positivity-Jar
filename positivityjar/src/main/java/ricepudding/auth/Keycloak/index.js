import React from "react"
import { useKeycloak } from '@react-keycloak/web'

const Home = () => {
  const { keycloak, initialized } = useKeycloak()
  return (
      <>
        <div>The user is {keycloak.authenticated ? '' : 'NOT'} authenticated</div>
        {keycloak.authenticated && (
            <button type="button" onClick={() => keycloak.logout()}>
              Logout
            </button>
         )}
      </>
   )
}

export default Home
