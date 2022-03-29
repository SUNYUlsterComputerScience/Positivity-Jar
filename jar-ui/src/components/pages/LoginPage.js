import React from 'react'
import { Link } from 'react-router-dom'

import '../../App.css'

export default function SignInPage(){
    return(
        <div className="text-center m-5-auto">
        <h2>Sign in to us</h2>
        <form action="/home">
            <p>
                <label>Username or email address</label><br/>
                <input type="text" name="first_name" required />
            </p>
            <p>
                <label>Password</label>
                <label className="right-label">Forget password?</label>
                <br/>
                <input type="password" name="password" required />
            </p>
            <p>
                <button id="sub_btn" type="submit">Login</button>
            </p>
        </form>
        <footer>
            <p>First time? Create an account.</p>
            <p>Back to Homepage.</p>
        </footer>
    </div>
    )
}