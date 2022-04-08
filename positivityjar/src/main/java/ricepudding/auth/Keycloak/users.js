import React, {useEffect, useState} from 'react'
import axios from 'axios'
import {useKeycloak} from '@react-keycloak/web'

const Users = () => {
  const {keycloak, initialized} = useKeycloak()
  const [users, setUsers] = useState([])

  //load users when the component loads
  useEffect(() => {
    //call your backend api to load users
    axios.get("http://YOUR_BACKEND/users", {
      headers: {
        //put the keycloak access token in the Authorization header
        'Authorization': `Bearer ${keycloak.token}`
      }
    }).then((response) => {
      setUsers(response.data)
    })
  }, [])

  //render users...
}
